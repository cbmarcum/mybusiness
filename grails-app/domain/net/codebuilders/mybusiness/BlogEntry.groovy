package net.codebuilders.mybusiness

class BlogEntry implements Taggable, Commentable {

    String author
    String title
    String body
    Boolean locked = false
    Boolean published = false

    Date dateCreated
    Date lastUpdated

    static constraints = {
        author(maxSize: 50)
        title(maxSize: 80, blank: false)
        body(maxSize: 4000, blank: false)
    }

    static mapping = {
        body type: "text"
        cache true
    }


}
