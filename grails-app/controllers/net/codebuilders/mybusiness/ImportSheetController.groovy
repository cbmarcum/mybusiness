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
    @Transactional
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
        log.info("Original filename: ${importSheet.sheet.originalFilename}")
        log.info("Filename: ${importSheet.sheet.fileName}")
        log.info("Content Type: ${importSheet.sheet.contentType}")
        // returns
        /*
        Original filename: null
        Filename: mpp-export-sample.ods
        Content Type: application/octet-stream
         */

        log.info("Testing a save...")
        importSheet.save(flush: true)

        // set to processing
        // importSheet.importSheetStatusType = ImportSheetStatusType.PROCESSING
        // importSheet.save(flush: true)

        if (importSheet.sheet.fileName.endsWith(".ods")) {
            log.info("we have a Calc file")

            ImportSheetStatusType result = importSheetService.processCalc(importSheet)

            log.info("status is ${result.toString()}")


        } else if (importSheet.sheet.fileName.endsWith(".xls")) {
            log.info "we have an Excel file"
            importSheet.importSheetStatusType = importSheetService.processExcel(importSheet)
            importSheet.save(flush: true)
        } else if (importSheet.sheet.fileName.endsWith(".xlsx")) {
            log.info "we have an Excel XML file"
            importSheet.importSheetStatusType = importSheetService.processExcel(importSheet)
            importSheet.save(flush: true)
        } else {
            log.info "no filename match"
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
