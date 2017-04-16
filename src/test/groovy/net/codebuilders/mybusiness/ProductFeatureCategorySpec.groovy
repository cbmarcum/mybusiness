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
@TestFor(ProductFeatureCategory)
class ProductFeatureCategorySpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test description can have a maximum of 50 characters"() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.description = str

        then: 'description validation fails'
        !domain.validate(['description'])
        domain.errors['description'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.description = str

        then: 'description validation passes'
        domain.validate(['description'])
    }

    void "test shortDescription can have a maximum of 25 characters"() {
        when: 'for a string of 51 characters'
        String str = 'a' * 51
        domain.shortDescription = str

        then: 'shortDescription validation fails'
        !domain.validate(['shortDescription'])
        domain.errors['shortDescription'].code == 'maxSize.exceeded'

        when: 'for a string of 50 characters'
        str = 'a' * 50
        domain.shortDescription = str

        then: 'shortDescription validation passes'
        domain.validate(['shortDescription'])
    }

}
