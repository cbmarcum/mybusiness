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
 * Domain class to join a Product with a ProductFeature
 *
 * @author Carl Marcum
 */
class ProductFeatureAppl {

    static constraints = {
        // thruDate(nullable:true)
        salesDiscontinuationDate(nullable: true)
    }

    Product product
    ProductFeature productFeature
    ProductFeatureApplType productFeatureApplType
    BigDecimal listPrice = 0.00 // catalog price of feature for product
    Integer sequenceNum = 0  // seq within a category per product like color
    String code = ""  // code used in part number creation on configurable products
    Boolean display = true // display in catalog - generally for configurable features
    // Date fromDate
    // Date thruDate
    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp
    Date salesDiscontinuationDate // generally for configurable features

}
