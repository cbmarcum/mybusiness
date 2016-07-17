package net.codebuilders.mybusiness

import grails.test.mixin.*
import spock.lang.*

@TestFor(SecUserSecRoleController)
@Mock(SecUserSecRole)
class SecUserSecRoleControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.secUserSecRoleList
            model.secUserSecRoleCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.secUserSecRole!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def secUserSecRole = new SecUserSecRole()
            secUserSecRole.validate()
            controller.save(secUserSecRole)

        then:"The create view is rendered again with the correct model"
            model.secUserSecRole!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            secUserSecRole = new SecUserSecRole(params)

            controller.save(secUserSecRole)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/secUserSecRole/show/1'
            controller.flash.message != null
            SecUserSecRole.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def secUserSecRole = new SecUserSecRole(params)
            controller.show(secUserSecRole)

        then:"A model is populated containing the domain instance"
            model.secUserSecRole == secUserSecRole
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def secUserSecRole = new SecUserSecRole(params)
            controller.edit(secUserSecRole)

        then:"A model is populated containing the domain instance"
            model.secUserSecRole == secUserSecRole
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/secUserSecRole/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def secUserSecRole = new SecUserSecRole()
            secUserSecRole.validate()
            controller.update(secUserSecRole)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.secUserSecRole == secUserSecRole

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            secUserSecRole = new SecUserSecRole(params).save(flush: true)
            controller.update(secUserSecRole)

        then:"A redirect is issued to the show action"
            secUserSecRole != null
            response.redirectedUrl == "/secUserSecRole/show/$secUserSecRole.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/secUserSecRole/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def secUserSecRole = new SecUserSecRole(params).save(flush: true)

        then:"It exists"
            SecUserSecRole.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(secUserSecRole)

        then:"The instance is deleted"
            SecUserSecRole.count() == 0
            response.redirectedUrl == '/secUserSecRole/index'
            flash.message != null
    }
}
