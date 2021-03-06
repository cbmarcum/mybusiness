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
 * Domain class to identify the various code or id values a product may have.
 *
 * @author Carl Marcum
 */
class GoodIdentification {

    static constraints = {
        value(maxSize: 50)
    }

    static search = {
        value index: 'yes'
    }

    GoodIdentificationType goodIdentificationType

    Product product

    /** holds the id number for the upc, sku, etc. */
    String value = ""

    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp

}
