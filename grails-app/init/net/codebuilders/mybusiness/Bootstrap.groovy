package net.codebuilders.mybusiness

import net.codebuilders.mybusiness.SecRole
import net.codebuilders.mybusiness.SecUser
import net.codebuilders.mybusiness.SecUserSecRole


/**
 * Created by carl on 7/8/16.
 */
class Bootstrap {
    def init = {

        def adminRole = new SecRole(authority: 'ROLE_ADMIN').save()
        def userRole = new SecRole(authority: 'ROLE_USER').save()

        def adminUser = new SecUser(username: 'admin', password: 'password').save()

        SecUserSecRole.create adminUser, adminRole

        SecUserSecRole.withSession {
            it.flush()
            it.clear()
        }

        assert SecUser.count() == 1
        assert SecRole.count() == 2
        assert SecUserSecRole.count() == 1

        println "Bootstrap Ran !!"
    }

}
