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
 * Controller class for ImportSheet
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ImportSheetController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    ImportSheetService importSheetService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ImportSheet.list(params), model: [importSheetCount: ImportSheet.count()]
    }

    def show(ImportSheet importSheet) {
        respond importSheet
    }

    def create() {
        respond new ImportSheet(params)
    }

    @Transactional
    def save(ImportSheet importSheet) {
        if (importSheet == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (importSheet.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond importSheet.errors, view: 'create'
            return
        }

        importSheet.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'importSheet.label', default: 'ImportSheet'), importSheet.id])
                redirect importSheet
            }
            '*' { respond importSheet, [status: CREATED] }
        }
    }

    def edit(ImportSheet importSheet) {
        respond importSheet
    }

    @Transactional
    def update(ImportSheet importSheet) {
        if (importSheet == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (importSheet.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond importSheet.errors, view: 'edit'
            return
        }

        importSheet.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'importSheet.label', default: 'ImportSheet'), importSheet.id])
                redirect importSheet
            }
            '*' { respond importSheet, [status: OK] }
        }
    }

    @Transactional
    def delete(ImportSheet importSheet) {

        if (importSheet == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        importSheet.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'importSheet.label', default: 'ImportSheet'), importSheet.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'importSheet.label', default: 'ImportSheet'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    // added for selfie plugin
    def upload() {
        def sheet = new ImportSheet(params)
        if (!sheet.save()) {
            println "Error Saving! ${sheet.errors.allErrors}"
        }
        redirect view: "index"
    }

    // add process sheet action
    // call ImportSheetService and return status type
    // return an ajax update of the status
    // or
    //
    @Transactional
    def processSheet(ImportSheet importSheet) {
        println "Original filename: ${importSheet.sheet.originalFilename}"
        println "Filename: ${importSheet.sheet.fileName}"
        println "Content Type: ${importSheet.sheet.contentType}"
        // returns
        /*
        Original filename: null
        Filename: mpp-export-sample.ods
        Content Type: application/octet-stream
         */

        log.info("Testing a save...")
        importSheet.save(flush: true)

        // set tp processing
        // importSheet.importSheetStatusType = ImportSheetStatusType.PROCESSING
        // importSheet.save(flush: true)

        if (importSheet.sheet.fileName.endsWith(".ods")) {
            println "we have a Calc file"
            log.info("we have a Calc file")

            importSheet.importSheetStatusType = importSheetService.processCalc(importSheet)

            log.info("status is ${importSheet.importSheetStatusType.toString()}")

            if (!importSheet.save(flush: true)) {
                println "Error Saving! ${importSheet.errors.allErrors}"
            }

        } else if (importSheet.sheet.fileName.endsWith(".xls")) {
            println "we have an Excel file"
            importSheet.importSheetStatusType = importSheetService.processExcel(importSheet)
            importSheet.save(flush: true)
        } else if (importSheet.sheet.fileName.endsWith(".xlsx")) {
            println "we have an Excel XML file"
            importSheet.importSheetStatusType = importSheetService.processExcel(importSheet)
            importSheet.save(flush: true)
        } else {
            println "no filename match"
            importSheet.importSheetStatusType = ImportSheetStatusType.FAILED
            importSheet.save(flush: true)
        }


        /*
        switch (importSheet.sheet.fileName) {
            case ~/.*\.odt$/:
                println "we have a Calc file"
                importSheet.importSheetStatusType = importSheetService.processCalc(importSheet)
                break
            case ~/.*\.xls$/:
                println "we have an Excel file"
                importSheet.importSheetStatusType = importSheetService.processExcel(importSheet)
                break
            case ~/.*\.xlsx$/:
                println "we have an Excel XML file"
                importSheet.importSheetStatusType = importSheetService.processExcel(importSheet)
                break
            default:
            println "no filename match"
            break

        }
        */



        // redirect back to index
        redirect view: "index"

    }

}
