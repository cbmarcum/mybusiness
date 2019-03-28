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

    ProductService productService
    ProductFeatureApplService productFeatureApplService
    ProductCategoryService productCategoryService

    /**
     * Process Calc Spreadsheet
     * Parse file and create or update product tables
     * @params sht The ImportSheet object to process
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

        BootstrapSocketConnector bootstrapSocketConnector

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
            bootstrapSocketConnector = new BootstrapSocketConnector(oooServer)
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

        log.info("Processing Product Feature Categories ...")

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

        // start off positive
        sht.comments = "No Errors Found"

        // iterate data rows and check cells
        (3..endRow).each { row ->
            boolean newEntry = false
            int pfcId = 0
            int col = 1
            XCell xCell = xSheetPFC.getCellByPosition(col, row)
            // assert xCell.getFormula() == "TEST_SKU_1"
            if (xCell.getFormula() == "Update") {
                log.info("Row ${row} needs updated")

                col = 2
                xCell = xSheetPFC.getCellByPosition(col, row)

                if (xCell.type == CellContentType.EMPTY) {
                    newEntry = true
                    log.info("ID is empty")
                } else {
                    String idStr = xCell.getFormula().trim()

                    if (idStr.isInteger()) {
                        pfcId = idStr as Integer
                    } else {
                        log.info("ID value can't be converted to a Integer")
                        // TODO: turn status cell bg red
                        sht.comments = "Errors Found"
                    }
                }

                col = 3
                xCell = xSheetPFC.getCellByPosition(col, row)
                String desc = xCell.getFormula().trim()
                log.info("Description is ${desc}")

                col = 4
                xCell = xSheetPFC.getCellByPosition(col, row)
                String shortDesc = xCell.getFormula().trim()
                log.info("Short Description is ${shortDesc}")

                col = 5
                xCell = xSheetPFC.getCellByPosition(col, row)
                String number = xCell.getFormula().trim()
                int sequenceNum = 0
                if (number.isInteger()) {
                    sequenceNum = number as Integer
                } else {
                    log.info("Sequence value can't be converted to a Integer")
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                }

                ProductFeatureCategory pfc = null


                if (newEntry) {
                    // not in db yet
                    pfc = new ProductFeatureCategory([description     : desc,
                                                      shortDescription: shortDesc, sequenceNum: sequenceNum])
                } else {
                    pfc = ProductFeatureCategory.get(pfcId)
                    pfc.description = desc
                    pfc.shortDescription = shortDesc
                    pfc.sequenceNum = sequenceNum
                }

                if (pfc.validate()) {
                    pfc.save(flush: true)
                    log.info("PFC ${desc} saved")
                    // TODO: turn status cell bg green
                } else {
                    // print validation errors
                    log.info("PFC did not validate")
                    pfc.errors.allErrors.each { org.springframework.validation.FieldError error ->
                        log.info(error.defaultMessage)
                    }
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                }

            } else {
                log.info("Skipping row ${row}.")
            }

        }

        // product features
        XSpreadsheet xSheetPF = xSpreadsheetDocument.getSheetByName("PF")

        log.info("Processing Product Features ...")

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
            boolean newEntry = false
            int pfId = 0
            int col = 1
            XCell xCell = xSheetPF.getCellByPosition(col, row)
            // assert xCell.getFormula() == "TEST_SKU_1"
            if (xCell.getFormula() == "Update") {
                log.info("Row ${row} needs updated")

                col = 2
                xCell = xSheetPF.getCellByPosition(col, row)

                if (xCell.type == CellContentType.EMPTY) {
                    newEntry = true
                    log.info("ID is empty")
                } else {
                    String idStr = xCell.getFormula().trim()

                    if (idStr.isInteger()) {
                        pfId = idStr as Integer
                    } else {
                        log.info("ID value can't be converted to a Integer")
                        // TODO: turn status cell bg red
                        sht.comments = "Errors Found"
                    }
                }

                col = 3
                xCell = xSheetPF.getCellByPosition(col, row)
                String desc = xCell.getFormula().trim()
                log.info("Description is ${desc}")

                col = 4
                xCell = xSheetPF.getCellByPosition(col, row)
                String shortDesc = xCell.getFormula().trim()
                log.info("Short Description is ${shortDesc}")

                col = 5
                xCell = xSheetPF.getCellByPosition(col, row)
                String pfcDesc = xCell.getFormula().trim()
                ProductFeatureCategory pfc = ProductFeatureCategory.findByDescription(pfcDesc)
                if (pfc) {
                    log.info("PCF = ${pfc}")
                    // TODO: turn status cell bg green
                } else {
                    log.info("PCF ${pfcDesc} not found")
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                    return
                }


                col = 6
                xCell = xSheetPF.getCellByPosition(col, row)
                String number = xCell.getFormula().trim()
                int sequenceNum = 0
                if (number.isInteger()) {
                    sequenceNum = number as Integer
                } else {
                    log.info("Sequence value can't be converted to a Integer")
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                }

                ProductFeature pf = null

                if (newEntry) {
                    // not in db yet
                    pf = new ProductFeature([description           : desc, shortDescription: shortDesc,
                                             productFeatureCategory: pfc, sequenceNum: sequenceNum])
                } else {
                    pf = ProductFeature.get(pfId)
                    pf.description = desc
                    pf.shortDescription = shortDesc
                    pf.productFeatureCategory = pfc
                    pf.sequenceNum = sequenceNum
                }

                if (pf.validate()) {
                    pf.save(flush: true)
                    log.info("PF ${desc} saved")
                    // TODO: turn status cell bg green
                } else {
                    // print validation errors
                    log.info("PF did not validate")
                    pf.errors.allErrors.each { org.springframework.validation.FieldError error ->
                        log.info(error.defaultMessage)
                    }
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                }

            } else {
                log.info("Skipping row ${row}.")
            }

        } // end each PF row

        // product categories
        XSpreadsheet xSheetPC = xSpreadsheetDocument.getSheetByName("PC")

        log.info("Processing Product Categories ...")

        // validate data
        // --- Find the used area ---
        xCursor = xSheetPC.createCursor()

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
            boolean newEntry = false
            int pcId = 0
            int col = 1
            XCell xCell = xSheetPC.getCellByPosition(col, row)
            // assert xCell.getFormula() == "TEST_SKU_1"
            if (xCell.getFormula() == "Update") {
                log.info("Row ${row} needs updated")

                col = 2
                xCell = xSheetPC.getCellByPosition(col, row)

                if (xCell.type == CellContentType.EMPTY) {
                    newEntry = true
                    log.info("ID is empty")
                } else {
                    String idStr = xCell.getFormula().trim()

                    if (idStr.isInteger()) {
                        pcId = idStr as Integer
                    } else {
                        log.info("ID value can't be converted to a Integer")
                        // TODO: turn status cell bg red
                        sht.comments = "Errors Found"
                    }
                }

                col = 3
                xCell = xSheetPC.getCellByPosition(col, row)
                String desc = xCell.getFormula().trim()
                log.info("Description is ${desc}")

                col = 4
                xCell = xSheetPC.getCellByPosition(col, row)
                String parentIdStr = xCell.getFormula().trim()
                // log.info("Parent is ${parent}")

                ProductCategory pcParent = null
                int parentId = 0
                if (parentIdStr.isInteger()) {
                    parentId = parentIdStr as Integer
                    pcParent = ProductCategory.get(parentId)
                } else {
                    log.info("Parent ID value can't be converted to a Integer")
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                }
                log.info("pcParent is ${pcParent}")

                ProductCategory pc = null

                if (newEntry) {
                    // not in db yet
                    pc = new ProductCategory([description: desc, parent: pcParent])
                } else {
                    pc = ProductFeature.get(pcId)
                    pc.description = desc
                    pc.parent = pcParent
                }

                if (pc.validate()) {
                    pc.save(flush: true)
                    log.info("PC ${desc} saved")
                    // TODO: turn status cell bg green
                } else {
                    // print validation errors
                    log.info("PC did not validate")
                    pc.errors.allErrors.each { org.springframework.validation.FieldError error ->
                        log.info(error.defaultMessage)
                    }
                    // TODO: turn status cell bg red
                    sht.comments = "Errors Found"
                }

            } else {
                log.info("Skipping row ${row}.")
            }

        } // end each PC row

        // products
        XSpreadsheet xSheet = xSpreadsheetDocument.getSheetByName("Products")

        log.info("Processing Products ...")

        // validate data
        // --- Find the used area ---
        xCursor = xSheet.createCursor()

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
            boolean newEntry = false
            int id = 0
            int col = 1
            XCell xCell = xSheet.getCellByPosition(col, row)
            // assert xCell.getFormula() == "TEST_SKU_1"
            if (xCell.getFormula() == "Update") {
                log.info("Row ${row} needs updated")

                Product product = null
                List<ProductCategory> productCategories = []

                // id
                col = 2
                xCell = xSheet.getCellByPosition(col, row)

                if (xCell.type == CellContentType.EMPTY) {
                    newEntry = true
                    log.info("ID is empty")
                } else {
                    String idStr = xCell.getFormula().trim()

                    if (idStr.isInteger()) {
                        id = idStr as Integer
                    } else {
                        log.info("ID value can't be converted to a Integer")
                        sht.comments = "Errors Found"
                        // TODO: turn status cell bg red
                        // set cell style to red bg
                        XPropertySet xCPS = xCell.guno(XPropertySet.class)
                        xCPS.putAt("CellStyle", "RedBg")
                        return // skip to next
                    }
                }

                if (newEntry) {
                    // not in db yet
                    product = new Product()
                    log.info("Creating new Product")
                } else {
                    product = Product.get(id)
                    if (product) {
                        log.info("Updating Product $id")
                    } else {
                        log.info("No Product ID $id found... skipping")
                        sht.comments = "Errors Found"
                        // set cell style to red bg
                        XPropertySet xCPS = xCell.guno(XPropertySet.class)
                        xCPS.putAt("CellStyle", "RedBg")
                        return // skip to next
                    }
                }

                // number (SKU)
                col = 3
                xCell = xSheet.getCellByPosition(col, row)
                String number = xCell.getFormula().trim()
                log.info("SKU  is ${number}")
                product.number = number

                // name (Product Name)
                col = 4
                xCell = xSheet.getCellByPosition(col, row)
                String name = xCell.getFormula().trim()
                log.info("Product Name is ${name}")
                product.name = name

                // shortDescription (Short Description)
                col = 5
                xCell = xSheet.getCellByPosition(col, row)
                String shortDescription = xCell.getFormula().trim()
                log.info("Short Description is ${shortDescription}")
                product.shortDescription = shortDescription


                // [productCategories] (Category - most specific)
                // could be comma separated list of id's or just one
                col = 6
                xCell = xSheet.getCellByPosition(col, row)
                String primaryCategoryStr = xCell.getFormula().trim()

                if (primaryCategoryStr) {
                    // double check the field contains a string of a single integer
                    // or a comma separated list of them
                    List tempStrList = primaryCategoryStr.tokenize(",")
                    List<Integer> intList = []

                    tempStrList.each { it ->
                        if (it.trim().isInteger()) {
                            intList << Integer.valueOf(it.trim())
                        } else {
                            log.info("${it.trim()} can't be made an Integer")
                        }
                    }

                    // make sure the string and int list sizes match
                    if (tempStrList.size() == intList.size()) {
                        // validate all id's can be found
                        List<ProductCategory> tempPcList = ProductCategory.getAll(intList)
                        if (intList.size() == tempPcList.size()) {
                            product.productCategories = productCategoryService.getRelatedCategoriesById(intList)
                        } else {
                            log.info("Not all ID's had categories")
                            sht.comments = "Errors Found"
                            // set cell style to red bg
                            XPropertySet xCPS = xCell.guno(XPropertySet.class)
                            xCPS.putAt("CellStyle", "RedBg")
                            return // skip to next
                        }

                    } else {
                        log.info("One of the category ID's can't be made an Integer")
                        sht.comments = "Errors Found"
                        // set cell style to red bg
                        XPropertySet xCPS = xCell.guno(XPropertySet.class)
                        xCPS.putAt("CellStyle", "RedBg")
                        return // skip to next
                    }

                } else {
                    log.info("Primary Category ID's is empty")
                }

                // [goodIdentifications] (UPCA)
                col = 7
                xCell = xSheet.getCellByPosition(col, row)
                String upca = xCell.getFormula().trim()
                log.info("UPCA is ${upca}")
                // added after product save


                // longDescription (Description)
                col = 8
                xCell = xSheet.getCellByPosition(col, row)
                String desc = xCell.getFormula().trim()
                log.info("Description is ${desc}")
                product.longDescription = desc

                // brand (Brand)
                col = 9
                xCell = xSheet.getCellByPosition(col, row)
                String brand = xCell.getFormula().trim()
                log.info("Brand is ${brand}")
                product.brand = brand

                // [goodIdentifications] (Manufacturer Part Number)
                col = 10
                xCell = xSheet.getCellByPosition(col, row)
                String manufId = xCell.getFormula().trim()
                log.info("Manufacturer ID is ${manufId}")
                // added after product save

                // taxCode (Product Tax Code)
                col = 11
                xCell = xSheet.getCellByPosition(col, row)
                String taxCode = xCell.getFormula().trim()
                log.info("Tax Code is ${taxCode}")
                product.taxCode = taxCode

                // TODO: images

                // listPrice (Price)
                col = 17
                xCell = xSheet.getCellByPosition(col, row)
                String price = xCell.getFormula().trim()
                // listPrice is a BigDecimal
                log.info("Price is ${price}")

                if (price.isBigDecimal()) {
                    product.listPrice = new BigDecimal(price)
                } else {
                    log.info("Can't make ${price} a decimal")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }

                // [productFeatureAppls] (Color)
                col = 18
                xCell = xSheet.getCellByPosition(col, row)
                String color = xCell.getFormula().trim()
                log.info("Color is ${color}")
                // added after product save

                // [productFeatureAppls] (Size)
                col = 19
                xCell = xSheet.getCellByPosition(col, row)
                String size = xCell.getFormula().trim()
                log.info("Size ${size}")
                // added after product save

                // [productFeatureAppls] (Pattern)
                col = 20
                xCell = xSheet.getCellByPosition(col, row)
                String pattern = xCell.getFormula().trim()
                log.info("Pattern or Design is ${pattern}")
                // added after product save

                // [productFeatureAppls] (Count per Pack)
                col = 21
                xCell = xSheet.getCellByPosition(col, row)
                String count = xCell.getFormula().trim()
                log.info("Count per Pack is ${count}")
                // added after product save

                // shipWeight (Package Weight in Pounds)
                col = 22
                xCell = xSheet.getCellByPosition(col, row)
                String weight = xCell.getFormula().trim()
                // shipWeight is a BigDecimal
                log.info("Package Weight in Pounds is ${weight}")
                if (weight.isBigDecimal()) {
                    product.shipWeight = new BigDecimal(weight)
                } else {
                    log.info("Can't make ${weight} a decimal")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }


                // variantGroupId (Variant Group ID)
                col = 23
                xCell = xSheet.getCellByPosition(col, row)
                String variantGroupId = xCell.getFormula().trim()
                log.info("Variant Group ID is ${variantGroupId}")
                product.variantGroupId = variantGroupId

                // primaryVariant (Is Primary Variant)
                col = 24
                xCell = xSheet.getCellByPosition(col, row)
                String primaryVariant = xCell.getFormula().trim()
                // primaryVariant is a boolean
                log.info("Is Primary Variant is ${primaryVariant}")

                if (primaryVariant.toLowerCase() == "yes") {
                    product.primaryVariant = Boolean.TRUE
                } else if (primaryVariant.toLowerCase() == "no") {
                    product.primaryVariant = Boolean.FALSE
                } else {
                    log.info("Primary Variant has to be Yes or No")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }

                // display (Display)
                col = 25
                xCell = xSheet.getCellByPosition(col, row)
                String display = xCell.getFormula().trim()
                // display is a boolean
                log.info("Display is ${display}")

                if (display.toLowerCase() == "yes") {
                    product.display = Boolean.TRUE
                } else if (display.toLowerCase() == "no") {
                    product.display = Boolean.FALSE
                } else {
                    log.info("Display has to be Yes or No")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }


                // showcase (Showcase)
                col = 26
                xCell = xSheet.getCellByPosition(col, row)
                String showcase = xCell.getFormula().trim()
                // showcase is a boolean
                log.info("Showcase is ${showcase}")

                if (showcase.toLowerCase() == "yes") {
                    product.showcase = Boolean.TRUE
                } else if (showcase.toLowerCase() == "no") {
                    product.showcase = Boolean.FALSE
                } else {
                    log.info("Showcase has to be Yes or No")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }

                // outOfStock (Out of Stock)
                col = 27
                xCell = xSheet.getCellByPosition(col, row)
                String outOfStock = xCell.getFormula().trim()
                // outOfStock is a boolean
                log.info("Out of Stock is ${outOfStock}")

                if (outOfStock.toLowerCase() == "yes") {
                    product.outOfStock = Boolean.TRUE
                } else if (outOfStock.toLowerCase() == "no") {
                    product.outOfStock = Boolean.FALSE
                } else {
                    log.info("Out of Stock has to be Yes or No")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }

                // webSell (Web Sell)
                col = 28
                xCell = xSheet.getCellByPosition(col, row)
                String webSell = xCell.getFormula().trim()
                // webSell is a boolean
                log.info("Web Sell is ${webSell}")

                if (webSell.toLowerCase() == "yes") {
                    product.webSell = Boolean.TRUE
                } else if (webSell.toLowerCase() == "no") {
                    product.webSell = Boolean.FALSE
                } else {
                    log.info("Web Sell has to be Yes or No")
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }

                // TODO: discontinuation dates

                log.info("Validating Product ${product}")
                if (product.validate()) {
                    product.save(flush: true)
                    log.info("Product ${product.number} saved")
                    // TODO: turn status cell bg green
                } else {
                    // print validation errors
                    log.info("Product did not validate")
                    product.errors.allErrors.each { org.springframework.validation.FieldError error ->
                        log.info(error.defaultMessage)
                    }
                    sht.comments = "Errors Found"
                    // set cell style to red bg
                    XPropertySet xCPS = xCell.guno(XPropertySet.class)
                    xCPS.putAt("CellStyle", "RedBg")
                    return // skip to next
                }

                // finish up things that need a product saved first

                // if it's a new entry we can just create one
                if (newEntry) {
                    GoodIdentification upcaGoodId = new GoodIdentification(
                            product: product, goodIdentificationType: GoodIdentificationType.UPCA, value: upca)
                    upcaGoodId.save(flush: true)
                } else {
                    // if it's an update, check if we have one to update
                    GoodIdentification upcaGoodId = GoodIdentification.findOrCreateByProductAndGoodIdentificationType(
                            product, GoodIdentificationType.UPCA)
                    upcaGoodId.value = upca
                    upcaGoodId.save(flush: true)
                }

                // if it's a new entry we can just create one
                if (newEntry) {
                    GoodIdentification manufGoodId = new GoodIdentification(
                            product: product, goodIdentificationType: GoodIdentificationType.MANUFACTURER_ID,
                            value: manufId)
                    manufGoodId.save(flush: true)
                } else {
                    // if it's an update, get or create one
                    GoodIdentification manufGoodId = GoodIdentification.findOrCreateByProductAndGoodIdentificationType(
                            product, GoodIdentificationType.MANUFACTURER_ID)
                    manufGoodId.value = manufId
                    manufGoodId.save(flush: true)
                }

                log.info("color applications")
                ProductFeatureCategory pfcColor = ProductFeatureCategory.findByDescription("Color")
                log.info("product = ${product}")
                log.info("pfcColor = ${pfcColor}")
                log.info("color = ${color}")
                String colorStatus = productFeatureApplService.updateByCategoryAndValue(product, pfcColor, color)
                log.info("Color was ${colorStatus}")

                log.info("size applications")
                ProductFeatureCategory pfcSize = ProductFeatureCategory.findByDescription("Size")
                log.info("product = ${product}")
                log.info("pfcSize = ${pfcSize}")
                log.info("size = ${size}")
                String sizeStatus = productFeatureApplService.updateByCategoryAndValue(product, pfcSize, size)
                log.info("Size was ${sizeStatus}")

                log.info("pattern applications")
                ProductFeatureCategory pfcPattern = ProductFeatureCategory.findByDescription("Pattern or Design")
                log.info("product = ${product}")
                log.info("pfcPattern = ${pfcPattern}")
                log.info("pattern = ${pattern}")
                String patternStatus = productFeatureApplService.updateByCategoryAndValue(product, pfcPattern, pattern)
                log.info("Pattern was ${patternStatus}")

                log.info("count applications")
                ProductFeatureCategory pfcCount = ProductFeatureCategory.findByDescription("Count per Pack")
                log.info("product = ${product}")
                log.info("pfcCount = ${pfcCount}")
                log.info("count = ${count}")
                String countStatus = productFeatureApplService.updateByCategoryAndValue(product, pfcCount, count)
                log.info("Count was ${countStatus}")


            } else {
                log.info("Skipping row ${row}")
            }

        } // end each Product row

        // Disconnect and terminate OOo server
        log.info("We would disconnect the connector here...")
        bootstrapSocketConnector.disconnect()

        // end calc test


        return ImportSheetStatusType.COMPLETED
    }

    /**
     * Process Excel Spreadsheet
     * Parse file and create or update product tables
     * @params sht* @return ImportSheetStatusType indicating result status
     */
    def processExcel(ImportSheet sht) {
        println "Entered processExcel"
        log.info("Entered processExcel")

        return ImportSheetStatusType.COMPLETED
    }


}
