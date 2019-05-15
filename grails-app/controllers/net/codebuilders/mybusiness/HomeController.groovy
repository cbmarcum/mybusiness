package net.codebuilders.mybusiness

class HomeController {

    NoticeService noticeService
    def rssFeedService

    def index() {
        def notices = noticeService.getCurrentNoticesByPage("Home")
        def feeds = rssFeedService.getFeedsByDisplay()
        [noticeList: notices, feedList: feeds]
    }
}
