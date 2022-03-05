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

class GapiService {

    // def grailsApplication

    def getPlaceDetails(String placeId, String placesKey) {

        log.info("entered GapiService getPlaceDetails...")
        // had trouble getting json to work
        def base = "https://maps.googleapis.com/maps/api/place/details/xml?"

        def qs = []
        qs << "placeid=${placeId}"
        qs << "key=${placesKey}"
        def url = new URL(base + qs.join("&"))
        log.info("gapi url: ${url}")
        def connection = url.openConnection()

        def result = [:]

        if (connection.responseCode == 200) {

            log.info("connection response: OK")

            def xml = connection.content.text
            log.info("XML: ${xml}")
            def response = new XmlSlurper().parseText(xml)

            result.rating = response.result.rating.text() as String
            log.info("result.rating = ${result.rating}")

            // created an ArrayList
            def reviews = []
            // loop through xml result and add to reviews
            response.result.review.each {
                Map review = [:]
                String photoUrl = it.profile_photo_url as String
                String text = it.text as String
                String rating = it.rating.text() as String
                String relativeTime = it.relative_time_description.text() as String
                review.put("profile_photo_url", photoUrl)
                review.put("text", text)
                review.put("rating", rating)
                review.put("relative_time_description", relativeTime)
                reviews << review
            }

            result.put("reviews", reviews)

        } else {
            log.error("GoogleMaps Service FAILED")
            log.error("gapi url: ${url}")
            log.error("Response Code: ${connection.responseCode}")
            log.error(" Message: ${connection.responseMessage}")
        }

        // hack to fix photo urls returned beginning with "//"

        log.info("result.reviews.class = ${result.reviews.class}") // ArrayList
        log.info("result.reviews.size = ${result.reviews.size()}") // 5
        result.reviews.each {
            log.info("it is a ${it.getClass()}")
            log.info(it.toString())

            if (it.profile_photo_url.startsWith("//")) {
                it.profile_photo_url = "http:${it.profile_photo_url}"
            }
        }

        return result // use when this is a service

    } // getPlaceDetails
}
