<%@ page import="net.codebuilders.mybusiness.ProductService" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <title><g:message code="default.detail.label" args="[product.name]"/></title>

    <!-- facebook open graph meta tags -->
    <meta property="og:url" content="${grailsApplication.config.grails.serverURL}${request.forwardURI}"/>
    <meta property="og:title" content="${product.name}"/>
    <meta property="og:site_name" content="Code Builders, LLC Site"/>
    <meta property="og:description"
          content="${product?.shortDescription}" />
    <g:if test="${product?.photos}">
        <meta property="og:image"
              content="${product.photos[0].photo.url("large")}"/>
        <meta property="og:image:width" content="400"/>
        <meta property="og:image:height" content="400"/>
    </g:if>
    <g:else>
        <meta property="og:image"
              content="${grailsApplication.config.grails.serverURL}/assets/fb-mb-image-1200x630.png"/>
        <meta property="og:image:width" content="1200"/>
        <meta property="og:image:height" content="630"/>
    </g:else>

    <meta property="fb:app_id" content="${grailsApplication.config.fb.appid}"/>
    <meta property="fb:admins" content="${grailsApplication.config.fb.admins}"/>
    <meta property="og:type" content="product"/>

</head>

<body>

<%-- facebook page plugin placeholder--%>
<script>
    window.fbAsyncInit = function () {
        FB.init({
            appId: "${grailsApplication.config.fb.appid}",
            autoLogAppEvents: true,
            xfbml: true,
            version: 'v3.2'
        });
    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>

<!-- COPIED IN -->
<div class="container">

    <div class="page-header">
        <h1><g:message code="default.detail.label" args="[product.name]"/></h1>
    </div> <!-- /.page-header -->

    <div class="row">

        <div class="col-md-6">
        <!-- left-detail -->

            <g:if test="${product?.photos}">
                <g:set var="large" value="${product.photos[0].photo.getCloudFile("large")}"/>
                <g:set var="small" value="${product.photos[0].photo.getCloudFile("small")}"/>
                <cb:zoomImageById gallery="gallery_01" imageId="zoom_01" large="${large}" small="${small}"/>

                <div id="gallery_01">
                    <g:each in="${product?.photos}">
                        <g:set var="large" value="${it.photo.getCloudFile("large")}"/>
                        <g:set var="small" value="${it.photo.getCloudFile("small")}"/>
                        <g:set var="thumb" value="${it.photo.getCloudFile("thumb")}"/>
                        <cb:zoomGallery gallery="gallery_01" imageId="zoom_01" large="${large}" small="${small}"
                                        thumb="${thumb}"/>
                    </g:each>
                </div>

            </g:if>

            <g:if test="${product?.longDescription}">
                <div>
                    ${(product?.longDescription.encodeAsRaw())}
                </div>
            </g:if>

        </div> <!-- /.col -->
    <!-- end left-detail -->

    <!-- right-detail -->
        <div class="col-md-6">

            <div class="row">
                <table class="table borderless">
                    <tbody>

                    <g:if test="${product?.number}">
                        <tr>
                            <td><b><g:message code="product.number.label" default="Number"/></b></td>
                            <td>${fieldValue(bean: product, field: "number")}</td>
                        </tr>
                    </g:if>
                    <g:if test="${product?.name}">
                        <tr>
                            <td><b><g:message code="product.name.label" default="Name"/></b></td>
                            <td>${fieldValue(bean: product, field: "name")}</td>
                        </tr>
                    </g:if>
                    <g:if test="${product?.listPrice}">
                        <tr>
                            <td><b><g:message code="product.listPrice.label" default="List Price"/></b></td>
                            <td><g:formatNumber number="${product.listPrice}" type="currency" currencyCode="USD"/></td>
                        </tr>
                    </g:if>
                    </tbody>
                </table>
                <g:if test="${product?.largeDescription}">
                    <div>
                        ${product?.largeDescription.encodeAsRaw()}
                    </div>
                </g:if>

            </div> <!-- ./row -->

            <g:if test="${variantMap}">

                <h2>Product Variations</h2>
                <g:if test="${variantMap?.size() > 1}">
                    <p class="bg-info" style="color: #333; padding: 5px;">
                        If a variation combination is unavailable, try changing the other type.
                        For instance, a certain size may not be available in certain colors or <em>vice versa</em>.
                    </p>
                </g:if>
                <div class="row">
                    <g:each var="variant" in="${variantMap}">
                        <div class="col-sm-6">
                            <div id="${variant.key}">
                                <h3>${variant.key}</h3>
                                <g:each var="button" in="${variant.value}">
                                    <!-- test for size same as this product and highlight -->
                                    <g:if test="${button.id == product.id}">
                                        <!-- test msg value -->
                                        <g:if test="${button.msg == 'Out of Stock'}">
                                            <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-info btn-lg btn-block">
                                                ${button.description}
                                            </g:link>
                                        </g:if>
                                        <g:elseif test="${button.msg == 'Call for Availability'}">
                                            <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-info btn-lg btn-block">
                                                ${button.description}
                                            </g:link>
                                        </g:elseif>
                                        <g:else>
                                            <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-info btn-lg btn-block">
                                                ${button.description}
                                            </g:link>
                                        </g:else>

                                    </g:if>
                                    <g:elseif test="${button.msg == 'No Match'}">
                                        <button type="button" class="btn btn-default btn-lg btn-block disabled">
                                            <span style="color:red">${button.description}</span></button>
                                    </g:elseif>
                                    <g:else>
                                        <!-- test msg value -->
                                        <g:if test="${button.msg == 'Out of Stock'}">
                                            <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-default btn-lg btn-block">
                                                ${button.description}
                                            </g:link>
                                        </g:if>
                                        <g:elseif test="${button.msg == 'Call for Availability'}">
                                            <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-default btn-lg btn-block">
                                                ${button.description}
                                            </g:link>
                                        </g:elseif>
                                        <g:else>
                                            <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-default btn-lg btn-block">
                                                ${button.description}
                                            </g:link>
                                        </g:else>
                                    </g:else>

                                </g:each>
                            </div><!-- variant.key -->
                        </div> <!-- .col -->
                    </g:each>
                </div> <!-- ./row -->
            </g:if>

        </div><!-- /.col -->
    <!-- end right-detail -->

    </div> <!-- ./row -->


<!-- test for out of stock, then test for web sell, then allow paypal
  later - test for option categories, if none remoteLink
  if some, formRemote - see ttlcal detail-->
    <div class="row" style="padding-top: 20px">
        <div class="col-sm-6">

            <g:if test="${product.outOfStock}">
                <span style="color:red">
                    <g:message code="product.outOfStock.label" default="Out of Stock"/>
                </span>
            </g:if>
            <g:elseif test="${!product.webSell}">
                <span style="color:red">
                    <g:message code="product.webSell.message" default="Call for Availability"/>
                </span>
            </g:elseif>
            <g:else>
                <g:remoteLink controller="shoppingCart" action="add2" params="${[id: product.id]}"
                              onSuccess="${remoteFunction(action: 'ajaxUpdateCartQty', update: 'cartQty')}"
                              onComplete="alert('added to cart');">
                    <img src="https://www.paypal.com/en_US/i/btn/btn_cart_LG.gif">
                </g:remoteLink>
            </g:else>

        </div> <!-- /.col -->

        <div class="col-sm-6">
            <div id="fb-product"></div>
        </div>
    </div> <!-- ./row -->

</div> <!-- /.container -->


<script type="text/javascript">
    //initiate the plugin and pass the id of the div containing gallery images
    $("#zoom_01").elevateZoom({
        gallery: 'gallery_01',
        cursor: 'pointer',
        galleryActiveClass: 'active',
        imageCrossfade: true,
        loadingIcon: 'http://www.elevateweb.co.uk/spinner.gif'
    });
</script>
<script type="text/javascript">
    //pass the images to Fancybox
    $("#zoom_01").bind("click", function (e) {
        var ez = $('#zoom_01').data('elevateZoom');
        $.fancybox(ez.getGalleryList());
        return false;
    });
</script>

<!-- add facebook buttons to product -->
<script>
    document.getElementById("fb-product").innerHTML = '<div class="fb-like" data-href="${grailsApplication.config.grails.serverURL}${request.forwardURI}" data-layout="button_count" data-action="like" data-show-faces="false" data-size="large" data-share="true" data-colorscheme="dark"></div>';
</script>

</body>
</html>
