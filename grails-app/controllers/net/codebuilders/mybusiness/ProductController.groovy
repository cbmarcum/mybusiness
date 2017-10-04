/*
 * *************************************************************
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * *************************************************************
 */

package net.codebuilders.mybusiness

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

// for hibernate search
import org.grails.orm.hibernate.HibernateMappingContextSessionFactoryBean
import org.hibernate.search.cfg.EntityDescriptor
import org.hibernate.search.cfg.PropertyDescriptor

import grails.core.GrailsApplication
import grails.plugins.hibernate.search.HibernateSearchGrailsPlugin


/**
 * Controller class for Product
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ProductController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ProductService productService
    def productFeatureApplService
    ShoppingCartService shoppingCartService

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

        // for search
        def page = [max   : Math.min(params.max ? params.int('max') : 10, 50),
                    offset: params.offset ? params.int('offset') : 0]

        List<Product> productList

        if (params.search) {

            def command = [
                    dateTo : new Date(),
                    keyword: params.search
            ]

            productList = Product.search().list {

                // allows for filtering on multiple properties when
                // delimited by a colon
                /*
                for (String filterDef : params.search.split("[:]")) {

                    // if using field_filterValue
                    String field = filterDef.split('[_]')[0]
                    String filterValue = filterDef.split('[_]')[1]
                    println "filter $field = $filterValue"
                    wildcard field, "*" + filterValue + "*"

                }
                */

                // we're using filter only and allowing multiple words
                // split command.keyword by spaces and add wildcards for each field
                if (command.keyword) {
                    should {
                        command.keyword.tokenize().each { keyword ->

                            def wild = keyword.toLowerCase() + '*'

                            wildcard "number", wild
                            wildcard "name", wild
                            wildcard "shortDescription", wild
                            wildcard "longDescription", wild
                            wildcard "largeDescription", wild
                            wildcard "goodIdentifications.value", wild
                            wildcard "productCategories.description", wild
                        }
                    }
                }

                if (command.dateTo) {
                    above "salesDiscontinuationDate", command.dateTo
                }

                mustNot { keyword "display", false }

                // sort "number", "asc"

                maxResults page.max
                offset page.offset
            }

        } else {

            def command = [
                    dateTo: new Date()
            ]

            productList = Product.search().list {

                wildcard "number", "*"

                if ( command.dateTo ) {
                    above "salesDiscontinuationDate", command.dateTo
                }

                mustNot { keyword "display", false }

                // sort "number", "asc"

                maxResults page.max
                offset page.offset
            }
        }

        log.info "${productList.size()} results"

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

        // render(view:'index', model: [message: 'Hello world', result: result, fieldsList: indexedProperties.keySet()])

        respond productList, model: [productCount: productList.size()]

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

// def pmaResults = productMovieApplService.getPmaByProduct(product) // was productInstance
        def pfaResults = productFeatureApplService.getPfaByProduct(product) // was productInstance

        [product: product, pfaResults: pfaResults] // was productInstance
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
// TODO: remove println
        println "entered ajaxUpdateCartQty"
        log.info "entered ajaxUpdateCartQty"
        def cartQty = shoppingCartService.getItems().size()
        println "qty=${cartQty}"
        log.debug "qty=${cartQty}"
        render "${cartQty}"
    }

}
