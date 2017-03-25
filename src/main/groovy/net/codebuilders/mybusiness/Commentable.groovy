/* Copyright 2006-2007 Code Builders, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.codebuilders.mybusiness

import grails.util.GrailsNameUtils

// import grails.util.Holders

/**
 * Marker interface used to specify a domain that can be commented on
 * @author Carl Marcum
 */
trait Commentable {

    static List getRecentComments() {
        def clazz = this

        def criteria = CommentLink.createCriteria()
        criteria.list {
            criteria.projections { property "comment" }
            criteria.eq('type', GrailsNameUtils.getPropertyName(clazz))
            criteria.maxResults 5
            criteria.cache(true)
            criteria.comment {
                order "dateCreated", "desc"
            }
        }

    }

    Commentable addComment(poster, String text) {
        if (this.id == null) throw new CommentException("You must save the entity [${this}] before calling addComment")

        def posterClass = poster.class.name
        def i = posterClass.indexOf('_$$_javassist')
        if (i > -1)
            posterClass = posterClass[0..i - 1]

        def c = new Comment(body: text, posterId: poster.id, posterClass: posterClass)
        if (!c.validate()) {
            throw new CommentException("Cannot create comment for arguments [$poster, $text], they are invalid.")
        }
        c.save()
        def link = new CommentLink(comment: c, commentRef: this.id, type: GrailsNameUtils.getPropertyName(this.class))
        link.save()
        try {
            this.onAddComment(c)
        } catch (MissingMethodException e) {
        }
        return this
    }

    List getComments() {
        def instance = this
        if (instance.id != null) {
            def criteria = CommentLink.createCriteria()
            criteria.list {
                criteria.projections { property "comment" }
                criteria.eq("commentRef", instance.id)
                criteria.eq('type', GrailsNameUtils.getPropertyName(instance.class))
                criteria.cache(true)
            }
        } else {
            return Collections.EMPTY_LIST
        }
    }

    Integer getTotalComments() {
        def instance = this
        if (instance.id != null) {
            def criteria = CommentLink.createCriteria()
            criteria.get {
                criteria.projections { rowCount() }
                criteria.eq("commentRef", instance.id)
                criteria.eq('type', GrailsNameUtils.getPropertyName(instance.class))
                criteria.cache(true)
            }
        } else {
            return 0
        }
    }

    def removeComment(Comment c) {
        CommentLink.findAllByComment(c)*.delete()
        c.delete(flush: true) // cascades deletes to links
    }

    def removeComment(Long id) {
        def c = Comment.get(id)
        if (c) removeComment(c)
    }

} // end trait