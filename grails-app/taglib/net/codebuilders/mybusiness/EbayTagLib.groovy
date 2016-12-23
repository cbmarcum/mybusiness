package net.codebuilders.mybusiness

import org.springframework.web.servlet.support.RequestContextUtils as RCU

class EbayTagLib {

    static namespace = 'cb' // <cb:ebayPaginate>

    /*
     * 
    total (required) - The total number of results to paginate
    action (optional) - the name of the action to use in the link, if not specified the default action will be linked
    controller (optional) - the name of the controller to use in the link, if not specified the current controller will be linked
    id (optional) - The id to use in the link
    params (optional) - A map containing request parameters
    prev (optional) - The text to display for the previous link (defaults to "Previous" as defined by default.paginate.prev property in I18n messages.properties)
    next (optional) - The text to display for the next link (defaults to "Next" as defined by default.paginate.next property in I18n messages.properties)
    max (optional) - The number of records displayed per page (defaults to 10). Used ONLY if params.max is empty
    maxsteps (optional) - The number of steps displayed for pagination (defaults to 10). Used ONLY if params.maxsteps is empty
    offset (optional) - Used ONLY if params.offset is empty
     *
     *
     * Similar to g:paginate which uses an offset for database list will use
     * pageNumber for ebay call
     */
    
    def ebayPaginate = { attrs ->
        def writer = out
        if (attrs.total == null) {
            throwTagError("Tag [paginate] is missing required attribute [total]")
        }

        def messageSource = grailsAttributes.messageSource 
        def locale = RCU.getLocale(request)

        def total = attrs.int('total') ?: 0 
        // def action = (attrs.action ? attrs.action : (params.returnAction ? params.returnAction : "list")) 
        def action = params?.returnAction
        def offset = params.int('offset') ?: 0 
        // def max = params.int('max') 
        def max = params.int('entriesPerPage') 
        def maxsteps = (attrs.int('maxsteps') ?: 10)
        
        // added
        def pageNumber = (params.int('pageNumber') ?: 1)
        def totalPages = (params.int('totalPages'))

        if (!offset) offset = (attrs.int('offset') ?: 0) 
        if (!max) max = (attrs.int('max') ?: 10)

        def linkParams = [:] 
        if (attrs.params) linkParams.putAll(attrs.params) 
        linkParams.offset = offset - max 
        linkParams.max = max 
        // added
        linkParams.pageNumber = pageNumber
        
        if (params.sort) linkParams.sort = params.sort 
        if (params.order) linkParams.order = params.order

        // added for mybusiness plugin way of passing categories and category name
        if (params.categories) linkParams.categories = params.categories
        if (params.categoryName) linkParams.categoryName = params.categoryName
        if (params.storeName) linkParams.storeName = params.storeName


        def linkTagAttrs = [action:action] 
        if (attrs.controller) { 
            linkTagAttrs.controller = attrs.controller 
        } 
        if (attrs.id != null) { 
            linkTagAttrs.id = attrs.id 
        }
        if (attrs.fragment != null) { 
            linkTagAttrs.fragment = attrs.fragment 
        }
        linkTagAttrs.params = linkParams

        // determine paging variables 
        def steps = maxsteps > 0 
        // int currentstep = (offset / max) + 1 
        int currentstep = pageNumber
        int firststep = 1 
        // int laststep = Math.round(Math.ceil(total / max))
        int laststep = totalPages

        // display previous link when not on firststep 
        def disabledPrev = (currentstep > firststep) ? "" : "disabled"
        // linkTagAttrs.class = 'prevLink' 
        // linkParams.offset = offset - max
        // added
        linkParams.pageNumber = pageNumber - 1

        writer << "<ul class='pagination'>"
        writer << "<li class='prev ${disabledPrev}'>"
        writer << link(linkTagAttrs.clone()) { 
            (attrs.prev ?: messageSource.getMessage('paginate.prev', null, 
                    messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale)) 
        } 
        writer << "</li>"
        

        // display steps when steps are enabled and laststep is not firststep 
        if (steps && laststep > firststep) { 
            linkTagAttrs.class = 'step'

            // determine begin and endstep paging variables 
            int beginstep = currentstep - Math.round(maxsteps / 2) + (maxsteps % 2) 
            int endstep = currentstep + Math.round(maxsteps / 2) - 1

            if (beginstep < firststep) { 
                beginstep = firststep 
                endstep = maxsteps 
            }
            if (endstep > laststep) { 
                beginstep = laststep - maxsteps + 1 
                if (beginstep < firststep) { 
                    beginstep = firststep 
                } 
                endstep = laststep 
            }

            // display firststep link when beginstep is not firststep 
            if (beginstep > firststep) { 
                linkParams.offset = 0
                // added
                linkParams.pageNumber = 1
                writer << "<li>"
                writer << link(linkTagAttrs.clone()) {firststep.toString()} 
                writer << "</li>"
                writer << '<li class="disabled"><a href="#">…</a></li>'
                // writer << '<span class="step">..</span>' 
            }

            // display paginate steps 
            (beginstep..endstep).each { i -> 
                if (currentstep == i) { 
                    // writer << "<span class=\"currentStep\">${i}</span>" 
                    writer << "<li class='active'><a href='#'>"+i.toString()+"</a></li>"
                    
                } 
                else { 
                    linkParams.offset = (i - 1) * max
                    // added
                    linkParams.pageNumber = i
                    writer << "<li>"
                    writer << link(linkTagAttrs.clone()) {i.toString()} 
                    writer << "</li>"
                } 
            }

            // display laststep link when endstep is not laststep 
            if (endstep < laststep) { 
                // writer << '<span class="step">..</span>' 
                linkParams.offset = (laststep -1) * max 
                // added
                linkParams.pageNumber = totalPages
                writer << '<li class="disabled"><a href="#">…</a></li>'
                writer << "<li>"
                writer << link(linkTagAttrs.clone()) { laststep.toString() } 
                writer << "</li>"
            } 
        }

        // display next link when not on laststep 
            def disabledNext = (currentstep < laststep) ? "" : "disabled"
            // linkTagAttrs.class = 'nextLink' 
            linkParams.offset = offset + max 
            // added
            linkParams.pageNumber = pageNumber + 1
            writer << "<li class='next ${disabledNext}'>"
            writer << link(linkTagAttrs.clone()) { 
                (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, 
                        messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale)) 
            } 
            writer << "</li>"
            writer << "</ul>"
        

    }

}
