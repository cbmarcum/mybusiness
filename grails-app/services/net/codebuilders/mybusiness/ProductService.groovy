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
 * Service for Product
 *
 * @author Carl Marcum
 */
@Transactional
class ProductService {

    /**
     * Get Products by category
     * @param params
     * @return List of Products
     */
    List<Product> getProductsByProdCat(params) {
        params.max = Math.min(params?.max?.toInteger() ?: 10, 100)
        params.offset = params?.offset?.toInteger() ?: 0
        params.sort = params?.sort ?: "number"
        params.order = params?.order ?: "asc"

        List<Product> products = Product.createCriteria().list(
                max: params.max,
                offset: params.offset,
                sort: params.sort,
                order: params.order) {
            productCategories {
                eq("description", params.category)
            }

        }

    }

    /**
     * Get Products by display status
     * @param params
     * @return List of Products
     */
    List<Product> getProductsByDisplay(params) {
        // set defaults if no param
        params.max = Math.min(params?.max?.toInteger() ?: 10, 100)
        params.offset = params?.offset?.toInteger() ?: 0
        params.sort = params?.sort ?: "number"
        params.order = params?.order ?: "asc"
        params.display = params?.display?.toBoolean() ?: true
        log.debug "params.display = ${params.display}"

        List<Product> products = Product.createCriteria().list(
                max: params.max,
                offset: params.offset,
                sort: params.sort,
                order: params.order) {

            eq("display", params.display)
        }

    }

    /**
     * Get Products by category and display status
     * @param params
     * @return List of Products
     */
    List<Product> getProductsByProdCatAndDisplay(params) {
        // set defaults if no param
        params.max = Math.min(params?.max?.toInteger() ?: 10, 100)
        params.offset = params?.offset?.toInteger() ?: 0
        params.sort = params?.sort ?: "number"
        params.order = params?.order ?: "asc"
        params.display = params?.display?.toBoolean() ?: true

        // def cat = params.category

        List<Product> products = Product.createCriteria().list(
                max: params.max,
                offset: params.offset,
                sort: params.sort,
                order: params.order) {
            and {
                productCategories {
                    eq("description", params.category)
                }
                eq("display", params.display)
            } //and
        } //end
    }

    /**
     * Get the value for a products size feature category or returns "none"
     * @param p Product to return size of
     * @return String size
     */
    @Deprecated
    String getSize(Product p) {

        def pfas = p.productFeatureAppls
        String size = "none"
        for (pfa in pfas) {
            if (pfa.productFeature.productFeatureCategory.description == "Size") {
                size = pfa.productFeature.description
                log.debug "size is ${size}"
            }
        }
        return size
    }

    /**
     * Get the ProductFeature for a products specific feature category
     * @param p Product to return ProductFeature from
     * @param c ProductFeatureCategory
     * @return ProductFeatureCategory object
     */
    ProductFeature getFeatureByCategory(Product p, ProductFeatureCategory c) {

        def pfas = p.productFeatureAppls
        ProductFeature pf
        for (pfa in pfas) {
            if (pfa.productFeature.productFeatureCategory == c) {
                pf = pfa.productFeature
                log.info "product feature = ${pf.toString()}"
            } else {
                log.info "product feature not found for ${c.toString()}"
            }
        }
        return pf
    }

}
