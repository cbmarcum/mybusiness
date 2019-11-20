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


/* Service to retrieve rss feeds from internet */

@Transactional
class RssFeedService {

    /* Get feeds from RssFeed domain with display true
    * return max number per feed*/

    def getFeedsByDisplay() {

        def c = RssFeed.createCriteria()

        def feedList = c.list {
            eq("display", true)
        }

        log.info("results by display = ${feedList}")

        def results = []

        for (RssFeed feed in feedList) {
            // add contents of list (maps of feed items) to results list
            results.addAll(getFeedItems(feed.url, feed.max))
        }
        log.info("feeds by display = ${results}")
        return results
    }

    def getFeedItems(String url, Integer max) {

        def results = []
        // read the feed

        try {

            // for max number get title, link, and description
            def items = new XmlParser().parse(url).channel[0].item

            for (item in items[0..max - 1]) {

                results.add([title: item.title.text(), link: item.link.text(), description: item.description.text()])

            }

            log.info("feed items = ${results}")

        } catch (all) {
            log.error("we caught an exception in XMLParser on ${url.toString()}")
        }

        return results

    }

}
