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
 * Controller class for Photos
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class PhotoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Photo.list(params), model:[photoCount: Photo.count()]
    }

    def show(Photo photo) {
        respond photo
    }

    // not yet known why create and edit render empty pages
    // workaround is a redirect for now
    def create() {
        redirect(action: 'createPhoto', params: params)
    }

    def createPhoto() {
        respond new Photo(params)
    }

    @Transactional
    def save(Photo photo) {
        if (photo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (photo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond photo.errors, view:'create'
            return
        }

        photo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'photo.label', default: 'Photo'), photo.id])
                redirect photo
            }
            '*' { respond photo, [status: CREATED] }
        }
    }

    // not yet known why create and edit render empty pages
    // workaround is a redirect for now
    def edit(Photo photo) {
        redirect(action: 'editPhoto', id: params.id)
    }

    def editPhoto(Photo photo) {
        respond photo
    }

    @Transactional
    def update(Photo photo) {
        if (photo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (photo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond photo.errors, view:'edit'
            return
        }

        photo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'photo.label', default: 'Photo'), photo.id])
                redirect photo
            }
            '*'{ respond photo, [status: OK] }
        }
    }

    @Transactional
    def delete(Photo photo) {

        if (photo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        photo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'photo.label', default: 'Photo'), photo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'photo.label', default: 'Photo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    // added to test selfie
    def upload() {
        def photo = new Photo(params)
        if(!photo.save()) {
            println "Error Saving! ${photo.errors.allErrors}"
        }
        redirect view: "index"
    }

}
