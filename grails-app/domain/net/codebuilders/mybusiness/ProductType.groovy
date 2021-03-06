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

/**
 * Enum class to distinguish products such as finished good, digital good,
 * configurable good, configurable good configuration
 *
 * @author Carl Marcum
 */
public enum ProductType {

    FINISHED_GOOD('Finished Good'),
    DIGITAL_GOOD('Digital Good'),
    CONFIG_GOOD('Configurable Good'),
    CONFIG_GOOD_CONFIG('Configurable Good Configuration')

    static constraints = {
        name(maxSize: 60)
    }

    String name

    ProductType(String name) {
        this.name = name
    }

    static list() {
        [FINISHED_GOOD, DIGITAL_GOOD, CONFIG_GOOD, CONFIG_GOOD_CONFIG]
    }
}
