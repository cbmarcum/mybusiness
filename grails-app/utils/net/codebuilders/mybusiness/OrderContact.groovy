

package net.codebuilders.mybusiness

import grails.validation.Validateable


/**
 *
 * @author carl
 *
 * Class to hold contact info for contact form
 */

// @Validateable // because it's a non-domain class - package needs listed in config.groovy
class OrderContact implements Validateable {

    String mbContact

    String fullName
    
    String company

    String address

    String city

    String state
    
    String postal

    String email

    String phone

    // String fax

    String comment

    static constraints = {
		fullName blank: false
		email (email: true, blank: false)
        phone blank: false
	}
    
	
}

