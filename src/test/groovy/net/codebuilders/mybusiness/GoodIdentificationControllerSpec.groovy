/*
 * *************************************************************
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * *************************************************************
 */

package net.codebuilders.mybusiness

import grails.test.mixin.*
import spock.lang.*

@TestFor(GoodIdentificationController)
@Mock(GoodIdentification)
class GoodIdentificationControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.goodIdentificationList
        model.goodIdentificationCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.goodIdentification != null
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def goodIdentification = new GoodIdentification()
        goodIdentification.validate()
        controller.save(goodIdentification)

        then: "The create view is rendered again with the correct model"
        model.goodIdentification != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        goodIdentification = new GoodIdentification(params)

        controller.save(goodIdentification)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/goodIdentification/show/1'
        controller.flash.message != null
        GoodIdentification.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def goodIdentification = new GoodIdentification(params)
        controller.show(goodIdentification)

        then: "A model is populated containing the domain instance"
        model.goodIdentification == goodIdentification
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def goodIdentification = new GoodIdentification(params)
        controller.edit(goodIdentification)

        then: "A model is populated containing the domain instance"
        model.goodIdentification == goodIdentification
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/goodIdentification/index'
        flash.message != null

        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def goodIdentification = new GoodIdentification()
        goodIdentification.validate()
        controller.update(goodIdentification)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.goodIdentification == goodIdentification

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        goodIdentification = new GoodIdentification(params).save(flush: true)
        controller.update(goodIdentification)

        then: "A redirect is issued to the show action"
        goodIdentification != null
        response.redirectedUrl == "/goodIdentification/show/$goodIdentification.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/goodIdentification/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def goodIdentification = new GoodIdentification(params).save(flush: true)

        then: "It exists"
        GoodIdentification.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(goodIdentification)

        then: "The instance is deleted"
        GoodIdentification.count() == 0
        response.redirectedUrl == '/goodIdentification/index'
        flash.message != null
    }
}
