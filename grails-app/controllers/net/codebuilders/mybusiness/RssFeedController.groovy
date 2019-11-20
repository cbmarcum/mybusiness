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

@Transactional(readOnly = true)
class RssFeedController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RssFeed.list(params), model: [rssFeedCount: RssFeed.count()]
    }

    def show(RssFeed rssFeed) {
        respond rssFeed
    }

    def create() {
        respond new RssFeed(params)
    }

    @Transactional
    def save(RssFeed rssFeed) {
        if (rssFeed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rssFeed.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rssFeed.errors, view: 'create'
            return
        }

        rssFeed.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rssFeed.label', default: 'RssFeed'), rssFeed.id])
                redirect rssFeed
            }
            '*' { respond rssFeed, [status: CREATED] }
        }
    }

    def edit(RssFeed rssFeed) {
        respond rssFeed
    }

    @Transactional
    def update(RssFeed rssFeed) {
        if (rssFeed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rssFeed.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rssFeed.errors, view: 'edit'
            return
        }

        rssFeed.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rssFeed.label', default: 'RssFeed'), rssFeed.id])
                redirect rssFeed
            }
            '*' { respond rssFeed, [status: OK] }
        }
    }

    @Transactional
    def delete(RssFeed rssFeed) {

        if (rssFeed == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rssFeed.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rssFeed.label', default: 'RssFeed'), rssFeed.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rssFeed.label', default: 'RssFeed'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
