package net.codebuilders.mybusiness

class HomeController {

    def noticeService

    def index() {
        def notices = noticeService.getCurrentNotices()
        [noticeInstanceList: notices]
    }
}
