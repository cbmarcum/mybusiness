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

/* Class to display a notice on the home page for vacation holiday schedule, etc.
*
*/

class Notice {

    static constraints = {
        name(maxSize: 50)
        longDescription(maxSize: 2000)
        page(maxSize: 50, nullable: true) // domain
    }

    String name = ""  // notice name
    String longDescription = "" // field for html page
    String page = "" // domain for notice
    Date fromDate = new Date()
    Date thruDate = new Date()

    boolean display = true // display on page
}
