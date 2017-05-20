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
 * Test class for Product domain
 *
 * @author Carl Marcum
 */
@TestFor(Product)
class ProductSpec extends Specification {

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

    void "test number can have a maximum of 50 characters"() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.number = str

        then: 'number validation fails'
        !domain.validate(['number'])
        domain.errors['number'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.number = str

        then: 'number validation passes'
        domain.validate(['number'])
    }

    void "test shortDescription can have a maximum of 250 characters"() {
        when: 'for a string of 251 characters'
        String str = 'a' * 251
        domain.shortDescription = str

        then: 'shortDescription validation fails'
        !domain.validate(['shortDescription'])
        domain.errors['shortDescription'].code == 'maxSize.exceeded'

        when: 'for a string of 250 characters'
        str = 'a' * 250
        domain.shortDescription = str

        then: 'shortDescription validation passes'
        domain.validate(['shortDescription'])
    }

    void "test longDescription can have a maximum of 2000 characters"() {
        when: 'for a string of 2001 characters'
        String str = 'a' * 2001
        domain.longDescription = str

        then: 'longDescription validation fails'
        !domain.validate(['longDescription'])
        domain.errors['longDescription'].code == 'maxSize.exceeded'

        when: 'for a string of 2000 characters'
        str = 'a' * 2000
        domain.longDescription = str

        then: 'longDescription validation passes'
        domain.validate(['longDescription'])
    }

    void "test largeDescription can have a maximum of 4095 characters"() {
        when: 'for a string of 4096 characters'
        String str = 'a' * 4096
        domain.largeDescription = str

        then: 'largeDescription validation fails'
        !domain.validate(['largeDescription'])
        domain.errors['largeDescription'].code == 'maxSize.exceeded'

        when: 'for a string of 4095 characters'
        str = 'a' * 4095
        domain.largeDescription = str

        then: 'largeDescription validation passes'
        domain.validate(['largeDescription'])
    }

    void "test salesDiscontinuationDate can be null"() {
        when:
        domain.salesDiscontinuationDate = null

        then:
        domain.validate(['salesDiscontinuationDate'])
    }

    void "test supportDiscontinuationDate can be null"() {
        when:
        domain.supportDiscontinuationDate = null

        then:
        domain.validate(['supportDiscontinuationDate'])
    }
}
