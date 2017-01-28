package net.codebuilders.mybusiness

import grails.util.*

import org.grails.taggable.*

class TaggableService {
    
    def grailsApplication
    
    def domainClassFamilies = [:]
    
    def getTagCounts(type) {
        def tagCounts = [:]
        TagLink.withCriteria {
            eq('type', type)
            projections {
                groupProperty('tag')
                count('tagRef')
            }
        }.each {
            def tagName = it[0].name
            def count = it[1]
            tagCounts[tagName] = tagCounts[tagName] ? (tagCounts[tagName] + count) : count
        }
        return tagCounts
    }
    
    /**
     * Update the graph of known subclasses
     *
     * Example:
     * [
     *  WcmContent: [
     *      WcmBlog,
     *      WcmHTMLContent,
     *      WcmComment
     *   ]
     *  WcmBlog: [],
     *  WcmHTMLContent: [WcmRichContent],
     *  WcmRichContent: [],
     *  WcmStatus: []
     * ]
     */
    def refreshDomainClasses() {
        grailsApplication.domainClasses.each { artefact ->
            if( Taggable.class.isAssignableFrom(artefact.clazz)) {
                domainClassFamilies[artefact.clazz.name] = [GrailsNameUtils.getPropertyName(artefact.clazz)]
                // Add class and all subclasses 
                domainClassFamilies[artefact.clazz.name].addAll(artefact.subClasses.collect { GrailsNameUtils.getPropertyName(it.clazz) })
            }
        }
    }
}
