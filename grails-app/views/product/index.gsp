<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <g:if test="${params.category}">
        <g:set var="entityCategory" value="${params.category}"/>
    </g:if>
    <g:else>
        <g:set var="entityCategory" value="All Products"/>
    </g:else>

    <title><g:message code="default.list.label" args="[entityName]"/></title>

</head>

<body>

<div class="container">

    <a href="#list-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>
    <sec:access expression="hasRole('ROLE_ADMIN')">
        <g:render template="/common/subnav-create"/>
    </sec:access>


    <div id="list-product" class="row">
        <div class="col-sm-6">
            <h1><g:message code="default.list.label" args="[entityName]"/>&nbsp;<small>&nbsp;${entityCategory}</small>
            </h1>
        </div>

        <div class="col-sm-6">
            <g:render template="/product/menubar-search"/>
        </div>

    </div> <%-- /.row --%>

    <g:if test="${flash.message}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
    </g:if>

    <g:if test="${noticeList}">
        <g:each in="${noticeList}" status="i" var="noticeInstance">
            <div class="alert alert-info alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">Close</span></button>

                <h2>${fieldValue(bean: noticeInstance, field: "name")}</h2>

                <p>
                    ${noticeInstance.longDescription.encodeAsRaw()}
                </p>

            <!-- only create show link to administrator -->
                <sec:access expression="hasRole('ROLE_ADMIN')">
                    <p>
                        Display = <g:formatBoolean boolean="${noticeInstance.display}"/>
                        <br/>
                        From Date = <g:formatDate format="yyyy-MM-dd" date="${noticeInstance.fromDate}"/>
                        <br/>
                        Thru Date = <g:formatDate format="yyyy-MM-dd" date="${noticeInstance.thruDate}"/>
                        <br/>
                        <g:link controller="notice" action="show" id="${noticeInstance.id}">Edit Notice</g:link>
                    </p>
                </sec:access>
            </div> <!-- /.alert -->
        </g:each>
    </g:if>

    <div>Hover over image to zoom</div>

    <div class="table-responsive">
        <table class="table">

            <tbody>
            <g:each in="${productList}" status="i" var="product">
                <g:set var="large" value="${product.photos[0].photo.getCloudFile("large")}"/>
                <g:set var="small" value="${product.photos[0].photo.getCloudFile("small")}"/>

                <tr>
                    <td width="160px">
                        <cb:zoomImageByClass clazz="zoom_01" large="${large}" small="${small}"/>
                    </td>
                    <td>SKU:
                        <sec:access expression="hasRole('ROLE_ADMIN')">
                            <g:link action="show" id="${product.id}">${product.number}</g:link><br/>
                        </sec:access>
                        <sec:noAccess expression="hasRole('ROLE_ADMIN')">
                            <g:link action="detail" id="${product.id}">${product.number}</g:link><br/>
                        </sec:noAccess>
                        <b>${product?.name}</b><br/>
                        ${product?.shortDescription}<br/>
                        List Price: <g:formatNumber number="${product.listPrice}" type="currency"
                                                    currencyCode="USD"/><br/>

                        <g:if test="${product.variantGroupId}">
                            <g:link action="detail" id="${product.id}">Product details and variations</g:link><br/>
                        </g:if>

                        <g:else>
                            <g:link action="detail" id="${product.id}">Product details</g:link><br/>
                        </g:else>

                    </td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div><!-- table-responsive -->


    <g:if test="${productCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${productCount}" params="${params}"/>
        </div>
    </g:if>

</div> <!-- /.container -->

<script type="text/javascript">
    //initiate the plugin and pass the class of the div containing gallery images
    $(".zoom_01").elevateZoom({
        zoomWindowWidth: 200,
        zoomWindowHeight: 200
    });
</script>

</body>
</html>