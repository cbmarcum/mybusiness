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

/**
 * Domain class for product features
 * ex. has a category of "Color" and a description of "Blue"
 *
 * @author Carl Marcum
 */
package net.codebuilders.mybusiness

class ProductFeature {

    static constraints = {
        description(maxSize: 50)
        shortDescription(maxSize: 25)
    }

    ProductFeatureCategory productFeatureCategory
    String description = "" // ex. Small/Medium
    String shortDescription = "" // ex. S/M
    Integer sequenceNum = 0  // sequence of feature within a product feature category
    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp

    // formatted for use in form select fields
    String toString() {
        return "${productFeatureCategory.description}: ${description} - id:${id}"
    }

}
