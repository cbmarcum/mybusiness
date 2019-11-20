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
 * Enum class to distinguish types of product conditions.
 *
 * NEW - a new product and unopened if in a package
 * NEW_OTHER - a new product that may have an opened box or other issues that
 *             need more explanation
 * NEW_DEFECTS - a new product that may have defects, blemishes, incorrect
 *               labels, tags, or markings
 * MANUFACTURER_REFURBISHED - a product that has been refurbished by the manufacturer
 * SELLER_REFURBISHED - a product that has been refurbished by the seller
 * USED - a used product
 * VERY_GOOD - a used product in very good condition
 * GOOD - a used product in good condition
 * ACCEPTABLE - a very used product that still works
 * NOT_WORKING - a product that doesn't work but may be used for parts
 *
 * @author Carl Marcum
 */
public enum ProductConditionType {

    NEW('New'),
    NEW_OTHER('New other (See details)'),
    NEW_DEFECTS('New with defects'),
    MANUFACTURER_REFURBISHED('Manufacturer refurbished'),
    SELLER_REFURBISHED('Seller refurbished'),
    USED('Used'),
    VERY_GOOD('Very good'),
    GOOD('Good'),
    ACCEPTABLE('Acceptable'),
    NOT_WORKING('Not working')

    static constraints = {
        name(maxSize: 60)
    }

    // New, New other (See details), Used, etc.
    String name

    ProductConditionType(String name) {
        this.name = name
    }

    static list() {
        [NEW, NEW_OTHER, NEW_DEFECTS, MANUFACTURER_REFURBISHED, SELLER_REFURBISHED,
        USED, VERY_GOOD, GOOD, ACCEPTABLE, NOT_WORKING]
    }

}

