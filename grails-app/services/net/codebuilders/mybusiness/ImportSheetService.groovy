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

import com.sun.star.beans.XPropertySet
import com.sun.star.comp.helper.BootstrapException
import com.sun.star.connection.NoConnectException
import com.sun.star.frame.XComponentLoader
import com.sun.star.frame.XController
import com.sun.star.frame.XModel
import com.sun.star.lang.XComponent
import com.sun.star.lang.XMultiComponentFactory
import com.sun.star.sheet.XCellRangeAddressable
import com.sun.star.sheet.XSheetCellCursor
import com.sun.star.sheet.XSpreadsheet
import com.sun.star.sheet.XSpreadsheetDocument
import com.sun.star.sheet.XSpreadsheets
import com.sun.star.sheet.XSpreadsheetView
import com.sun.star.sheet.XUsedAreaCursor
import com.sun.star.sheet.XViewFreezable
import com.sun.star.table.*
import com.sun.star.uno.XComponentContext

import ooo.connector.BootstrapSocketConnector
import ooo.connector.server.OOoServer

/**
 * Service for ImportSheet
 *
 * @author Carl Marcum
 */
@Transactional(readOnly = true)
class ImportSheetService {

    /**
     * Process Calc Spreadsheet
     * Parse file and create or update product tables
     * @params sht
     * @return ImportSheetStatusType indicating result status
     */
    @Transactional
    def processCalc(ImportSheet sht) {
        println "Entered processCalc"
        log.info("Entered processCalc")

        // work on getting the file out...
        // File f = sht.sheet.getCloudFile("original")
        def sUrl = sht.sheet.url("original")
        log.info("sUrl = ${sUrl}")
        log.info("sUrl is a ${sUrl.getClass()}") // string


        // start calc test

        // EDIT FOR LOCATION OF SOFFICE
        // typical Linux location
        String oooExeFolder = "/opt/openoffice4/program/"
        // typical MS Windows
        // String oooExeFolder = "C:/Program Files (x86)/OpenOffice 4/program"

        XComponentContext mxRemoteContext
        XMultiComponentFactory mxRemoteServiceManager
        XSpreadsheetDocument xSpreadsheetDocument
        XComponent xComponent
        XSpreadsheetView xSpreadsheetView
        XViewFreezable xFreeze

        // start connect
        log.info "entered connect..."
        try {

            // normally this is how we get the remote office component context
            // mxRemoteContext = com.sun.star.comp.helper.Bootstrap.bootstrap()

            // instead we use the bootstrap-connector
            // mxRemoteContext = BootstrapSocketConnector.bootstrap(oooExeFolder)

            // method if we need to pass additional options
            // Create OOo server with additional -headless option
            List oooOptions = OOoServer.getDefaultOOoOptions()
            oooOptions.add("-headless")
            OOoServer oooServer = new OOoServer(oooExeFolder, oooOptions)
            // Connect to OOo
            BootstrapSocketConnector bootstrapSocketConnector = new BootstrapSocketConnector(oooServer)
            mxRemoteContext = bootstrapSocketConnector.connect()
            // end method if we need to pass additional options

            log.info("Connected to a running office ...")
            mxRemoteServiceManager = mxRemoteContext.getServiceManager()


        } catch (NoConnectException nce) {

            log.error("OOo is not responding")
            nce.printStackTrace()
        } catch (BootstrapException be) {

            log.error("ERROR: can't get a component context from a running office ...")
            println "First check the Office Path string in the script..."
            be.printStackTrace()
        } catch (Exception e) {
            log.error("Something is wrong with AOO")
            e.printStackTrace()
            // System.exit(1)
        }
        // end connect

        // replaces initDocument()
        XComponentLoader aLoader = mxRemoteContext.componentLoader

        // xComponent = aLoader.loadComponentFromURL(
        //         "private:factory/scalc", "_default", 0, new com.sun.star.beans.PropertyValue[0])

        xComponent = aLoader.loadComponentFromURL(
                sUrl, "_default", 0, new com.sun.star.beans.PropertyValue[0])

        xSpreadsheetDocument = xComponent.getSpreadsheetDocument(mxRemoteContext)

        XSpreadsheet xSheetPFC = xSpreadsheetDocument.getSheetByName("PFC")

        // validate data
        // --- Find the used area ---
        XSheetCellCursor xCursor = xSheetPFC.createCursor()

        // get the used area so we know how many rows
        XUsedAreaCursor xUsedCursor = xCursor.guno(XUsedAreaCursor.class)
        // xUsedCursor and xCursor are interfaces of the same object -
        // so modifying xUsedCursor takes effect on xCursor:
        xUsedCursor.gotoStartOfUsedArea(false)
        xUsedCursor.gotoEndOfUsedArea(true)

        XCellRangeAddressable xAddr = xCursor.guno(XCellRangeAddressable.class)

        CellRangeAddress cellRangeAddress = xAddr.getRangeAddress()

        Integer endRow = cellRangeAddress.EndRow

        println "cellRangeAddress.EndRow = ${endRow}"

        // iterate data rows and check cells
        (3..endRow).each { row ->
            int col = 0
            XCell xCell = xSheetPFC.getCellByPosition(col, row)
            // assert xCell.getFormula() == "TEST_SKU_1"
            if (xCell.getFormula() == "Update") {
                log.info("We found an Update")
                col = 1
                xCell = xSheetPFC.getCellByPosition(col, row)
                String desc = xCell.getFormula()
                log.info("Description is ${desc}")
                col = 2
                xCell = xSheetPFC.getCellByPosition(col, row)
                String shortDesc = xCell.getFormula()
                log.info("Short Description is ${shortDesc}")
                col = 3
                xCell = xSheetPFC.getCellByPosition(col, row)
                String number = xCell.getFormula()
                int sequenceNum = 0
                if (number.isInteger()) {
                    sequenceNum = number as Integer
                } else {
                    log.info("sequenceNum can't be converted to a Integer")
                    // TURN IT RED
                }


                ProductFeatureCategory pfc = ProductFeatureCategory.findOrCreateByDescription(desc)
                pfc.shortDescription =  shortDesc
                pfc.sequenceNum = sequenceNum
                pfc.save(flush: true)
                log.info("PFC ${desc} saved")

            } else {
                log.info("row was not update")
            }

        }

        // product features
        XSpreadsheet xSheetPF = xSpreadsheetDocument.getSheetByName("PF")

        // validate data
        // --- Find the used area ---
        xCursor = xSheetPF.createCursor()

        // get the used area so we know how many rows
        xUsedCursor = xCursor.guno(XUsedAreaCursor.class)
        // xUsedCursor and xCursor are interfaces of the same object -
        // so modifying xUsedCursor takes effect on xCursor:
        xUsedCursor.gotoStartOfUsedArea(false)
        xUsedCursor.gotoEndOfUsedArea(true)

        xAddr = xCursor.guno(XCellRangeAddressable.class)

        cellRangeAddress = xAddr.getRangeAddress()

        endRow = cellRangeAddress.EndRow

        println "cellRangeAddress.EndRow = ${endRow}"

        // iterate data rows and check cells

        (3..endRow).each { row ->
            int col = 0
            XCell xCell = xSheetPF.getCellByPosition(col, row)
            // assert xCell.getFormula() == "TEST_SKU_1"
            if (xCell.getFormula() == "Update") {
                log.info("We found an Update")
                col = 1
                xCell = xSheetPF.getCellByPosition(col, row)
                String desc = xCell.getFormula()
                log.info("Description is ${desc}")

                col = 2
                xCell = xSheetPF.getCellByPosition(col, row)
                String shortDesc = xCell.getFormula()
                log.info("Short Description is ${shortDesc}")

                col = 3
                xCell = xSheetPF.getCellByPosition(col, row)
                String pfcDesc = xCell.getFormula()
                log.info("pfcDesc is ${pfcDesc}")

                col = 4
                xCell = xSheetPF.getCellByPosition(col, row)
                String number = xCell.getFormula()
                int sequenceNum = 0
                if (number.isInteger()) {
                    sequenceNum = number as Integer
                } else {
                    log.info("sequenceNum can't be converted to a Integer")
                    // TURN IT RED
                }

                ProductFeatureCategory pfc = ProductFeatureCategory.findByDescription(pfcDesc)
                if (pfc) {
                    log.info("pfc ${desc} found")
                    ProductFeature pf = ProductFeature.findOrCreateByDescription(desc)
                    pf.shortDescription =  shortDesc
                    pf.productFeatureCategory = pfc
                    pf.sequenceNum = sequenceNum
                    pf.save(flush: true)
                    log.info("PF ${desc} saved")
                } else {
                    log.info("no pfc ${desc}")
                }

            } else {
                log.info("row was not update")
            }

        } // end each PF row





        // end calc test


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
