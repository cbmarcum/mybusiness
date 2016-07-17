package net.codebuilders.mybusiness

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class SecUserSecRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SecUserSecRole.list(params), model:[secUserSecRoleCount: SecUserSecRole.count()]
    }

    def show(SecUserSecRole secUserSecRole) {
        respond secUserSecRole
    }

    def create() {
        respond new SecUserSecRole(params)
    }

    @Transactional
    def save(SecUserSecRole secUserSecRole) {
        if (secUserSecRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secUserSecRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secUserSecRole.errors, view:'create'
            return
        }

        secUserSecRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secUserSecRole.label', default: 'SecUserSecRole'), secUserSecRole.id])
                redirect secUserSecRole
            }
            '*' { respond secUserSecRole, [status: CREATED] }
        }
    }

    def edit(SecUserSecRole secUserSecRole) {
        respond secUserSecRole
    }

    @Transactional
    def update(SecUserSecRole secUserSecRole) {
        if (secUserSecRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secUserSecRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secUserSecRole.errors, view:'edit'
            return
        }

        secUserSecRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'secUserSecRole.label', default: 'SecUserSecRole'), secUserSecRole.id])
                redirect secUserSecRole
            }
            '*'{ respond secUserSecRole, [status: OK] }
        }
    }

    @Transactional
    def delete(SecUserSecRole secUserSecRole) {

        if (secUserSecRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        secUserSecRole.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'secUserSecRole.label', default: 'SecUserSecRole'), secUserSecRole.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secUserSecRole.label', default: 'SecUserSecRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
