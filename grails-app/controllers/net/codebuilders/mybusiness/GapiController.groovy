package net.codebuilders.mybusiness

import groovy.json.JsonSlurper

class GapiController {

    // def grailsApplication

    def gapiService

    def index() {

        def result = [:]

        result = gapiService.getPlaceDetails("${grailsApplication.config.mybusiness.places.id}", "${grailsApplication.config.mybusiness.places.key}")

        [results: result] // plural key name for use in view

    } // index

}
