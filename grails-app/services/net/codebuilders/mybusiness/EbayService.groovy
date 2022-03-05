/* 
 * Copyright 2016-2020 Code Builders, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package net.codebuilders.mybusiness

import grails.core.*

class EbayService {

    GrailsApplication grailsApplication
    
    boolean transactional = false

    def findItemsInEbayStores(String storeName, String entriesPerPage, String pageNumber, String[] categories) {

        def base = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsIneBayStores&SERVICE-VERSION=1.0.0&"

        def qs = []
        qs << "SECURITY-APPNAME=${grailsApplication.config.mybusiness.ebay.security.appname}"
        
        qs << "RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD"
        qs << "storeName=" + URLEncoder.encode(storeName, "UTF-8")
        qs << "paginationInput.entriesPerPage=" + URLEncoder.encode(entriesPerPage, "UTF-8")
        qs << "paginationInput.pageNumber=" + URLEncoder.encode(pageNumber, "UTF-8")
        qs << "sortOrder=CurrentPriceHighest"
        // test for large pictures
        qs << "outputSelector=PictureURLLarge"
        categories.eachWithIndex() { obj, i -> qs << "categoryId(${i})=" + URLEncoder.encode(obj, "UTF-8") }
        
        

        def url = new URL(base + qs.join("&"))
        log.info(url.toString())
        def connection = url.openConnection()

        def result = [:]
        if(connection.responseCode == 200){
            def xml = connection.content.text
            log.info(xml)
            def response = new XmlSlurper().parseText(xml)
            log.info(print(response.toString()))
            // result.pageNumber = response.paginationOutput.pageNumber as String 
            // result.entriesPerPage = response.paginationOutput.entriesPerPage as String
            result.totalPages = response.paginationOutput.totalPages as String
            result.totalEntries = response.paginationOutput.totalEntries as String
            log.info("totalPages = ${result.totalPages}")
            log.info("totalEntries = ${result.totalEntries}")
            // test returning items also
            result.items = response.searchResult.item
            log.info(result.items[0].toString())
            
            for ( item in result.items ) {
              log.info(item.title.toString(), "UTF-8")
            }
            
        }
        
        else{
            log.error("EbayService.findItemsInEbayStores FAILED")
            log.error(url.toString())
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }      
        return result
    }
    
    
    def searchItemsInEbayStores(String storeName, String entriesPerPage, String pageNumber, String keywords, String[] categories) {

        def base = "https://svcs.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsIneBayStores&SERVICE-VERSION=1.0.0&"
        
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
        categories.eachWithIndex() { obj, i -> qs << "categoryId(${i})=" + URLEncoder.encode(obj) }
        
        def url = new URL(base + qs.join("&"))
        log.info(url.toString())
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
            log.info(result.items[0].toString())
            
            for ( item in result.items ) {
              log.info(item.title.toString())
            }
            
        }
        
        else{
            log.error("EbayService.findItemsInEbayStores FAILED")
            log.error(url.toString())
            log.error(connection.responseCode)
            log.error(connection.responseMessage)
        }      
        return result
    }
        
}

