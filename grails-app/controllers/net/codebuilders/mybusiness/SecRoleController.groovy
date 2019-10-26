package net.codebuilders.mybusiness

import static org.springframework.http.HttpStatus.*
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class SecRoleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SecRole.list(params), model:[secRoleCount: SecRole.count()]
    }

    def show(SecRole secRole) {
        respond secRole
    }

    def create() {
        respond new SecRole(params)
    }

    @Transactional
    def save(SecRole secRole) {
        if (secRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secRole.errors, view:'create'
            return
        }

        secRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'secRole.label', default: 'SecRole'), secRole.id])
                redirect secRole
            }
            '*' { respond secRole, [status: CREATED] }
        }
    }

    def edit(SecRole secRole) {
        respond secRole
    }

    @Transactional
    def update(SecRole secRole) {
        if (secRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (secRole.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond secRole.errors, view:'edit'
            return
        }

        secRole.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'secRole.label', default: 'SecRole'), secRole.id])
                redirect secRole
            }
            '*'{ respond secRole, [status: OK] }
        }
    }

    @Transactional
    def delete(SecRole secRole) {

        if (secRole == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        secRole.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'secRole.label', default: 'SecRole'), secRole.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'secRole.label', default: 'SecRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
