/*
* *************************************************************
 *
 * Copyright 2018 Code Builders, LLC
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
 * *************************************************************
 */

package net.codebuilders.mybusiness

import grails.transaction.Transactional

/**
 * Service for ImportSheet
 *
 * @author Carl Marcum
 */
// @Transactional
class ImportSheetService {

    /**
     * Process Calc Spreadsheet
     * Parse file and create or update product tables
     * @params sht
     * @return ImportSheetStatusType indicating result status
     */
    def processCalc(ImportSheet sht) {
        println "Entered processCalc"
        log.info("Entered processCalc")

        return ImportSheetStatusType.COMPLETED
    }

    /**
     * Process Excel Spreadsheet
     * Parse file and create or update product tables
     * @params sht
     * @return ImportSheetStatusType indicating result status
     */
    def processExcel(ImportSheet sht) {
        println "Entered processExcel"
        log.info("Entered processExcel")

        return ImportSheetStatusType.COMPLETED
    }


}
