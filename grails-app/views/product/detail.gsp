<%@ page import="net.codebuilders.mybusiness.ProductService" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <title><g:message code="default.detail.label" args="[product.name]"/></title>

</head>

<body>
<a href="#show-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<!-- COPIED IN -->
<div class="container">

    <div class="page-header">
        <h1><g:message code="default.detail.label" args="[product.name]"/></h1>
    </div> <!-- /.page-header -->


    <div class="row">

        <div class="col-md-5">
        <!-- left-detail -->

            <g:if test="${product?.photos}">
                <g:set var="large" value="${product.photos[1].photo.getCloudFile("large")}" />
                <g:set var="small" value="${product.photos[1].photo.getCloudFile("small")}" />
                <cb:zoomImage gallery="gallery_01" imageId="zoom_01" large="${large}" small="${small}"/>

                <div id="gallery_01">
                <g:each in="${product?.photos}">
                    <g:set var="large" value="${it.photo.getCloudFile("large")}" />
                    <g:set var="small" value="${it.photo.getCloudFile("small")}" />
                    <g:set var="thumb" value="${it.photo.getCloudFile("thumb")}" />
                    <cb:zoomGallery gallery="gallery_01" imageId="zoom_01" large="${large}" small="${small}" thumb="${thumb}"/>
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
        <div class="col-md-7">

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

        %{--Use ProductService--}%
            <g:set var="prodSvc" bean="productService"/>
            <div id="sizes">

                <g:if test="${pfaResults?.size > 0}">
                    <h2>Available Sizes</h2>

                    <div class="col-md-6">

                        <g:each in="${pfaResults}" status="i" var="pfaInstance">

                            <!-- test for size same as this product and highlight -->
                            <g:if test="${prodSvc.getSize(pfaInstance.product) == prodSvc.getSize(product)}">
                                <!-- test for out of stock, then test for web sell, then say in stock  -->
                                <g:if test="${pfaInstance.product.outOfStock}">
                                    <g:link controller="product" action="detail" id="${pfaInstance.product.id}"
                                            class="btn btn-info btn-lg btn-block">
                                        ${prodSvc.getSize(pfaInstance.product)} -
                                        <span style="color:red">
                                            <g:message code="product.outOfStock.label" default="Out of Stock"/>
                                        </span>
                                    </g:link>
                                </g:if>
                                <g:elseif test="${!pfaInstance.product.webSell}">
                                    <g:link controller="product" action="detail" id="${pfaInstance.product.id}"
                                            class="btn btn-info btn-lg btn-block">
                                        ${prodSvc.getSize(pfaInstance.product)} -
                                        <span style="color:red">
                                            <g:message code="product.webSell.message" default="Call for Availability"/>
                                        </span>
                                    </g:link>
                                </g:elseif>
                                <g:else>
                                    <g:link controller="product" action="detail" id="${pfaInstance.product.id}"
                                            class="btn btn-info btn-lg btn-block">
                                        ${prodSvc.getSize(pfaInstance.product)} -
                                        <span style="color:green">
                                            <g:message code="product.inStock.message" default="In Stock"/>
                                        </span>
                                    </g:link>
                                </g:else>

                            </g:if>
                            <g:else>
                                <!-- test for out of stock, then test for web sell, then say in stock  -->
                                <g:if test="${pfaInstance.product.outOfStock}">
                                    <g:link controller="product" action="detail" id="${pfaInstance.product.id}"
                                            class="btn btn-default btn-lg btn-block">
                                        ${prodSvc.getSize(pfaInstance.product)} -
                                        <span style="color:red">
                                            <g:message code="product.outOfStock.label" default="Out of Stock"/>
                                        </span>
                                    </g:link>
                                </g:if>
                                <g:elseif test="${!pfaInstance.product.webSell}">
                                    <g:link controller="product" action="detail" id="${pfaInstance.product.id}"
                                            class="btn btn-default btn-lg btn-block">
                                        ${prodSvc.getSize(pfaInstance.product)} -
                                        <span style="color:red">
                                            <g:message code="product.webSell.message" default="Call for Availability"/>
                                        </span>
                                    </g:link>
                                </g:elseif>
                                <g:else>
                                    <g:link controller="product" action="detail" id="${pfaInstance.product.id}"
                                            class="btn btn-default btn-lg btn-block">
                                        ${prodSvc.getSize(pfaInstance.product)} -
                                        <span style="color:green">
                                            <g:message code="product.inStock.message" default="In Stock"/>
                                        </span>
                                    </g:link>
                                </g:else>

                            </g:else>

                        </g:each> <!-- pfaResults -->

                    </div><!-- col -->

                </g:if>

            </div><!-- sizes -->

        </div> <!-- /.col -->
    <!-- end right-detail -->

    </div> <!-- ./row -->


<!-- test for out of stock, then test for web sell, then allow paypal
  later - test for option categories, if none remoteLink
  if some, formRemote - see ttlcal detail-->
    <div>
        <p>
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
        </p>
    </div>

    <p>

    <div id="social"></div>
</p>

</div> <!-- /.container -->







<script type="text/javascript">
    //initiate the plugin and pass the id of the div containing gallery images
    $("#zoom_01").elevateZoom({gallery:'gallery_01', cursor: 'pointer', galleryActiveClass: 'active', imageCrossfade: true, loadingIcon: 'http://www.elevateweb.co.uk/spinner.gif'});
</script>
<script type="text/javascript">
    //pass the images to Fancybox
    $("#zoom_01").bind("click", function(e) {
        var ez =   $('#zoom_01').data('elevateZoom');
        $.fancybox(ez.getGalleryList());
        return false;
    });
</script>
</body>
</html>
