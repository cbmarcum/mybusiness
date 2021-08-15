<!doctype html>
<html lang="en">
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
        <title><g:message code="default.detail.label" args="[product.name]"/></title>

        <!-- facebook open graph meta tags -->
        <g:render template="fb-meta-tags-detail"/>

    <!-- jQuery Zoom-->
    <asset:javascript src="vendor/jquery-zoom/jquery.zoom.js"/>

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

    <section class="product-details">
        <div class="container">
            <div class="row"> <%-- big row, images and content --%>
                <div class="col-lg-4 col-xl-3 pt-4 order-2 order-lg-1">
                    <%-- for each photo tied to product --%>
                    <g:if test="${product?.photos}">
                        <g:each in="${product?.photos}">
                            <g:set var="large" value="${it.photo.getCloudFile("large")}"/>
                            <g:set var="footer" value="${product?.name}"/>
                            <cb:zoomGallery large="${large}" footer="${footer}" alt="${it.alt}"/>
                        </g:each>

                    </g:if>


                </div>
                <div class="col-lg-8 col-xl-9 ps-lg-5 pt-4 order-1 order-lg-2">

                    <div style="top: 100px;" class="sticky-top">
                        <h1 class="h2 mb-4">${product?.name} </h1>
                        <div class="d-flex flex-column flex-sm-row align-items-sm-center justify-content-sm-between mb-4">
                            <ul class="list-inline mb-2 mb-sm-0">
                                <g:if test="${product?.listPrice}">
                                    <li class="list-inline-item h4 fw-light mb-0"><g:formatNumber number="${product.listPrice}" type="currency" currencyCode="USD"/>
                                    </li>
                                </g:if>
                            </ul>

                        </div>

                        <g:if test="${product?.shortDescription}">
                            <div class="mb-4">
                                ${product?.shortDescription.encodeAsRaw()}
                            </div>
                        </g:if>

                        <g:if test="${variantMap}">

                            <g:if test="${variantMap?.size() > 1}" >
                                <div class="mb-4 text-muted">
                                    If a variation combination is unavailable, try changing the other type.
                                    For instance, a certain size may not be available in certain colors or <em>vice versa</em>.
                                </div>
                        </g:if>

                        <div class="row">

                            <g:each var="variant" in="${variantMap}">
                                <div class="col-sm-6 col-lg-12 detail-option mb-3">
                                    <h6 class="detail-option-heading">${variant.key} <span>(required)</span></h6>

                                    <g:each var="button" in="${variant.value}">
                                        <!-- test for size same as this product and highlight -->
                                        <g:if test="${button.id == product.id}">
                                            <!-- test msg value -->
                                            <g:if test="${button.msg == 'Out of Stock'}">
                                                <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-sm btn-outline-secondary detail-option-btn-label active">
                                                    ${button.description}
                                                </g:link>
                                            </g:if>
                                            <g:elseif test="${button.msg == 'Call for Availability'}">
                                                <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-sm btn-outline-secondary detail-option-btn-label active">
                                                    ${button.description}
                                                </g:link>
                                            </g:elseif>
                                            <g:else>
                                                <g:link controller="product" action="detail" id="${button.id}"
                                                    class="btn btn-sm btn-outline-secondary detail-option-btn-label active">
                                                    ${button.description}
                                                </g:link>
                                            </g:else>

                                        </g:if>
                                        <g:elseif test="${button.msg == 'No Match'}">
                                            <button type="button" class="btn btn-sm btn-outline-secondary detail-option-btn-label disabled">
                                                <span style="color:red">${button.description}</span></button>
                                            </g:elseif>
                                            <g:else>
                                                <!-- test msg value -->
                                                <g:if test="${button.msg == 'Out of Stock'}">
                                                    <g:link controller="product" action="detail" id="${button.id}"
                                                        class="btn btn-sm btn-outline-secondary detail-option-btn-label">
                                                        ${button.description}
                                                    </g:link>
                                                </g:if>
                                                <g:elseif test="${button.msg == 'Call for Availability'}">
                                                    <g:link controller="product" action="detail" id="${button.id}"
                                                        class="btn btn-sm btn-outline-secondary detail-option-btn-label">
                                                        ${button.description}
                                                    </g:link>
                                                </g:elseif>
                                                <g:else>
                                                    <g:link controller="product" action="detail" id="${button.id}"
                                                        class="btn btn-sm btn-outline-secondary detail-option-btn-label">
                                                        ${button.description}
                                                    </g:link>
                                                </g:else>
                                            </g:else>
                                        </g:each>
                                </div> <%-- ./col --%>
                            </g:each> <%-- in variantMap --%>
                        </div> <%-- ./row --%>
                        </g:if> <%-- variantMap --%>




                    <%-- test for out of stock, then test for web sell, then allow paypal
                    later - test for option categories, if none remoteLink
                    if some, formRemote - see ttlcal detail --%>
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
                                <button type="button" onclick="jsAddToCart('${product.id}');" class="btn btn-dark btn-lg mb-1" rel="nofollow">
                                    <i class="fa fa-shopping-cart mr-2"></i>Add to Cart</button>
                                </g:else>

                        </div> <!-- /.col -->

                        <div class="col-sm-6">
                            <div id="fb-product"></div>
                        </div>
                    </div> <!-- ./row -->



                </div> <%-- ./sticky-top --%>

            </div> <%-- right column --%>

        </div> <%-- ./row big one images and detail content--%>
    </div> <%-- ./container --%>
</section>

<section class="mt-5">
    <div class="container">
        <ul role="tablist" class="nav nav-tabs flex-column flex-sm-row">
            <li class="nav-item">
                <a data-toggle="tab" href="#description" role="tab" class="nav-link detail-nav-link active">Description</a>
            </li>
        </ul>
        <div class="tab-content py-4">
            <div id="description" role="tabpanel" class="tab-pane active px-3">
                <g:if test="${product?.longDescription}">
                    <div>
                        ${product?.longDescription.encodeAsRaw()}
                    </div>
                </g:if>
            </div>

        </div>
    </div>
</section>

<!-- add facebook buttons to product -->
<script>
    document.getElementById("fb-product").innerHTML = '<div class="fb-like" data-href="${grailsApplication.config.grails.serverURL}${request.forwardURI}" data-layout="button_count" data-action="like" data-show-faces="false" data-size="large" data-share="true" data-colorscheme="dark"></div>';
</script>

</body>
</html>
