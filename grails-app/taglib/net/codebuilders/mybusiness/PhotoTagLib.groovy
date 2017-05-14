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

class PhotoTagLib {

    static namespace = "cb" // <cb:bsPaginate>

    static defaultEncodeAs = [taglib: 'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    /**
     * Returns HTML for the ElevateZoom image zoom javascript library.
     * Includes the thumbnail gallery for additional images.
     *
     * @attr ProductId REQUIRED The id of the product to return images for.
     */
    def imageWithGallery = { attrs, body ->

        def basePath = 'storage'
        def bucket = 'uploads'
        def product = Product.get(attrs.productId)


        def cloudUrlLarge = product.photos[0].photo.getCloudFile('large')
        def cloudUrlSmall = product.photos[0].photo.getCloudFile('small')
        // def cloudUrlThumb = product.photos[0].photo.getCloudFile('thumb')

        out << "<img id=\"zoom_01\" src=\"/${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"

        // create the gallery
        out << "<div id=\"${attrs.gallery}\">"

        product.photos.each {
            cloudUrlLarge = it.photo.getCloudFile('large')
            cloudUrlSmall = it.photo.getCloudFile('small')
            def cloudUrlThumb = it.photo.getCloudFile('thumb')

            out << "<a href=\"#\" data-image=\"/${basePath}/${bucket}/${cloudUrlSmall}\""
            out << " data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"
            // out << "\\n"
            out << "<img id=\"${attrs.imageId}\" src=\"/${basePath}/${bucket}/${cloudUrlThumb}\" />"
            // out << "\\n"
            out << "</a>"
            // out << "\\n"
        }


        out << "</div>"

    }

}
