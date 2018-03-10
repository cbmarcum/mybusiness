<%@ page import="net.codebuilders.mybusiness.ProductService" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <title><g:message code="default.detail.label" args="[product.name]"/></title>

</head>

<body>

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
                                        <button type="button" class="btn btn-default btn-lg btn-block">
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
    <div class="row">
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
            <div id="social"></div>
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
</body>
</html>
