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
