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
 * Tag Library class for the ElevateZoom image zoom javascript library.
 *
 * @author Carl Marcum
 */
class PhotoTagLib {

    static namespace = "cb" // <cb:bsPaginate>

    static defaultEncodeAs = [taglib: 'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]



    /**
     * Returns HTML for the ElevateZoom image zoom javascript library.
     * Used for the current image from gallery to zoom.
     *
     * @attr imageId REQUIRED The imageId attribute
     * @attr large REQUIRED The large image URL
     * @attr small REQUIRED The small image URL
     */
    def zoomImageById = { attrs, body ->

        // <cb:zoomImage imageId="zoom_01" large="Photo/1/photo/IE9413_XX_43_large.jpg" small="Photo/1/photo/IE9413_XX_43_small.jpg/>
        // TODO: get these from config
        def basePath = 'storage'
        def bucket = 'uploads'

        // TODO: add alt and title to image
        def imageId = attrs.imageId
        def cloudUrlLarge = attrs.large
        def cloudUrlSmall = attrs.small

        out << "<img id=\"${imageId}\" src=\"/${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"


    }

    /**
     * Returns HTML for the ElevateZoom image zoom javascript library.
     * Used for the thumbnail gallery to display additional images.
     *
     * @attr imageId REQUIRED The imageId attribute
     * @attr large REQUIRED The large image URL
     * @attr small REQUIRED The small image URL
     * @attr thumb REQUIRED The thumb image URL
     */
    def zoomGallery = { attrs, body ->

        // <cb:zoomImage imageId="zoom_01" large="Photo/1/photo/IE9413_XX_43_large.jpg" small="Photo/1/photo/IE9413_XX_43_small.jpg" thumb="Photo/1/photo/IE9413_XX_43_thumb.jpg" />
        // TODO: get these from config
        def basePath = 'storage'
        def bucket = 'uploads'

        // TODO: add alt and title to image
        def imageId = attrs.imageId
        def cloudUrlLarge = attrs.large
        def cloudUrlSmall = attrs.small
        def cloudUrlThumb = attrs.thumb

        out << "<a href=\"#\" data-image=\"/${basePath}/${bucket}/${cloudUrlSmall}\""
        out << " data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"
        out << "<img id=\"${imageId}\" src=\"/${basePath}/${bucket}/${cloudUrlThumb}\" />"
        out << "</a>"


    }

    /**
     * Returns HTML for the ElevateZoom image zoom javascript library.
     * Used for the current image from gallery to zoom.
     *
     * @attr clazz REQUIRED The imageId attribute
     * @attr large REQUIRED The large image URL
     * @attr small REQUIRED The small image URL
     */
    def zoomImageByClass = { attrs, body ->

        // <cb:zoomImage class="zoom_01" large="Photo/1/photo/IE9413_XX_43_large.jpg" small="Photo/1/photo/IE9413_XX_43_small.jpg/>
        // TODO: get these from config
        def basePath = 'storage'
        def bucket = 'uploads'

        // TODO: add alt and title to image
        def clazz = attrs.clazz
        def cloudUrlLarge = attrs.large
        def cloudUrlSmall = attrs.small

        out << "<img class=\"${clazz}\" src=\"/${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"

    }

    /**
     * Returns HTML for an image.
     *
     * @attr image REQUIRED The imageId attribute
     */
    def image = { attrs, body ->

        // <cb:image image="Photo/1/photo/IE9413_XX_43_large.jpg" />
        // TODO: get these from config
        def basePath = 'storage'
        def bucket = 'uploads'

        // TODO: add alt and title to image
        def cloudUrl = attrs.image

        out << "<img src=\"/${basePath}/${bucket}/${cloudUrl}\" />"


    }

}
