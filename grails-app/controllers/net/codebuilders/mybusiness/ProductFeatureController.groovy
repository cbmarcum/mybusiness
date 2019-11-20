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
 * Controller class for ProductFeature
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ProductFeatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductFeature.list(params), model: [productFeatureCount: ProductFeature.count()]
    }

    def show(ProductFeature productFeature) {
        respond productFeature
    }

    def create() {
        respond new ProductFeature(params)
    }

    @Transactional
    def save(ProductFeature productFeature) {
        if (productFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productFeature.errors, view: 'create'
            return
        }

        productFeature.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productFeature.label', default: 'ProductFeature'), productFeature.id])
                redirect productFeature
            }
            '*' { respond productFeature, [status: CREATED] }
        }
    }

    def edit(ProductFeature productFeature) {
        respond productFeature
    }

    @Transactional
    def update(ProductFeature productFeature) {
        if (productFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productFeature.errors, view: 'edit'
            return
        }

        productFeature.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productFeature.label', default: 'ProductFeature'), productFeature.id])
                redirect productFeature
            }
            '*' { respond productFeature, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductFeature productFeature) {

        if (productFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productFeature.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productFeature.label', default: 'ProductFeature'), productFeature.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productFeature.label', default: 'ProductFeature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
