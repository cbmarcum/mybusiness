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

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional

/**
 * Controller class for ProductFeatureAppl
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ProductFeatureApplController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductFeatureAppl.list(params), model: [productFeatureApplCount: ProductFeatureAppl.count()]
    }

    def show(ProductFeatureAppl productFeatureAppl) {
        respond productFeatureAppl
    }

    def create() {
        respond new ProductFeatureAppl(params)
    }

    @Transactional
    def save(ProductFeatureAppl productFeatureAppl) {
        if (productFeatureAppl == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productFeatureAppl.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productFeatureAppl.errors, view: 'create'
            return
        }

        productFeatureAppl.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl'), productFeatureAppl.id])
                redirect productFeatureAppl
            }
            '*' { respond productFeatureAppl, [status: CREATED] }
        }
    }

    def edit(ProductFeatureAppl productFeatureAppl) {
        respond productFeatureAppl
    }

    @Transactional
    def update(ProductFeatureAppl productFeatureAppl) {
        if (productFeatureAppl == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productFeatureAppl.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productFeatureAppl.errors, view: 'edit'
            return
        }

        productFeatureAppl.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl'), productFeatureAppl.id])
                redirect productFeatureAppl
            }
            '*' { respond productFeatureAppl, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductFeatureAppl productFeatureAppl) {

        if (productFeatureAppl == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productFeatureAppl.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl'), productFeatureAppl.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
