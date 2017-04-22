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
 * Controller class for GoodIdentification
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class GoodIdentificationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GoodIdentification.list(params), model: [goodIdentificationCount: GoodIdentification.count()]
    }

    def show(GoodIdentification goodIdentification) {
        respond goodIdentification
    }

    def create() {
        respond new GoodIdentification(params)
    }

    @Transactional
    def save(GoodIdentification goodIdentification) {
        if (goodIdentification == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (goodIdentification.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond goodIdentification.errors, view: 'create'
            return
        }

        goodIdentification.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'goodIdentification.label', default: 'GoodIdentification'), goodIdentification.id])
                redirect goodIdentification
            }
            '*' { respond goodIdentification, [status: CREATED] }
        }
    }

    def edit(GoodIdentification goodIdentification) {
        respond goodIdentification
    }

    @Transactional
    def update(GoodIdentification goodIdentification) {
        if (goodIdentification == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (goodIdentification.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond goodIdentification.errors, view: 'edit'
            return
        }

        goodIdentification.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'goodIdentification.label', default: 'GoodIdentification'), goodIdentification.id])
                redirect goodIdentification
            }
            '*' { respond goodIdentification, [status: OK] }
        }
    }

    @Transactional
    def delete(GoodIdentification goodIdentification) {

        if (goodIdentification == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        goodIdentification.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'goodIdentification.label', default: 'GoodIdentification'), goodIdentification.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodIdentification.label', default: 'GoodIdentification'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
