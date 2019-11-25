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

/**
 * Domain class that represents a good or service for sale
 *
 * @author Carl Marcum
 */

class Product {

    static constraints = {
        number(maxSize: 40)
        name(maxSize: 80, nullable: true)
        brand(maxSize: 60, nullable: true)
        shortDescription(maxSize: 200, nullable: true)
        longDescription(maxSize: 2000, nullable: true)
        largeDescription(maxSize: 4000, nullable: true) // was 4095
        conditionDescription(maxSize: 1000, nullable: true)
        variantGroupId(maxSize: 50, nullable: true)
        taxCode(maxSize: 50, nullable: true)
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


    static search = {
        // fields
        number index: 'yes', sortable: true
        name index: 'yes', sortable: true
        brand index: 'yes'
        shortDescription index: 'yes'
        longDescription index: 'yes'
        largeDescription index: 'yes'
        goodIdentifications indexEmbedded: [includeEmbeddedObjectId: true, depth: 1]
        productCategories indexEmbedded: [includeEmbeddedObjectId: true, depth: 1]
        display index: 'yes'
        lastUpdated date: 'day', sortable: true
        salesDiscontinuationDate date: 'day'
        listPrice numeric: 2, analyze: false
        primaryVariant index: 'yes'
    }


    // ProductCategory is used to group products that could be in
    // multiple groups.
    // ex. Pressure, Pressure Gauge, Tire Pressure Assembly
    static hasMany = [goodIdentifications     : GoodIdentification,
                      productCategories       : ProductCategory,
                      productFeatureAppls     : ProductFeatureAppl,
                      otherAttributes         : String,
                      photos                  : Photo
    ]

    // declare these as List instead of default Set
    List<GoodIdentification> goodIdentifications = []
    List<ProductCategory> productCategories = []
    List<ProductFeatureAppl> productFeatureAppls = []
    List<String> otherAttributes = []
    List <Photo> photos = []

    // sku, upc, etc can also be in GoodIdentification
    // this number is easier to show in product list
    String number = "" // primary part number for product listing - for sku amazon 40, walmart 50
    String name = ""  // part name - ebay 80 char
    String brand = ""  // brand name - walmart 60 char

    String shortDescription = ""  // sales description
    String longDescription = "" // detail page description
    String largeDescription = "" // large detail page description
    String conditionDescription = "" // to further describe condition especially for used - ebay 1000
    String variantGroupId = ""  // to group variants together for color, size, countPerPack, etc.
    String taxCode = ''  // using taxware US tax code also used by walmart. see US_Tax_Codes_2017

    BigDecimal listPrice = 0.00 // catalog price

    ProductType productType = ProductType.FINISHED_GOOD // ex. CONFIGURABLE_GOOD or FINISHED_GOOD
    ProductConditionType productConditionType = ProductConditionType.NEW // NEW, USED, etc

    // the one variant of a group to display in a list result
    // or a product with no other variation
    Boolean primaryVariant = true

    Boolean display = true // display in product list
    Boolean showcase = false // use for special display
    Boolean outOfStock = false // show as out of stock
    Boolean webSell = true // can be added to cart - false gets Call for Availability

    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp
    // used in criteria for product listing
    Date salesDiscontinuationDate = new Date().parse('yyyy/MM/dd', '2070/01/01')
    Date supportDiscontinuationDate = new Date().parse('yyyy/MM/dd', '2070/01/01')

    // for paypal
    BigDecimal shipWeight = 0.00 // ship weight in pounds

    // formatted for use in form select fields
    String toString() {
        return "${number} - id:${id}"
    }

}