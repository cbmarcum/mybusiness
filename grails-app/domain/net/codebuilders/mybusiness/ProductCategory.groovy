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
 *
 * Domain class used to assign categories to a Product.
 * ex. Pistols, Rifles, Firearms
 * This has many to many relation to Product.
 * Categories may have a parent which is used to create a hierarchy.
 *
 * @author Carl Marcum
 */
class ProductCategory {

    static constraints = {
        description(maxSize: 50)
        parent(nullable: true)
    }

    static search = {
        description index: 'yes'
    }

    String description = ""
    ProductCategory parent
    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp

    // formatted for use in form select fields
    String toString() {
        return "${description} - id:${id} > ${parent?.description ?: 'No Parent'}"
    }

}
