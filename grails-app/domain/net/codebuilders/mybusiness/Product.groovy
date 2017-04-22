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

/**
 * Domain class that represents a good or service for sale
 *
 * @author Carl Marcum
 */

class Product {

    static constraints = {
        number(maxSize: 50)
        name(maxSize: 50)
        shortDescription(maxSize: 250)
        longDescription(maxSize: 2000)
        largeDescription(maxSize: 4095) // was size: 0..4095
        salesDiscontinuationDate(nullable: true)
        supportDiscontinuationDate(nullable: true)
    }

    // for searchable plugin
    // UPDATE FOR HIBERNATE SEARCH
    /*
    static searchable = {
        mapping {
            productCategories component: true
            // goodIdentifications component: true // compile failed
            spellCheck "include"
        }
    }
    */

    // ProductCategory is used to group products that could be in
    // multiple groups.
    // ex. Pressure, Pressure Gauge, Tire Pressure Assembly
    static hasMany = [goodIdentifications     : GoodIdentification,
                      productCategories       : ProductCategory,
                      productFeatureCategories: ProductFeatureCategory,
                      productFeatureAppls     : ProductFeatureAppl,
                      otherAttributes         : String]

    // sku, upc, etc can also by in GoodIdentification
    // this number is easier to show in product list
    String number = "" // primary part number for product listing
    String name = ""  // part name

    String shortDescription = ""  // sales description
    String longDescription = "" // detail page description
    String largeDescription = "" // large detail page description
    BigDecimal listPrice = 0.00 // catalog price

    ProductType productType // ex. CONFIGURABLE_GOOD or FINISHED_GOOD

    Boolean display = true // display in catalog
    Boolean showcase = false // use for special display
    Boolean outOfStock = false // show as out of stock
    Boolean webSell = true // can be added to cart

    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp
    Date salesDiscontinuationDate
    Date supportDiscontinuationDate

    // for paypal
    BigDecimal shipWeight = 0.00 // ship weight in pounds
}