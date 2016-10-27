/*
Copyright 2016 Code Builders, LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package net.codebuilders.mybusiness

import groovy.text.SimpleTemplateEngine
import org.springframework.mail.MailException
import grails.util.Environment
import com.megatome.grails.RecaptchaService

class ContactController {


    // def simpleCaptchaService
    RecaptchaService recaptchaService

    // map of contact_names and email@address
    // names use underscores for URL's
    Map contactMap = grailsApplication.config.getProperty('mybusiness.contactMap', Map, ["My_Contact" : "my.contact@myfakedomain.com"])
    // list of the contactMap keys for select box
    List contactList = contactMap.keySet() as List




    def index() {

        [contactList: contactList, params: params]
    }


    def email() {



        def orderContact = new OrderContact()

        orderContact.mbContact = params?.mbContact
        orderContact.fullName = params?.name
        orderContact.company = params?.company
        orderContact.email = params?.email1
        orderContact.address = params?.address
        orderContact.city = params?.city
        orderContact.state = params?.state
        orderContact.postal = params?.postal
        orderContact.phone = params?.phone
        orderContact.comment = params?.comment

        if (!orderContact.validate()) {

            render(view: 'index', model: [orderContact: orderContact, contactList: contactList])

        } else {

            boolean captchaValid = true

            if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
                captchaValid = false
            }

            if (captchaValid == true) {
                def date = new Date()

                def contactEmail = contactMap.get(orderContact.mbContact)

                if (params?.ccMe == "on") {

                    try {
                        sendMail {
                            to contactEmail
                            cc orderContact.email
                            // from "info@codebuilders.net"  // use grails.mail.default.from
                            subject "Website Contact"
                            html g.render(template:'/contact/contactEmail', model: [orderContact: orderContact])
                        }
                    } catch (MailException ex) {
                        log.error("Failed to send emails", ex)
                        return false
                    }

                } else {

                    try {
                        sendMail {
                            to contactEmail
                            // from "info@codebuilders.net" // grails.mail.default.from
                            subject "Website Contact"
                            html g.render(template:'/contact/contactEmail', model: [orderContact: orderContact])
                        }
                    } catch (MailException ex) {
                        log.error("Failed to send emails", ex)
                        return false
                    }
                }

                // pass contact to view
                flash['contact'] = orderContact

                // cleanup captcha
                recaptchaService.cleanUp(session)

                // return true



            } else {
                // captcha not ok
                log.info "Captcha did not match"
                flash.message = "Access code did not match"
                render(view: 'index', model: [orderContact: orderContact, contactList: contactList])
            }


        } // orderContact.validate else

        // render the email view
        [orderContact: orderContact, params: params]
    } // email action

} // class
