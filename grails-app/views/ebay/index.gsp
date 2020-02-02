<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">

        <g:if test="${params.categoryName}">
            <g:set var="entityCategory" value="${params.categoryName}"/>
        </g:if>
        <g:else>
            <g:set var="entityCategory" value="${message(code: 'subnavigation.ext-tabs.ebay.store')}"/>
        </g:else>

        <title><g:message code="default.list.label" args="[entityCategory]"/></title>
        <meta name="description" content="${g.message(code: 'meta.description.home')}"/>
    </head>

    <body>
        <div class="block block-secondary block-inverse p-y-md">
            <div class="container">
                <div class="row page-header">

                    <div class="col-sm-8">
                        <h1>Store&nbsp;<small>&nbsp;${entityCategory}
                            <g:if test="${params.keywords}"> - Search for ${params.keywords} </g:if>
                            </small></h1>
                    </div> <!-- /.page-header -->
                    <div class="col-sm-4">
                        <g:render template="/ebay/menubar-search"/>
                    </div>
                </div>

                <div class="alert alert-warning" role="alert">
                    <span class="icon icon-info-with-circle" aria-hidden="true"></span>
                    <span class="sr-only">Info:</span>
                    Items are displayed from our eBay store. Links will open eBay site in a new browser window.<br/>
                    <span class="visible-xs">Please scroll to the right to see full description.</span>
                </div>

                <g:if test="${flash.message}">
                    <div class="alert alert-warning" role="alert">${flash.message}</div>
                </g:if>

                <g:if test="${items?.size() == 0}">
                    <h2>No Results</h2>
                </g:if>

                <div class="table-responsive">
                    <table class="table">
                        <tbody>
                            <g:each in="${items}" status="i" var="item">
                                <tr>
                                    <td><img data-action="zoom" style="height: 140px;" src="${item.pictureURLLarge}" border="0">
                                    </td>
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
                </div>

                <div class="text-center">
                    <nav>
                        <cb:ebayPaginate total="${params.totalEntries}" params="${params}"/>
                    </nav>
                </div>

            </div> <!-- /.container -->
        </div> <!-- .block -->
    </body>
</html>
