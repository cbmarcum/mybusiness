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

}
