package net.codebuilders.mybusiness

import grails.transaction.Transactional

@Transactional
class NoticeService {

    def getCurrentNotices() {

        def today = new Date()
        def c = Notice.createCriteria()

        def results = c.list {

            and {

                // date is in range
                and {
                    lt("fromDate", today)
                    gt("thruDate", today)
                } // and

                // display is true
                eq("display", true)
            } // and


            order("fromDate")
        }

        return results

    }
}
