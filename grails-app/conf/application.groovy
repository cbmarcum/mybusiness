/**
 * Created by carl on 9/11/16.
 */
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'net.codebuilders.mybusiness.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'net.codebuilders.mybusiness.SecUserSecRole'
grails.plugin.springsecurity.authority.className = 'net.codebuilders.mybusiness.SecRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/index', access: ['permitAll']],
        [pattern: '/index.gsp', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/home/**', access: ['permitAll']],
        [pattern: '/contact/**', access: ['permitAll']],
        [pattern: '/ebay/**', access: ['permitAll']],
        [pattern: '/gapi/**', access: ['permitAll']],
        // everything below this line should be ['ROLE_ADMIN', 'isFullyAuthenticated()']]
        [pattern: '/admin/**', access: ['permitAll']],
        [pattern: '/notice/**', access: ['permitAll']],
        [pattern: '/rssFeed/**', access: ['permitAll']],
        // for ckeditor in notice
        [pattern: '/ck/standard/filemanager', access: ['permitAll']],
        [pattern: '/ck/standard/uploader', access: ['permitAll']],
        [pattern: '/ck/ofm/filemanager', access: ['permitAll']],
        [pattern: '/ck/ofm/filetree', access: ['permitAll']]

]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**', filters: 'JOINED_FILTERS']
]

// for tutorial only - remove after logout gsp is created
// grails.plugin.springsecurity.logout.postOnly = false

grails.bootstrap.skip = 'false'

// for contact form - override in application
// key is name displayed in form
// value is email address
// map of contact_names and email@address
// names use underscores for URL's
mybusiness.contactMap = ["My_Contact": "my.contact@myfakedomain.com"]

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "youracount@gmail.com"
        password = "yourpassword"
        props = ["mail.smtp.auth"                  : "true",
                 "mail.smtp.socketFactory.port"    : "465",
                 "mail.smtp.socketFactory.class"   : "javax.net.ssl.SSLSocketFactory",
                 "mail.smtp.socketFactory.fallback": "false"]
    }
}

// for mail plugin
grails.mail.overrideAddress = "me@myfakedomain.com"

// used for mail when no from address is given
grails.mail.default.from = "server@myfakedomain.com"



mybusiness.ebay.security.appname = "mybusiness-1234-5678-aaaa-bbbbccccdddd"
mybusiness.ebay.storeName = "mystorename"

// light or dark
mybusiness.recaptcha.style = "light"

// google places api key (dev key - override in production with domain specific key
// mybusiness.places.key = "YOUR_API_KEY"

// google places api id
// mybusiness.places.id = "YOUR_PLACES_ID"

// google map src
mybusiness.map.src = "https://www.google.com/maps/embed?pb=!1m19!1m8!1m3!1d48969.617373514775!2d-84.227148!3d39.9055628!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x883f7eb8cdfed0f1%3A0x7d07f86955cce608!2sWright+Brothers+Aeroplane%2C+3555+Hangar+Dr%2C+Vandalia%2C+OH+45377!3m2!1d39.903591399999996!2d-84.2050347!5e0!3m2!1sen!2sus!4v1482770754729"

// ckeditor used in notice


ckeditor {
    // config = "/js/myckconfig.js"
    skipAllowedItemsCheck = false
    defaultFileBrowser = "ofm"
    upload {
        basedir = "/uploads/"
        overwrite = false
        link {
            browser = true
            upload = false
            allowed = []
            denied = ['html', 'htm', 'php', 'php2', 'php3', 'php4', 'php5',
                      'phtml', 'pwml', 'inc', 'asp', 'aspx', 'ascx', 'jsp',
                      'cfm', 'cfc', 'pl', 'bat', 'exe', 'com', 'dll', 'vbs', 'js', 'reg',
                      'cgi', 'htaccess', 'asis', 'sh', 'shtml', 'shtm', 'phtm']
        }
        image {
            browser = true
            upload = true
            allowed = ['jpg', 'gif', 'jpeg', 'png']
            denied = []
        }
        flash {
            browser = false
            upload = false
            allowed = ['swf']
            denied = []
        }
    }
}

