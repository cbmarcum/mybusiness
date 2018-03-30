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

import com.bertramlabs.plugins.selfie.Attachment
// import com.bertramlabs.plugins.selfie.AttachmentUserType

/**
 * Domain class that represents a photo or image to relate to a product.
 * Photo image file will be stored on local filesystem or in cloud based on configuration.
 * Multiple styles (sizes) can be stored for each image and retrieved by style.
 *
 * @author Carl Marcum
 */
class Photo {

    String name
    String alt // for img alt tag
    String title // for img title tag
    Attachment photo
    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp

    static constraints = {
        photo contentType: ['image/png','image/jpeg'], fileSize:1024*1024 // 1mb
        name(maxSize: 50)
        alt(maxSize: 50, nullable: true)
        title(maxSize: 50, nullable: true)
    }

    // mode options - fit, crop, scale
    static attachmentOptions = [
            photo: [
                    styles: [
                            thumb: [width: 50, height: 50, mode: 'fit'],
                            small: [width: 150, height: 150, mode: 'scale'],
                            large: [width: 400, height: 400, mode: 'scale']
                    ]
            ]
    ]

    static embedded = ['photo'] //required


}
