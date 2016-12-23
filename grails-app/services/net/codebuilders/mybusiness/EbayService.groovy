package net.codebuilders.mybusiness

import grails.core.*

class EbayService {

    GrailsApplication grailsApplication
    
    boolean transactional = false

    def findItemsInEbayStores(String storeName, String entriesPerPage, String pageNumber, String[] categories) {

        def base = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsIneBayStores&SERVICE-VERSION=1.0.0&"

        def qs = []
        qs << "SECURITY-APPNAME=${grailsApplication.config.mybusiness.ebay.security.appname}"
        
        qs << "RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD"
        qs << "storeName=" + URLEncoder.encode(storeName)
        qs << "paginationInput.entriesPerPage=" + URLEncoder.encode(entriesPerPage)
        qs << "paginationInput.pageNumber=" + URLEncoder.encode(pageNumber)
        qs << "sortOrder=CurrentPriceHighest"
        // test for large pictures
        qs << "outputSelector=PictureURLLarge"
        categories.eachWithIndex() { obj, i -> qs << "categoryId[${i}]=" + URLEncoder.encode(obj) };
        
        

        def url = new URL(base + qs.join("&"))
        log.info(url)
        def connection = url.openConnection()

        def result = [:]
        if(connection.responseCode == 200){
            def xml = connection.content.text
            log.info(xml)
            def response = new XmlSlurper().parseText(xml)
            log.info(print(response))
            // result.pageNumber = response.paginationOutput.pageNumber as String 
            // result.entriesPerPage = response.paginationOutput.entriesPerPage as String
            result.totalPages = response.paginationOutput.totalPages as String
            result.totalEntries = response.paginationOutput.totalEntries as String
            log.info("totalPages = ${result.totalPages}")
            log.info("totalEntries = ${result.totalEntries}")
            // test returning items also
            result.items = response.searchResult.item
            log.info(result.items[0])
            
            for ( item in result.items ) {
              log.info(item.title)
            }
            
        }
        
        else{
            log.error("EbayService.findItemsInEbayStores FAILED")
            log.error(url)
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }      
        return result
    }
    
    
    def searchItemsInEbayStores(String storeName, String entriesPerPage, String pageNumber, String keywords, String[] categories) {

        def base = "http://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsIneBayStores&SERVICE-VERSION=1.0.0&"
        
        def qs = []
        qs << "SECURITY-APPNAME=${grailsApplication.config.mybusiness.ebay.security.appname}"
        
        qs << "RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD"
        qs << "storeName=" + URLEncoder.encode(storeName)
        qs << "paginationInput.entriesPerPage=" + URLEncoder.encode(entriesPerPage)
        qs << "paginationInput.pageNumber=" + URLEncoder.encode(pageNumber)
        qs << "sortOrder=CurrentPriceHighest"
        // test for large pictures
        qs << "outputSelector=PictureURLLarge"
        qs << "keywords=" + URLEncoder.encode(keywords)
        categories.eachWithIndex() { obj, i -> qs << "categoryId[${i}]=" + URLEncoder.encode(obj) };
        
        def url = new URL(base + qs.join("&"))
        log.info(url)
        def connection = url.openConnection()

        def result = [:]
        if(connection.responseCode == 200){
            def xml = connection.content.text
            log.info(xml)
            def response = new XmlSlurper().parseText(xml)
            log.info(print(response))
            // result.pageNumber = response.paginationOutput.pageNumber as String 
            // result.entriesPerPage = response.paginationOutput.entriesPerPage as String
            result.totalPages = response.paginationOutput.totalPages as String
            result.totalEntries = response.paginationOutput.totalEntries as String
            log.info("totalPages = ${result.totalPages}")
            log.info("totalEntries = ${result.totalEntries}")
            // test returning items also
            result.items = response.searchResult.item
            log.info(result.items[0])
            
            for ( item in result.items ) {
              log.info(item.title)
            }
            
        }
        
        else{
            log.error("EbayService.findItemsInEbayStores FAILED")
            log.error(url)
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }      
        return result
    }
        
}

