/**
 * Created by carl on 2/19/17.
 */

seed = {
    secRole(meta:[key:'id'], authority: 'ROLE_ADMIN')

    secUser(meta:[key:'id'], id: 1L, username: 'admin', password: 'password')

    // secUserSecRole(secUser: [username: 'admin'], secRole: [authority: 'ROLE_ADMIN'])
    // secUserSecRole(secUser: SecUser.get(1), secRole: SecRole.get(1))
    secUserSecRole(secUser: 1L, secRole: 1L)
}
