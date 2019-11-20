/* 
 * Copyright 2016-2020 Code Builders, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

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
        comment blank: false
	}
    
	
}

