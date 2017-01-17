package net.codebuilders.mybusiness

import grails.transaction.Transactional


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

        // for max number get title, link, and description
        def items = new XmlParser().parse(url).channel[0].item

        for (item in items[0..max - 1]) {

            results.add([title: item.title.text(), link: item.link.text(), description: item.description.text()])

        }

        log.info("feed items = ${results}")
        return results

    }

}
