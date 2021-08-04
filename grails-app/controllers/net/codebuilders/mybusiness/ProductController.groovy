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

import javax.sql.rowset.spi.TransactionalWriter
import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional
import grails.core.GrailsApplication
import grails.plugin.springsecurity.SpringSecurityUtils
import net.codebuilders.mybusiness.ProductCategory


/**
 * Controller class for Product
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ProductController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ProductService productService
    ProductFeatureApplService productFeatureApplService
    ShoppingCartService shoppingCartService
    def springSecurityService
    NoticeService noticeService
    ProductCategoryService productCategoryService

    def index(Integer max) {

        /* before search
        params.max = Math.min(max ?: 10, 100)
        if (params.category) {
        def products = productService.getProductsByProdCatAndDisplay(params)
        respond products, model: [productCount: products.size()]
        } else {
        respond Product.list(params), model: [productCount: Product.count()]
        }
         */

        // removed search

        params.max = Math.min(max ?: 10, 100)

        List<Product> productList
        Integer productCount = 0

        if (params.category) {
            productList = productService.getProductsByProdCatAndDisplay(params)
        } else {
            productList = Product.list(params)
        }
        productCount = productList.size()

        log.info "${productList.size()} results"

        log.info "params before respond ..."
        params.each { k, v ->
            log.info "${k} = ${v}"
        }

        // for debugging missing photos
        /*
        productList.each {
        log.info "${it.number} ${it.name}"
        // log.info "photos[0].photo.name = ${it.photos[0].photo.name}"
        if (it.photos) {
        log.info "has ${it.photos.size()} photos."
        } else {
        log.info "has no photos."
        }
        }
         */
       
        log.info("params.category= ${params.category}")
        def productCategory = params.category ? ProductCategory.findById(params.category)?.description : "All Products"
        log.info("productCategory = ${productCategory}")

        def notices = noticeService.getCurrentNoticesByPage("Product")
        
        // used for no-image if a product is missing one.
        def noImage = Photo.findByName("no-image")?.photo?.getCloudFile("large")
        
        // for a params map without params.format
        Map filterParams = params.clone()
        filterParams.remove('format')

        // render(view:'index', model: [message: 'Hello world', result: result, fieldsList: indexedProperties.keySet()])

        respond productList, model: [productCategory: productCategory, productCount: productCount, noticeList: notices, noImage: noImage, filterParams: filterParams]

    }

    // used by admins only
    def show(Product product) {
        respond product
    }

    // like show but formatted for shoppers
    def detail(Product product) {
        // def productInstance = Product.get(params.id)
        if (!product) { // was productInstance
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
            redirect(action: "list")
            return
        }

        Map variantMap = [:]
        variantMap = productService.getVariantMapByProduct(product)

        [product: product, variantMap: variantMap]
    }

    def create() {
        respond new Product(params)
    }

    @Transactional
    def save(Product product) {
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond product.errors, view: 'create'
            return
        }

        // make sure we have all the ancestor categories
        List<ProductCategory> tempList = product.productCategories
        product.productCategories = productCategoryService.getRelatedCategories(tempList)

        product.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect product
            }
            '*' { respond product, [status: CREATED] }
        }
    }

    def edit(Product product) {
        respond product
    }

    @Transactional
    def update(Product product) {
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond product.errors, view: 'edit'
            return
        }

        // make sure we have all the ancestor categories
        List<ProductCategory> tempList = product.productCategories
        product.productCategories = productCategoryService.getRelatedCategories(tempList)

        product.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect product
            }
            '*' { respond product, [status: OK] }
        }
    }

    @Transactional
    def delete(Product product) {

        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        // since we're not using belongsTo because the database is established
        // we'll delete the join table references manually.
        
        product.goodIdentifications.each { gid -> 
            log.info("deleting goodId ${gid.toString()}")
            gid.delete()
        }
        product.productFeatureAppls.each { pfa -> 
            log.info("deleting pfa ${pfa.toString()}")
            pfa.delete()
        }
        product.save()

        product.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def ajaxUpdateCartQty() {
        
        log.info "entered ajaxUpdateCartQty"
        def cartQty = shoppingCartService.getItems().size()
        log.debug "qty=${cartQty}"
        render "${cartQty}"
    }
    
    def ajaxUpdateQuickCartContent() {

        log.info "entered ajaxUpdateQuickCartContent"
        render(template: '/shoppingCart/quickCartContent')
    }

    @Transactional
    def removePhoto() {
        log.info "entered removePhoto action"
        params.each { it ->
            log.info("${it.key} = ${it.value}")
        }
        Product product = Product.get(params.id)
        log.info(product.toString())
        Photo photo = Photo.get(params.photo)
        log.info(photo.toString())

        if (product.photos.contains(photo)) {
            log.info "found photo"
            // product.photos.remove(photo)
            product.removeFromPhotos(photo)
        } else {
            log.info "no photo found"
        }

        redirect(action: 'show', id: params.id)
    }

    @Transactional
    def uploadPhoto() {
        log.info "entered uploadPhoto action"
        params.each { it ->
            log.info("${it.key} = ${it.value}")
        }
        def photo = new Photo(params)
        if(!photo.save()) {
            println "Error Saving! ${photo.errors.allErrors}"
        } else {
            Product product = Product.get(params.id)
            log.info(product.toString())
            product.addToPhotos(photo)
        }


        // redirect(view: "show"
        redirect(action: 'show', id: params.id)
    }

    @Transactional
    def attachPhoto() {
        log.info "entered attachPhoto action"
        params.each { it ->
            log.info("${it.key} = ${it.value}")
        }
        Product product = Product.get(params.id)
        log.info(product.toString())
        Photo photo = Photo.get(params.photo)
        log.info(photo.toString())

        product.addToPhotos(photo)

        if (!product.save()) {
            println "Error Saving! ${product.errors.allErrors}"
        } else {
            log.info "photo attached"
        }

        redirect(action: 'show', id: params.id)
    }

}
