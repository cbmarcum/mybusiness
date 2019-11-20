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
class NoticeService {

    def getCurrentNotices() {

        def today = new Date()
        def c = Notice.createCriteria()

        def results = c.list {

            // date is in range
            and {
                lt("fromDate", today)
                gt("thruDate", today)
            } // and

            // display is true
            eq("display", true)

            order("fromDate")
        }

        return results
    }

    def getCurrentNoticesByPage(String page) {

        def today = new Date()
        def c = Notice.createCriteria()

        def results = c.list {

            // date is in range
            and {
                lt("fromDate", today)
                gt("thruDate", today)
            } // and

            // display is true
            eq("display", true)

            eq("page", page)

            order("fromDate")
        }

        return results
    }

}
