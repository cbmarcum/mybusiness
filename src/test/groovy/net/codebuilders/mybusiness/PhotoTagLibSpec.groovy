/*
 * *************************************************************
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * *************************************************************
 */

package net.codebuilders.mybusiness

import grails.test.mixin.*
import spock.lang.Specification
import org.springframework.mock.web.MockMultipartFile
import org.grails.plugins.testing.GrailsMockMultipartFile
import com.bertramlabs.plugins.selfie.*

/**
 * Test class for PhotoTagLib
 *
 * @author Carl Marcum
 */
@TestFor(PhotoTagLib)
@Mock([Photo, Product])
class PhotoTagLibSpec extends Specification {



    def "zoomImage returns correct html"() {

        given: "an image id and photos"

        def argsMap = [:]
        argsMap.imageId = "zoom_01"
        argsMap.large = "MyPhoto_large.jpg"
        argsMap.small = "MyPhoto_small.jpg"

        when: "tag is used for photo"
        def result = tagLib.zoomImage(argsMap)

        then: "correct code is returned"
        result == '<img id="zoom_01" src="/storage/uploads/MyPhoto_small.jpg" data-zoom-image="/storage/uploads/MyPhoto_large.jpg"/>'
    }

    def "zoomGallery returns correct html"() {

        given: "an image id and photos"
        def argsMap = [:]
        argsMap.imageId = "zoom_01"
        argsMap.large = "MyPhoto_large.jpg"
        argsMap.small = "MyPhoto_small.jpg"
        argsMap.thumb = "MyPhoto_thumb.jpg"

        when: "tag is used for photo"
        def result = tagLib.zoomGallery(argsMap)

        then: "correct code is returned"
        result == '<a href="#" data-image="/storage/uploads/MyPhoto_small.jpg" data-zoom-image="/storage/uploads/MyPhoto_large.jpg"/><img id="zoom_01" src="/storage/uploads/MyPhoto_thumb.jpg" /></a>'

    }


}
