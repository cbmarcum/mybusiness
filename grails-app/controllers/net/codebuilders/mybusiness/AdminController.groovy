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

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class AdminController {

    def index() { }

    def about() { }

    def indexSearch() {

        /*
        BlogEntry.search().createIndexAndWait()
        GoodIdentification.search().createIndexAndWait()
        ProductCategory.search().createIndexAndWait()
        Product.search().createIndexAndWait()
        */

        // index only updated at commit time
        BlogEntry.search().withTransaction { transaction ->
            BlogEntry.findAll().each {
                it.search().index()
            }
        }

        // index only updated at commit time
        GoodIdentification.search().withTransaction { transaction ->
            GoodIdentification.findAll().each {
                it.search().index()
            }
        }

        // index only updated at commit time
        ProductCategory.search().withTransaction { transaction ->
            ProductCategory.findAll().each {
                it.search().index()
            }
        }

        // index only updated at commit time
        Product.search().withTransaction { transaction ->
            Product.findAll().each {
                it.search().index()
            }
        }

        redirect(action: "index")

    }


}
