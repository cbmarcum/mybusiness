package net.codebuilders.mybusiness

class HomeController {

    def noticeService
    def rssFeedService

    def index() {
        def notices = noticeService.getCurrentNotices()
        def feeds = rssFeedService.getFeedsByDisplay()
        [noticeInstanceList: notices, feedList: feeds]
    }
}
