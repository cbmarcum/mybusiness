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
 * Domain class that represents an MS Excel xls or OpenDocument ods spreadsheet for importing product data.
 * Spreadsheet file will be stored on local filesystem or in cloud based on configuration.
 *
 * @author Carl Marcum
 */
class ImportSheet {

    String name
    // String alt // for img alt tag
    // String title // for img title tag
    Attachment sheet
    ImportSheetStatusType importSheetStatusType = ImportSheetStatusType.READY
    Date dateCreated // auto timestamp
    Date lastUpdated // auto timestamp

    static constraints = {
        sheet contentType: ['application/vnd.ms-excel', 'application/vnd.oasis.opendocument.spreadsheet'],
                fileSize:5*1024*1024 // 5mb
        name(maxSize: 50)
        // alt(maxSize: 50, nullable: true)
        // title(maxSize: 50, nullable: true)
    }

    // no static attachmentOptions like Photos domain

    static embedded = ['sheet'] //required

}
