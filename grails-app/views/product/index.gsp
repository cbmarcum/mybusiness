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

    <div class="row page-header">
        <div class="col-sm-6">
        <h1><g:message code="default.list.label" args="[entityName]"/>&nbsp;<small>&nbsp;${entityCategory}</small></h1>
        </div>
        <div class="col-sm-6">
            <g:render template="/product/menubar-search"/>
        </div>

    </div> <%-- /.row .page-header --%>

    <g:if test="${flash.message}">
        <div class="alert alert-warning" role="alert">${flash.message}</div>
    </g:if>

    <sec:access expression="hasRole('ROLE_ADMIN')">
        <p>
            <g:link class="btn btn-default" controller="admin" action="index"><g:message
                    code="default.admin.home.label"/></g:link>
            <g:link class="btn btn-default" action="create"><g:message code="default.new.label"
                                                                       args="[entityName]"/></g:link>
        </p>
    </sec:access>

    <table class="table">

        <tbody>
        <g:each in="${productList}" status="i" var="product">
            <g:set var="large" value="${product.photos[0].photo.getCloudFile("large")}" />
            <g:set var="small" value="${product.photos[0].photo.getCloudFile("small")}" />

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
                <!-- test for out of stock, then test for web sell, then allow paypal  -->
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
                        <g:remoteLink controller="shoppingCart" action="add3" params="${[id: product.id]}"
                                      onSuccess="${remoteFunction(action: 'ajaxUpdateCartQty', update: 'cartQty')}"
                                      onFailure="alert('failure');"
                                      onComplete="alert('$product.name added to cart');">
                            <img src="https://www.paypal.com/en_US/i/btn/btn_cart_LG.gif" align="left"
                                 style="margin-right:7px;">
                        </g:remoteLink>
                    </g:else>
                    <div id="message"></div>

                    <div id="error"></div>
                </td>

            </tr>
        </g:each>
        </tbody>
    </table>


    <g:if test="${productCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${productCount}" params="${params}"/>
        </div>
    </g:if>

</div> <!-- /.container -->

<script type="text/javascript">
    //initiate the plugin and pass the class of the div containing gallery images
    $(".zoom_01").elevateZoom({
        zoomWindowWidth:200,
        zoomWindowHeight:200
    });
</script>

</body>
</html>