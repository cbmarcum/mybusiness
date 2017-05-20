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

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Photo)
class PhotoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test name can have a maximum of 50 characters"() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.name = str

        then: 'name validation fails'
        !domain.validate(['name'])
        domain.errors['name'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.name = str

        then: 'name validation passes'
        domain.validate(['name'])
    }

    void "test alt can have a maximum of 50 characters"() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.alt = str

        then: 'alt validation fails'
        !domain.validate(['alt'])
        domain.errors['alt'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.alt = str

        then: 'alt validation passes'
        domain.validate(['alt'])
    }

    void "test title can have a maximum of 50 characters"() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.title = str

        then: 'title validation fails'
        !domain.validate(['title'])
        domain.errors['title'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.title = str

        then: 'title validation passes'
        domain.validate(['title'])
    }

    void "test alt can be null"() {
        when:
        domain.alt = null

        then:
        domain.validate(['alt'])
    }

    void "test title can be null"() {
        when:
        domain.title = null

        then:
        domain.validate(['title'])
    }

}
