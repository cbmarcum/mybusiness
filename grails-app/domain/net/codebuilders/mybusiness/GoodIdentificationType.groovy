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

/**
 * Enum class used to define types of GoodIdentification.
 *
 * SKU - Stock-Keeping Unit
 * UPCA - Universal Product Code - American
 * UPCE - Universal Product Code - European
 * MANUFACTURER_ID - Manufacturer Id
 * ISBN - International Standard Book Number
 * OTHER_ID - Other Id
 *
 * @author Carl Marcum
 */
public enum GoodIdentificationType {

    SKU('Stock-Keeping Unit'),
    UPCA('Universal Product Code - American'),
    UPCE('Universal Product Code - European'),
    MANUFACTURER_ID('Manufacturer Id'),
    ISBN('International Standard Book Number'),
    OTHER_ID('Other Id')

    static constraints = {

    }

    String name

    GoodIdentificationType(String name) {
        this.name = name
    }

    static list() {
        [SKU, UPCA, UPCA, MANUFACTURER_ID, ISBN, OTHER_ID]
    }

}
