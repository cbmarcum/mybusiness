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

class HomeController {

    NoticeService noticeService
    def rssFeedService
    def shopService

    def index() {
        def notices = noticeService.getCurrentNoticesByPage("Home")
        def feeds = rssFeedService.getFeedsByDisplay()

        if (!session?.cartCounter||!session?.cart) {
            session.cartCounter=[:]
            session.cart=[:]
        }

        //def categories = shopService.getCategories()
        def windowsList = shopService.getWindowList(categories.searchParams)

        //render view: 'index', model: [categories:categories.categories,noticeList: notices, windowsList: windowsList, noticeList: notices]

        render view: 'index', model: [categories:[], noticeList: notices, windowsList: windowsList, noticeList: notices]
        return
    }
}
