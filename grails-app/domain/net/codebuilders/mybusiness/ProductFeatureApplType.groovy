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

/**
 * Enum class to distinguish types of product features.
 *
 * REQUIRED_FEATURE - a "red" item by product number
 * STANDARD_FEATURE - the standard color of an item than can have optional colors
 * OPTIONAL_FEATURE - an optional color for an item that has a standard color
 * SELECTABLE_FEATURE - and item that requires selection of color
 */
public enum ProductFeatureApplType {

    OPTIONAL_FEATURE('Optional'),
    REQUIRED_FEATURE('Required'),
    STANDARD_FEATURE('Standard'),
    SELECTABLE_FEATURE('Selectable')

    static constraints = {
    }

    // Required, Optional, Standard
    // sub-types - Selectable
    String name

    ProductFeatureApplType(String name) {
        this.name = name
    }

    static list() {
        [OPTIONAL_FEATURE, REQUIRED_FEATURE, STANDARD_FEATURE, SELECTABLE_FEATURE]
    }

}