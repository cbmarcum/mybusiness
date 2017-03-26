package net.codebuilders.mybusiness

import net.codebuilders.mybusiness.TestBlogEntry
import net.codebuilders.mybusiness.TestBlogPoster
import grails.test.mixin.TestFor
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@TestFor(CommentsTagLib)
@Rollback
class CommentsTagLibSpec extends Specification {

    void testEachComment() {
        given:
        def poster = new TestBlogPoster(name: "fred")
        poster.save()

        when:
        def entry = new TestBlogEntry(title: "The Entry")
        entry.addComment(poster, "My comment")

        then:
        thrown CommentException

        when:
        entry.save()
        entry.addComment poster, "one."
        entry.addComment poster, "two."
        def template = '<comments:each bean="${bean}">${comment.body}</comments:each>'

        then:
        applyTemplate(template, [bean: entry]) == "one.two."
        applyTemplate(template, [bean: null]) == ""
    }


    void testEachRecent() {
        given:
        def poster = new TestBlogPoster(name: "fred")
        poster.save()

        when:
        def entry = new TestBlogEntry(title: "The Entry")
        entry.addComment(poster, "My comment")
        then:
        thrown(CommentException)

        when:
        entry.save()
        entry.addComment poster, "one."
        entry.addComment poster, "two."
        def template = '<comments:eachRecent domain="${domain}">${comment.body}</comments:eachRecent>'

        then:
        applyTemplate(template, [domain: TestBlogEntry]) == 'two.one.'
    }
}
