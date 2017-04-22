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

/**
 * Controller class for ProductCategory
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ProductCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductCategory.list(params), model: [productCategoryCount: ProductCategory.count()]
    }

    def show(ProductCategory productCategory) {
        respond productCategory
    }

    def create() {
        respond new ProductCategory(params)
    }

    @Transactional
    def save(ProductCategory productCategory) {
        if (productCategory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productCategory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productCategory.errors, view: 'create'
            return
        }

        productCategory.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), productCategory.id])
                redirect productCategory
            }
            '*' { respond productCategory, [status: CREATED] }
        }
    }

    def edit(ProductCategory productCategory) {
        respond productCategory
    }

    @Transactional
    def update(ProductCategory productCategory) {
        if (productCategory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productCategory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productCategory.errors, view: 'edit'
            return
        }

        productCategory.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), productCategory.id])
                redirect productCategory
            }
            '*' { respond productCategory, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductCategory productCategory) {

        if (productCategory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productCategory.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), productCategory.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
