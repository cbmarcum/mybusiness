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
     * @return ProductFeature object
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

    /**
     * Get a simplified Map of ProductFeatures grouped by ProductFeatureCategory
     * description for all the products belonging to the variant group the
     * product belongs to.
     * ex. [Color:[Black, Raspberry Sorbet], Size:[X-Small, Small, Medium, Large, X-Large]]
     * @param p A Product that belongs to the variant group
     * @return A map with keys of ProductFeatureCategory description and values of List
     * of values
     */
    Map getSimpleProductFeatureMap(Product p) {
        log.info("entered get getSimpleProductFeatureMap")
        Map pfMap = [:]
        List<Product> products = getProductsByVariantGroup(p.variantGroupId)

        log.info("product count is ${products.size()}")

        List<ProductFeatureCategory> pfcs = []
        pfcs = getProductFeatureCategories(p)
        log.info("product has ${pfcs.size()} pfc's")

        // for each p.productFeatureCategories
        pfcs.each { pfc ->
            List values = []
            // a list of pfa's we can sort by seqenceNum
            List<ProductFeature> tempPfs = []
            log.info "Feature Category ${pfc.description}"
            products.each { product ->
                log.info("testing product ${product.id}")
                // if product has the feature category we're after, add the product feature to the list
                product.productFeatureAppls.each { pfa ->
                    if (pfa.productFeature.productFeatureCategory == pfc) {
                        log.info("product ${product.id} is ${pfa.productFeature.description}")
                        log.info "adding ${pfa.productFeature}"
                        // values << pfa.productFeature.description
                        tempPfs << pfa.productFeature
                    } else {
                        // println "No category of ${product.id} found for ${pfa.productFeature.productFeatureCategory.description}"
                        log.info "${pfa.productFeature.productFeatureCategory.description} did not match ${pfc.description}"
                    }
                }
            }

            // values.unique()
            tempPfs.unique()
            List<ProductFeature> pfs = []
            pfs = tempPfs.sort { it.sequenceNum }
            pfs.each { pf ->
                values << pf.description
            }

            log.info "${pfc.description}: ${values}"
            pfMap[(pfc.description)] = values

        } // end each


        return pfMap
    }

    /**
     * Get a List of ProductFeatureCategory for a Product
     * @param p A Product
     * @return A list of ProductFeatureCategory assigned to a product
     */
    List<ProductFeatureCategory> getProductFeatureCategories(Product p) {
        List<ProductFeatureCategory> pfcs = []
        List<ProductFeatureAppl> pfas = p.productFeatureAppls
        if (pfas) {
            pfas.each { pfa ->
                pfcs << pfa.productFeature.productFeatureCategory

            }
            pfcs.unique()
            log.info("product ${p.id} has ${pfcs.size()} ProductFeatureCategory")
        } else {
            log.info("product ${p.id} has no ProductFeatureAppls")
        }

        return pfcs
    }

    /**
     * Get a Map of variants grouped by feature category containing a list of maps representing
     * products and features.
     * ex. [Color:[[id:1, description:"Pink"], [id:3, description:"Blue"]],
     * Size:[[id:3, description:"Small"], [id:4, description:"Medium"]]]
     * @param p A Product
     * @return A Map of variants
     */
    // get a Map of variants grouped by feature category containing a list of maps representing
    // products and features
    // ex. [Color:[[id:1, description:"Pink"], [id:3, description:"Blue"]], Size:[[id:3, description:"Small"], [id:4, description:"Medium"]]]
    Map getVariantMapByProduct(Product p) {

        log.info("Entered getVariantMapByProduct")
        Map variantMap = [:]
        Map pfMap = [:]
        Map simpleCurrent = getSimpleProduct(p)
        // remove the id and the msg
        simpleCurrent.remove('id')
        simpleCurrent.remove('msg')
        List<Product> products = getProductsByVariantGroup(p.variantGroupId)
        List simpleProducts = getSimpleProducts(products)

        pfMap = getSimpleProductFeatureMap(p)
        if (!pfMap) {
            log.info("pfMap is empty")
            return variantMap
        }

        log.info(pfMap.toMapString())

        pfMap.each { category ->
            List buttons = []
            log.info "category = ${category.key}"
            log.info "category value list = ${category.value}"
            category.value.each { v ->
                // clone simpleCurrent and replace this current category value
                Map query = simpleCurrent.clone()
                query[category.key] = v
                log.info "query = ${query}"
                // find simpleProduct that matches query
                log.info "Finding product match"
                // Map button = [:]
                use(Loose) {
                    Map match = simpleProducts.find { prod ->
                        query == prod
                    }
                    if (match) {
                        log.info match.toMapString()
                        Map button = [id: match.id, description: v]
                        log.info "button = ${button}"
                        buttons << button
                    } else {
                        log.info("no match")
                        Map button = [description: v, msg: "No Match"]
                        log.info "button = ${button}"
                        buttons << button
                    }
                }
                variantMap[(category.key)] = buttons
            } // category.value.each

            // println "variantMap = ${variantMap}"

        } // pfMap.each

        return variantMap

    }

    /**
     * Get a List of maps representing simplified products
     * ex. [[id: 1, Color: "Pink", Size: "Small"], [id: 2, Color: "Pink", Size: "Medium"],
     * [id: 3, Color: "Blue", Size: "Small"], [id: 4, Color: "Blue", Size: "Medium"]]
     * @param products A List of Product
     * @return A List of maps representing simplified products
     */
    List<Map> getSimpleProducts(List<Product> products) {

        List<Map> simpleProducts = []
        products.each { product ->
            Map simpleProduct = [id: product.id]
            product.productFeatureAppls.each { pfa ->
                // since we use a variable for a key we have to escape it with ()
                simpleProduct << [(pfa.productFeature.productFeatureCategory.description): pfa.productFeature.description]
            }
            // add the message
            if (product.outOfStock) {
                simpleProduct << [msg: "Out of Stock"]
            } else {
                simpleProduct << [msg: "In Stock"]
            }
            simpleProducts << simpleProduct
        }
        println "simpleProducts:"
        simpleProducts.each { sp -> log.info(sp.toMapString()) }

        return simpleProducts

    }

    /**
     * Get a Map representing a simplified product
     * ex. [id: 1, Color: "Pink", Size: "Small"]
     * @param p A Product
     * @return A Map representing a simplified product
     */
    Map getSimpleProduct(Product p) {

        Map simpleProduct = [id: p.id]
        p.productFeatureAppls.each { pfa ->
            // since we use a variable for a key we have to escape it with ()
            simpleProduct << [(pfa.productFeature.productFeatureCategory.description): pfa.productFeature.description]
        }
        // add the message
        if (p.outOfStock) {
            simpleProduct << [msg: "Out of Stock"]
        } else {
            simpleProduct << [msg: "In Stock"]
        }

        return simpleProduct
    }

    /**
     * Get a List of Product belonging to a variant group
     * @param vg A String value for the variant group id.
     * @return A List of Product
     */
    List<Product> getProductsByVariantGroup(String vg) {

        List<Product> products = Product.createCriteria().list() {
            and {
                eq("variantGroupId", vg)
                eq("display", true)
            } // and

        } // list

        log.info("product count is ${products.size()}")

        return products
    }


}

class Loose {
    // first arg is the lesser test map and the second is the larger
    // that must match all of the first
    def static equals(Map one, Map two) {
        if (!two.keySet().containsAll(one.keySet())) return false
        one.every { it.value == two[it.key] }
    }
}
