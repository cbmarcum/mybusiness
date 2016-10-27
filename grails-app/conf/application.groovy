/**
 * Created by carl on 9/11/16.
 */
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'net.codebuilders.mybusiness.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'net.codebuilders.mybusiness.SecUserSecRole'
grails.plugin.springsecurity.authority.className = 'net.codebuilders.mybusiness.SecRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/',                 access: ['permitAll']],
        [pattern: '/error',            access: ['permitAll']],
        [pattern: '/index',            access: ['permitAll']],
        [pattern: '/index.gsp',        access: ['permitAll']],
        [pattern: '/shutdown',         access: ['permitAll']],
        [pattern: '/assets/**',        access: ['permitAll']],
        [pattern: '/**/js/**',         access: ['permitAll']],
        [pattern: '/**/css/**',        access: ['permitAll']],
        [pattern: '/**/images/**',     access: ['permitAll']],
        [pattern: '/**/favicon.ico',   access: ['permitAll']],
        [pattern: '/contact/**',       access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**',             filters: 'JOINED_FILTERS']
]

// for tutorial only - remove after logout gsp is created
// grails.plugin.springsecurity.logout.postOnly = false

// for contact form - override in application
// key is name displayed in form
// value is email address
// map of contact_names and email@address
// names use underscores for URL's
mybusiness.contactMap = ["My_Contact" : "my.contact@myfakedomain.com"]

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "youracount@gmail.com"
        password = "yourpassword"
        props = ["mail.smtp.auth":"true",
                 "mail.smtp.socketFactory.port":"465",
                 "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback":"false"]
    }
}

// REMOVE AFTER DOCUMENTATION
// for mail plugin used on contact form FOR TEST ONLY - REMOVE FROM PRODUCTION
// grails.mail.overrideAddress = "carl.marcum@codebuilders.net"

// used for mail when no from address is given
grails.mail.default.from="server@myfakedomain.com"
