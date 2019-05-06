package net.codebuilders.mybusiness

// for hibernate search
import org.grails.orm.hibernate.HibernateMappingContextSessionFactoryBean
import org.hibernate.search.cfg.EntityDescriptor
import org.hibernate.search.cfg.PropertyDescriptor

import grails.core.GrailsApplication
import grails.plugins.hibernate.search.HibernateSearchGrailsPlugin

import grails.plugin.springsecurity.SpringSecurityUtils

class BlogController {

    def allowedMethods = [publish: 'POST']

    def springSecurityService
    NoticeService noticeService

    def afterInterceptor = { model ->
        def layout = grailsApplication.config.blog.page.layout
        if (layout) model?.layout = layout
    }

    private parseDateParams(params) {
        //TODO better default values for dates
        if (!params.year) return null
        def month = params.month ? params.month : 01
        def day = params.day ? params.day : 01
        Date.parse("yyyy/MM/dd", "$params.year/$month/$day")
    }



    def list = {
        log.info("Entered blog controller list ...")
        def date = parseDateParams(params)
        def today = new Date()
        def author = params.author
        def entries
        def totalEntries
        if (author) {
            entries = BlogEntry.withCriteria {
                eq 'author', author
                eq 'published', true

                if (date) {
                    if (params.year)
                        between 'dateCreated', date, today
                }
                order "dateCreated", "desc"
                maxResults 10
                cache true
            }
            totalEntries = BlogEntry.createCriteria().get {
                projections { rowCount() }
                eq 'author', author
                eq 'published', true

                if (date) {
                    if (params.year)
                        between 'dateCreated', date, today
                }
                cache true
            }
        } else {
            entries = findRecentEntries()
            totalEntries = BlogEntry.countByPublished(true, [cache: true])
        }

        renderListView entries, totalEntries
    }

    private findBlogAuthors() {
        BlogEntry.withCriteria {
            projections { distinct "author" }
            cache true
        }
    }

    private findRecentEntries() {
        BlogEntry.findAllByPublished(true, [max: 10, cache: true, offset: params.offset, order: "desc", sort: "dateCreated"])
    }

    def feed = {
        def entries = findRecentEntries()
        def feedOutput = {
            title = g.message(code: "blog.feed.title", 'default': "Recent Blog Posts")
            description = g.message(code: "blog.feed.description", 'default': "Recent Blog Posts")
            link = g.createLink(absolute: true, controller: "blog", action: "list")

            for (e in entries) {
                entry(e.title) {
                    link = g.createLink(absolute: true, controller: "blog", action: "showEntry", params: [title: e.title, author: e.author])
                    publishedDate = e.lastUpdated
                    author = e.author
                    e.body
                }
            }
        }
        if (['rss', 'atom'].contains(params.format))
            render(feedType: params.format, feedOutput)
        else
            render(feedType: "rss", feedOutput)
    }

    def createEntry = {
        def entry = new BlogEntry(request.method == 'POST' ? params['entry'] : [:])

        render view: "/blogEntry/create", model: [entry: entry]
    }

    def publish = {
        def entry = BlogEntry.get(params.id) ?: new BlogEntry()
        BlogEntry.withTransaction {
            entry.properties = params['entry']
            def authorEvaluator = grailsApplication.config.grails.blog.author.evaluator

            // don't change the author if updating an entry, because this will cause the entry's URL to change
            if (!entry.author && authorEvaluator instanceof Closure) {
                authorEvaluator.delegate = this
                authorEvaluator.resolveStrategy = Closure.DELEGATE_FIRST
                // entry.author = authorEvaluator.call()?.toString()
                entry.author = authorEvaluator.call()?.username // to fix SecUser(username:admin)
            }

            // added check for spaces in title
            if (entry.title.contains(' ')) {
                entry.title = entry.title.replaceAll(' ', '-')
            }

            entry.published = true

            if (entry.save()) {

                // remove any existing tags, and add the new ones
                println("deleting ${entry.tags.size()} existing tags")
                entry.tags.each {
                    println "trying to delete tag link" // DEBUG
                    println("deleting {it}")
                    entry.removeTag(it)
                }

                println(params.tags) // DEBUG
                params.tags.split(",").collect {
                    it = it.trim()
                    if (it) {
                        println(it) // DEBUG
                        entry.addTag(it)
                    }
                }

                redirect(action: "showEntry", params: [title: entry.title, author: entry.author])
            } else {
                render view: "/blogEntry/create", model: [entry: entry]
            }
        }
    }

    def editEntry = {
        def entry = BlogEntry.get(params.id)
        if (entry) {
            render view: "/blogEntry/create", model: [entry: entry]
        } else {
            response.sendError 404
        }
    }

    def deleteEntry = {
        def entry = BlogEntry.get(params.id)
        if (entry) {
            entry.comments.each { comment ->
                entry.removeComment comment
            }
            entry.delete()
            redirect action: 'list'
        } else {
            response.sendError 404
        }
    }

    def showEntry = {
        def entry = BlogEntry.findByAuthorAndTitle(params.author, params.title)
        if (entry) {
            render view: "/blogEntry/entry", model: [entry: entry]
        } else {
            response.sendError 404
        }
    }

    private renderListView(entries, totalEntries) {
        //List all tags since there isn't a Tag domain object
        /* String listAllHQL = """
            SELECT tag
            FROM Tag tag
            ORDER BY tag.name
        """
        def allTags = BlogEntry.executeQuery(listAllHQL)
        */

        def notices = noticeService.getCurrentNoticesByPage("Blog")

        render(view: "/blogEntry/list", model: [entries     : entries,
                                                authors     : findBlogAuthors(),
                                                tagNames    : BlogEntry.allTags,
                                                // tagNames    : allTags,
                                                totalEntries: totalEntries,
                                                noticeList: notices])
    }

    def search = {
        log.info("entered blog controller search ...")

        List<BlogEntry> blogEntryList
        Integer blogEntryCount = 0

        if (params.q != null) {

            log.info "there was a search ..."
            log.debug("there was a search ...")
            println "there was a search"

            // since a search returns all params, remove the ones we don't need
            params.remove('keyword')
            params.remove('max')
            params.remove('offset')

            // check for empty search and ignore
            if (params.q.trim() != "") {
                log.info "there was a search and it was not blank"
                log.info "setting keyword"
                params.keyword = params.q.trim()

            } else {
                log.info "there was a search and it was blank"
                log.info "not setting keyword"
            }

            params.remove('q')


            log.info "params at the end of search"
            params.each { k, v ->
                log.info "${k} = ${v}"

            }
        }

        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset = params.offset ? params.int('offset') : 0

        if (params.keyword) {

            log.info "entered keyword ..."

            log.info "params at the beginning of keyword"
            params.each { k, v ->
                log.info "${k} = ${v}"
            }

            // log.debug("params.search is not null")
            // returns as a String first time and as a list of strings the second time
            // may be fixed after always removing q from params now
            // we will leave the list join method is just in case
            // log.info("params.q is a ${params.q.getClass()}")
            // log.info("params.q = ${params.q}")


            def command = [
                    dateTo : new Date(),
                    keyword: params.keyword // was keyword: params.list('q').join()
            ]


            blogEntryList = BlogEntry.search().list {

                // allows for filtering on multiple properties when
                // delimited by a colon

                // for (String filterDef : params.search.split("[:]")) {

                // if using field_filterValue
                //    String field = filterDef.split('[_]')[0]
                //     String filterValue = filterDef.split('[_]')[1]
                //     println "filter $field = $filterValue"
                //    wildcard field, "*" + filterValue + "*"

                // }

                // we're using filter only and allowing multiple words
                // split command.keyword by spaces and add wildcards for each field
                if (command.keyword) {
                    should {
                        command.keyword.tokenize().each { keyword ->

                            def wild = keyword.toLowerCase() + '*'

                            wildcard "title", wild
                            wildcard "body", wild
                            wildcard "author", wild
                        }
                    }
                }


                if (SpringSecurityUtils.ifNotGranted('ROLE_ADMIN')) {

                    mustNot { keyword "published", false }
                }

                // sort "number", "asc"

                // maxResults page.max
                // offset page.offset
                maxResults params.max
                offset params.offset
            }

            // get the count
            blogEntryCount = BlogEntry.search().count {

                // we're using filter only and allowing multiple words
                // split command.keyword by spaces and add wildcards for each field
                if (command.keyword) {
                    should {
                        command.keyword.tokenize().each { keyword ->

                            def wild = keyword.toLowerCase() + '*'

                            wildcard "title", wild
                            wildcard "body", wild
                            wildcard "author", wild
                        }
                    }
                }

                if (SpringSecurityUtils.ifNotGranted('ROLE_ADMIN')) {

                    mustNot { keyword "published", false }
                }
            }


        } else {

            // copied and modified from list action
            def date = parseDateParams(params)
            def today = new Date()
            def author = params.author
            // def entries
            // def totalEntries
            if (author) {
                blogEntryList = BlogEntry.withCriteria {
                    eq 'author', author
                    eq 'published', true

                    if (date) {
                        if (params.year)
                            between 'dateCreated', date, today
                    }
                    order "dateCreated", "desc"
                    maxResults 10
                    cache true
                }
                blogEntryCount = BlogEntry.createCriteria().get {
                    projections { rowCount() }
                    eq 'author', author
                    eq 'published', true

                    if (date) {
                        if (params.year)
                            between 'dateCreated', date, today
                    }
                    cache true
                }

            } else {
                blogEntryList = findRecentEntries()
                blogEntryCount = BlogEntry.countByPublished(true, [cache: true])
            }

        }

        log.info "${blogEntryList.size()} results"

        log.info "params before respond ..."
        params.each { k, v ->
            log.info "${k} = ${v}"
        }

        renderListView blogEntryList, blogEntryCount
    }

    def byAuthor = {
        if (params.author) {
            def entries = BlogEntry.findAllByAuthor(params.author.trim(), [max: 10, offset: params.offset, sort: "dateCreated", order: "desc"])
            renderListView entries.findAll { it.published }, BlogEntry.countByAuthor(params.author.trim())
        } else {
            redirect action: "list"
        }
    }

    def byTag = {
        if (params.tag) {
            def entries = BlogEntry.findAllByTag(params.tag.trim(), [max: 10, offset: params.offset, sort: "dateCreated", order: "desc"])
            renderListView entries.findAll { it.published }, BlogEntry.countByTag(params.tag.trim())
        } else {
            redirect action: "list"
        }
    }
}