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

class ContactController {

    // map of contact_names and email@address
    // names use underscores for URL's
    Map contactMap = grailsApplication.config.getProperty('mybusiness.contactMap', Map, ["My_Contact" : "my.contact@myfakedomain.com"])
    // list of the contactMap keys for select box
    List contactList = contactMap.keySet() as List

    def index() {

        def a = (int)Math.ceil(Math.random() * 9)+ ''
        def b = (int)Math.ceil(Math.random() * 9)+ ''
        def c = (int)Math.ceil(Math.random() * 9)+ ''
        def d = (int)Math.ceil(Math.random() * 9)+ ''
        def e = (int)Math.ceil(Math.random() * 9)+ ''
        def f = (int)Math.ceil(Math.random() * 9)+ ''

        def code1 = a + b + c + d + e

        params.captcha1 = code1
        params.captcha2 = f

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

            boolean captchaValid = false

            captchaValid = captchaValidate(params.captcha1, params.captcha2, params.captchaInput)

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


            } else {
                // captcha not ok
                log.info "Captcha did not match"
                flash.message = "Captcha code did not match"
                render(view: 'index', model: [orderContact: orderContact, contactList: contactList])
            }

        } // orderContact.validate else

        // render the email view
        [orderContact: orderContact, params: params]
    } // email action

    boolean captchaValidate(String captcha1, String captcha2, String answer) {

        int x, y, z
        try {
            x = Integer.parseInt(captcha1)
            y = Integer.parseInt(captcha2)
            z = Integer.parseInt(answer.trim())
        } catch (NumberFormatException nfe) {
            log.error(nfe.toString())
            return false
        }
        log.info("x = ${x}")
        log.info("y = ${y}")
        log.info("z = ${z}")

        if (x + y == z) {
            return true
        } else {
            return false
        }
    }

} // class
