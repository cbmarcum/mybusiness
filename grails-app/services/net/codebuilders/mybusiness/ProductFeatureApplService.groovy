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

import grails.transaction.Transactional

/**
 * Service for ProductFeatureAppl
 *
 * @author Carl Marcum
 */
@Transactional
class ProductFeatureApplService {

    // TODO: determine a real way to group products like by a style or base number
    // get ProductFeatureAppls by Product
    @Deprecated
    def getPfaByProduct(Product p) {

        // get first 7 characters of part number
        def prodType = p.number.substring(0, 6)
        println "prodType = ${prodType}"

        def pfas = p.productFeatureAppls
        def color = "none"
        for (pfa in pfas) {
            if (pfa.productFeature.productFeatureCategory.description == "Color") {
                color = pfa.productFeature.description
                log.debug "color is ${color}"
            }
        }

        def c = ProductFeatureAppl.createCriteria()

        def results = c.list {
            and {
                product {
                    like("number", "${prodType}%")
                }
                productFeature {
                    eq("description", color) // did include [ignoreCase: true]
                }
            }
            // order("sequenceNum") // is this correct ?
        }

        log.debug "results size = ${results.size()}"
        return results

    }

    /**
     * Creates or updates existing ProductFeatureAppl or deletes obsolete ones.
     * @param p Product to return ProductFeature from
     * @param c ProductFeatureCategory
     * @return ProductFeature object
     */
    def updateByCategoryAndValue(Product p, ProductFeatureCategory c, String val) {
        String result = ""
        def allPfas = p.productFeatureAppls
        List<ProductFeatureAppl> matchPfas = []
        List<ProductFeatureAppl> removePfas = []

        // find the category matches
        log.info("${allPfas.size()} PFA's found")
        for (pfa in allPfas) {
            log.info("pfa.productFeature = ${pfa.productFeature}")
            log.info("pfa.productFeature.productFeatureCategory = ${pfa.productFeature?.productFeatureCategory}")
            if (pfa.productFeature?.productFeatureCategory == c) {
                matchPfas << pfa
            }
        }

        log.info("${matchPfas.size()} PFA's matched category")

        // find the feature description match
        for (pfa in matchPfas) {
            if (val) {
                ProductFeature currentPf = pfa.productFeature
                if (currentPf.description != val) {
                    log.info("currentPf description = ${currentPf.description}")
                    log.info("color field val = ${val}")
                    log.info("feature description needs updated")
                    ProductFeature newPf = ProductFeature.findWhere(productFeatureCategory: c, description: val)
                    pfa.productFeature = newPf
                    pfa.save(flush: true)
                    result = "feature updated"
                } else {
                    log.info("feature description matches value")
                    result = "feature matched current"
                }

            } else {
                // category match with no value needs deleted
                removePfas << pfa
            }
        }

        // if we found obsolete ones delete them
        if (removePfas) {
            ProductFeatureAppl.deleteAll(removePfas)
            result += " and removed obsolete feature"
        }

        // if no category match and val is not empty, create one
        if (!matchPfas && val) {
            log.info("creating ProductFeatureAppl of ${val} in category ${c.description}")
            ProductFeature newPf = ProductFeature.findWhere(productFeatureCategory: c, description: val)
            ProductFeatureAppl pfa = new ProductFeatureAppl(
                    product: p, productFeature: newPf,
                    productFeatureApplType: ProductFeatureApplType.REQUIRED_FEATURE)
            pfa.save(flush: true)
            result = "new feature added"

        }

        return result
    }

}
