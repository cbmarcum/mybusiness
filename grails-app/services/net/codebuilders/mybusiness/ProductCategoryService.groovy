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

import grails.gorm.transactions.Transactional

@Transactional
class ProductCategoryService {

    /**
     * Gets a unique list of categories including ancestors based on a list of specific (child) categories
     * @param categories List of specific (closest child) ProductCategory
     * @return ProductCategory List
     */
    List<ProductCategory> getRelatedCategories(List<ProductCategory> categories) {
        List<ProductCategory> tempList = []
        categories.each { category ->
            ProductCategory cat = category
            tempList.add(cat)
            while (cat.parent) {
                cat = cat.parent
                tempList << cat
            }
        }
        List<ProductCategory> result = tempList.unique()
        return result
    }

    /**
     * Gets a unique list of categories including ancestors based on a list of specific (child) category id's
     * @param catIds List of id's of specific (closest child) ProductCategory
     * @return ProductCategory List
     */
    List<ProductCategory> getRelatedCategoriesById(List<Integer> catIds) {
        // get the categories by id
        List<ProductCategory> tempCategories = ProductCategory.getAll(catIds)
        // get the unique list with ancestors and remove possible nulls from getAll
        List<ProductCategory> result = getRelatedCategories(tempCategories) - null
        return result
    }


}
