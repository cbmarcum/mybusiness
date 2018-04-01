<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main">

    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <g:if test="${params.returnAction}">
        <g:set var="entityCategory" value="${message(code: "subnavigation.ext-tabs.ebay.${params.returnAction}")}"/>
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
            <h1><g:message code="navigation.ext-tabs.ebay"/>&nbsp;<small>&nbsp;${entityCategory}</small></h1>
        </div>

        <div class="col-sm-6">
            <g:render template="/ebay/menubar-search"/>
        </div>
    </div> <%-- /.row .page-header --%>
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-warning" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Info:</span>
                <strong>For demonstration purposes only.</strong> Items shown are displayed from all eBay stores. Links will open eBay site in a new browser window.<br/>
                We have no control of these sites. Products may have been listed with incorrect categories.
            </div>
        </div>

        <div class="col-md-12">
            <g:if test="${flash.message}">
                <div class="alert alert-warning" role="alert">${flash.message}</div>
            </g:if>
        </div>
    </div> <%-- /.row --%>

    <g:if test="${items.size() == 0}">
        <h2>No Results</h2>
    </g:if>

    <table class="table">
        <tbody>
        <g:each in="${items}" status="i" var="item">
            <tr>
                <td><img src="${item.galleryURL}" border="0"></td>
                <td><a href="${item.viewItemURL}" target="_blank">${item.title}</a></td>
                <td>
                    <g:formatNumber number="${item.sellingStatus.currentPrice.text().toDouble()}"
                                    type="currency"
                                    currencyCode="${item.sellingStatus.currentPrice.@currencyId.text()}"/>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="text-center">
        <nav>
            <cb:ebayPaginate controller="ebay" total="${params.totalEntries}"/>
        </nav>
    </div>
</div> <%-- /.container --%>
</body>
</html>
