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
    
    
    def index() {
        log.info("entering index action")

        params.storeName = params?.storeName ?: grailsApplication.config.mybusiness.ebay.storeName
        log.info("params.storeName = ${params.storeName}")

        params.categories = params?.categories ?: [] as String[]
        log.info("params.categories = ${params.categories}")
        
        
        
        if (params.q != null) {
            
            log.info "there was a search ..."

            // since a search returns all params, remove the ones we don't need
            params.remove('keywords')
            params.remove('max')
            params.remove('offset')

            // check for empty search and ignore
            if (params.q.trim() != "") {
                log.info "there was a search and it was not blank"
                log.info "setting keyword"
                params.keywords = params.q.trim()

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
        
        params.max = Math.min(params.max ? params.int('max') : 50, 100) 
        params.offset = params.offset ? params.int('offset') : 0
        
        if (params.keywords) {

            log.info "entered keywords ..."

            log.info "params at the beginning of keywords"
            params.each { k, v ->
                log.info "${k} = ${v}"
            }
            
            // do search
            params.entriesPerPage = params?.entriesPerPage ?: params.max
            
            if (params.offset == 0) {
                params.pageNumber = 1
            } else {
                params.pageNumber = (params.offset / params.max).intValue() + 1 // BigDecimal to Integer
            }
            def result = ebayService.searchItemsInEbayStores(params.storeName, params.entriesPerPage.toString(), params.pageNumber.toString(), params.keywords ,params.categories)
        
            params.totalPages = result?.totalPages
            params.totalEntries = result?.totalEntries
        
            products = result?.items
            
        } else { // no search, just list
            
            // get all
            params.entriesPerPage = params?.entriesPerPage ?: params.max
            if (params.offset == 0) {
                params.pageNumber = 1
            } else {
                params.pageNumber = (params.offset / params.max).intValue() + 1 // BigDecimal to Integer
            }

            def result = ebayService.findItemsInEbayStores(params.storeName, params.entriesPerPage.toString(), params.pageNumber.toString(), params.categories)
            params.totalPages = result?.totalPages
            params.totalEntries = result?.totalEntries
        
            products = result?.items
            
        }
        
        log.info("products.size = ${products.size()}")
        log.info("params = ${params}")
        if (products) {
            log.info("products[0].title = ${products[0].title}")
        } else {
            log.info("no products")
        }
        
        respond([items: products])
        
        
    } // end index


    def ebayResult = {
        def result = ebayService.findItemsInEbayStores(params.storeName, params.entriesPerPage, params.pageNumber)
        render result as JSON
    }  
    
}
