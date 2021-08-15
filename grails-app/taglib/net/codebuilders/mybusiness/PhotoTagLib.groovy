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
 * Tag Library class for use with displaying product images using the "Sell" Bootstrap theme.
 *
 * @author Carl Marcum
 */
class PhotoTagLib {

    static namespace = "cb" // <cb:bsPaginate>

    static defaultEncodeAs = [taglib: 'text']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    // s3 url's look like this:
    // https://s3.us-east-2.amazonaws.com/mbstore1/Photo/88/photo/51cMZHK7S0L_large.jpg

    /**
     * Returns HTML for image tags used in the "Sell" Bootstrap theme product list page.
     *
     * @attr clazz REQUIRED The imageId attribute
     * @attr large REQUIRED The large image URL
     * @attr alt REQUIRED The alt attribute
     */
    def imageByClass = { attrs, body ->

        // <cb:imageByClass clazz="zoom_01" large="Photo/1/photo/IE9413_XX_43_large.jpg" alt="something"/>
        // <cb:zoomImageByClass clazz="img-fluid" large="${large}" alt="something"/>
        // TODO: get these from config
        // def basePath = 'storage'
        // def bucket = 'uploads'
        // def basePath = 'https://s3.us-east-2.amazonaws.com'
        def basePath = grailsApplication.config.mybusiness.storage.basePath
        def bucket = grailsApplication.config.mybusiness.storage.bucket // asw s3

        // TODO: add alt and title to image
        def clazz = attrs.clazz
        def cloudUrlLarge = attrs.large
        def alt = attrs.alt

        // out << "<img class=\"${clazz}\" src=\"/${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"
        // <img src="img/mpp/camo-hoodie-green_large.jpg" alt="product" class="img-fluid"/>
        out << "<img class=\"${clazz}\" src=\"${basePath}/${bucket}/${cloudUrlLarge}\" alt=\"${alt}\"/>"
    }
    
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
        // def basePath = 'storage'
        // def bucket = 'uploads'
        // def basePath = 'https://s3.us-east-2.amazonaws.com'
        def basePath = grailsApplication.config.mybusiness.storage.basePath
        def bucket = grailsApplication.config.mybusiness.storage.bucket // asw s3

        // TODO: add alt and title to image
        def imageId = attrs.imageId
        def cloudUrlLarge = attrs.large
        def cloudUrlSmall = attrs.small

        // out << "<img id=\"${imageId}\" src=\"/${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"
        out << "<img id=\"${imageId}\" src=\"${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"${basePath}/${bucket}/${cloudUrlLarge}\"/>"

    }

    /**
     * Returns HTML for image tags used in the "Sell" Bootstrap theme product detail page.
     *
     * @attr large REQUIRED The large image URL
     * @attr footer REQUIRED The data-footer attribute
     * @attr alt REQUIRED The alt attribute
     */
    def zoomGallery = { attrs, body ->

        // <cb:zoomGallery imageId="zoom_01" large="Photo/1/photo/IE9413_XX_43_large.jpg"  />
        // TODO: get these from config
        // def basePath = 'storage'
        // def bucket = 'uploads'
        // def basePath = 'https://s3.us-east-2.amazonaws.com'
        def basePath = grailsApplication.config.mybusiness.storage.basePath
        def bucket = grailsApplication.config.mybusiness.storage.bucket // asw s3

        // TODO: add alt and title to image
        // def imageId = attrs.imageId
        def cloudUrlLarge = attrs.large
        def footer = attrs.footer
        def alt = attrs.alt
        // def cloudUrlSmall = attrs.small
        // def cloudUrlThumb = attrs.thumb

        /*
        <a class="glightbox d-block mb-4" href="img/mpp/camo-hoodie-green_large.jpg" data-title="Modern Jacket 1 - Caption text" data-gallery="product-gallery">
        <div data-bs-toggle="zoom" data-image="img/mpp/camo-hoodie-green_large.jpg">
        <img class="img-fluid" src="img/mpp/camo-hoodie-green_large.jpg" alt="Modern Jacket 1" >
        </div>
        </a>
        
         */
        out << "<a class=\"glightbox d-block mb-4\" href=\"${basePath}/${bucket}/${cloudUrlLarge}\" "
        out << "data-title=\"${footer}\" data-gallery=\"product-gallery\" >"
        out << "<div data-bs-toggle=\"zoom\" data-image=\"${basePath}/${bucket}/${cloudUrlLarge}\" >"
        out << "<img class=\"img-fluid\"  src=\"${basePath}/${bucket}/${cloudUrlLarge}\" alt=\"${alt}\" />"
        out << "</div>"
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
        // def basePath = 'storage'
        // def bucket = 'uploads'
        // def basePath = 'https://s3.us-east-2.amazonaws.com'
        def basePath = grailsApplication.config.mybusiness.storage.basePath
        def bucket = grailsApplication.config.mybusiness.storage.bucket // asw s3

        // TODO: add alt and title to image
        def clazz = attrs.clazz
        def cloudUrlLarge = attrs.large
        def cloudUrlSmall = attrs.small

        // out << "<img class=\"${clazz}\" src=\"/${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"/${basePath}/${bucket}/${cloudUrlLarge}\"/>"
        out << "<img class=\"${clazz}\" src=\"${basePath}/${bucket}/${cloudUrlSmall}\" data-zoom-image=\"${basePath}/${bucket}/${cloudUrlLarge}\"/>"
    }

    /**
     * Returns HTML for the ElevateZoom image zoom javascript library.
     * Similar to zoomImageByClass except doesn't add basePath and bucket to URL
     * Added for eBay images which are not stored locally.
     *
     * @attr clazz REQUIRED The imageId attribute
     * @attr large REQUIRED The large image URL
     * @attr small REQUIRED The small image URL
     */
    def zoomWebImageByClass = { attrs, body ->

        // <cb:zoomWebImageByClass class="zoom_01" large="http://some.domain/IE9413_XX_43_large.jpg" small="http://some.domain/IE9413_XX_43_small.jpg/>

        // TODO: add alt and title to image
        def clazz = attrs.clazz
        def cloudUrlLarge = attrs.large
        def cloudUrlSmall = attrs.small

        out << "<img class=\"${clazz}\" src=\"${cloudUrlSmall}\" data-zoom-image=\"${cloudUrlLarge}\"/>"

    }



    /**
     * Returns HTML for an image.
     *
     * @attr image REQUIRED The imageId attribute
     */
    def image = { attrs, body ->

        // <cb:image image="Photo/1/photo/IE9413_XX_43_large.jpg" />
        // TODO: get these from config
        // def basePath = 'storage'
        // def bucket = 'uploads'
        // def basePath = 'https://s3.us-east-2.amazonaws.com'
        def basePath = grailsApplication.config.mybusiness.storage.basePath
        def bucket = grailsApplication.config.mybusiness.storage.bucket // asw s3

        // TODO: add alt and title to image
        def cloudUrl = attrs.image

        // out << "<img src=\"/${basePath}/${bucket}/${cloudUrl}\" />"
        out << "<img src=\"${basePath}/${bucket}/${cloudUrl}\" />"

    }

}
