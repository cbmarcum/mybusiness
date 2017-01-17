package net.codebuilders.mybusiness

class RssFeed {

    static constraints = {
        name(uniuque: true, maxSize: 50)
        url url: true
    }

    String name = ""
    String url = ""
    Integer max = 4

    Boolean display = false

}
