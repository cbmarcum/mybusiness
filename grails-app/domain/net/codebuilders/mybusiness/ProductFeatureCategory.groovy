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
 * ProductFeatureCategory is a class for categories of product features
 * ex. color, range, size
 *
 * @author Carl Marcum
 *
 */
class ProductFeatureCategory {

    static constraints = {
        description(maxSize: 50)
        shortDescription(maxSize: 25)
    }

    String description = "" // ex. Pressure Gauge Range
    // to use in a list within a product type like Pressure Gauge
    String shortDescription = "" // ex. Range

}
