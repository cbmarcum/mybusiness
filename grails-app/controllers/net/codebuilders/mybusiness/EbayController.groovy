package net.codebuilders.mybusiness

// import org.springframework.dao.DataIntegrityViolationException
// import org.compass.core.engine.SearchEngineQueryParseException
// import pl.burningice.plugins.image.ImageUploadService
// import com.metasieve.shoppingcart.ShoppingCartService
// import org.apache.shiro.SecurityUtils
import grails.converters.JSON

class EbayController {

    def ebayService
    
    def products = []

    // test passing categories instead of separate methods
    def store() {
        log.info("entering store action")
        params.returnAction = params?.returnAction ?: "store"
        log.info("params.returnAction = ${params.returnAction}")

        params.storeName = params?.storeName ?: grailsApplication.config.mybusiness.ebay.storeName
        log.info("params.storeName = ${params.storeName}")

        params.categories = params?.categories ?: [] as String[]
        log.info("params.categories = ${params.categories}")

        log.info("chaining to find")
        chain(action: "find", params: params)
    }

    
    def find() {
        log.debug("entering find action")
        
        params.entriesPerPage = params?.entriesPerPage ?: 50
        params.pageNumber = params?.pageNumber ?: 1

        def result = ebayService.findItemsInEbayStores(params.storeName, params.entriesPerPage.toString(), params.pageNumber.toString(), params.categories)
        params.totalPages = result?.totalPages
        params.totalEntries = result?.totalEntries
        
        products = result?.items
        
        chain(action: "list", params: params, model: [items: products])
    }
    
    def list() {
        log.info("entering list action")
        // since all categories and search use the list action we need to
        // check for page reloads the direct back to list and send them
        // thru the correct action first
        // if list was properly chained to, there will be a chainModel
        // if there was a reload from a search, there will be keywords
        
        if (chainModel != null) {
            log.info("chainModel is not null")
            products = chainModel.items
        } else if (params.keywords) {
            log.info("maybe a page reload from search")
            log.info("has a params.keywords = ${params.keywords}")
            log.info("chaining to search")
            chain(action: "search", params: params)
        } else {
            log.info("maybe a page reload of plain list")
            log.info("chaining to ${params.returnAction}")
            chain(action: "${params.returnAction}", params: params)
        }
        
        log.info("products.size = ${products.size()}")
        log.info("params = ${params}")
        if (products) {
            log.info("products[0].title = ${products[0].title}")
        } else {
            log.info("no products")
        }
        
        // MAYBE WE NEED TO EXPLICITLY RENDER OR PASS PRODUCTS
        [params: params, model: [items: products]]
    }
    
    def search() {
        log.info("entering search action")

        // check for page reloads (q is null) and empty search (q == "")
        
        if (params.q != null) {
            log.debug("params.q is not null")
            // returns as a String first time and as a list of strings the second time
            // may be fixed after always removing q from params now
            // we will leave the list join method is just in case
            log.info("params.q is a ${params.q.getClass()}")
            log.info("params.q = ${params.q}")
            
            // if q is "", we don't need to do an empty search
            // chain back to reqular method
            if (params.q == "") {
                log.info("params.q string is empty")
                
                log.info("removing q and keywords ...")
                params.remove('q')
                params.remove('keywords')
                log.info("chaining to ${params.returnAction}")
                chain(action: "${params.returnAction}", params: params)
            }
            
            // grails can get a list returned from any param
            // turn it into a string again
            // may be fixed after always removing q from params now
            // we will leave the list join method just in case
            params.keywords = params.list('q').join()
            params.remove('q')
            
        } else { // params.q is null - may be a reload
            log.info("params.q is null .. may have been a page reload")
            log.info("leaving keywords alone")
        }
        
        log.info("params.keywords is now a ${params.keywords.getClass()}")
        log.info("params.keywords = ${params.keywords}")

        log.info("params.returnAction = ${params.returnAction}")
        log.info("params.storeName = ${params.storeName}")
        log.info("params.keywords = ${params.keywords}")
        def result = ebayService.searchItemsInEbayStores(params.storeName, params.entriesPerPage.toString(), params.pageNumber.toString(), params.keywords ,params.categories)
        
        params.totalPages = result?.totalPages
        params.totalEntries = result?.totalEntries
        
        products = result?.items
        
        chain(action: "list", params: params, model: [items: products])
    }

    

    def ebayResult = {
        def result = ebayService.findItemsInEbayStores(params.storeName, params.entriesPerPage, params.pageNumber)
        render result as JSON
    }  
    
}
