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
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GoodIdentificationService)
class GoodIdentificationServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test get value by product and type"() {
        given: "a product and a type"

        Product product = new Product(
                number: "123456",
                name: "My Product",
                productType: ProductType.FINISHED_GOOD)

        // product.save(flush: true, failOnError: true)

        GoodIdentification goodId = new GoodIdentification(
                value: "ABCDE",
                goodIdentificationType: GoodIdentificationType.OTHER_ID,
                product: product)
        // goodId.save(failOnError: true)

        when: "find value by product and type"

        String value = service.getValueByProductAndType(product, GoodIdentificationType.OTHER_ID)

        then: "should return ABCDE"

        assert value == "ABCDE"

    }

}
