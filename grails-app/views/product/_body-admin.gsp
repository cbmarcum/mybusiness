<div class="container">
    <a href="#list-product" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
            default="Skip to content&hellip;"/></a>

        <g:render template="/common/subnav-create"/>

    <div id="list-product" class="page-header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
                <tr>

                    <g:sortableColumn property="id"
                    title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                    <g:sortableColumn property="number"
                    title="${message(code: 'product.number.label', default: 'product.number.label')}"/>

                    <g:sortableColumn property="name"
                    title="${message(code: 'product.name.label', default: 'product.name.label')}"/>

                    <g:sortableColumn property="variantGroupId"
                    title="${message(code: 'product.variantGroupId.label', default: 'product.variantGroupId.label')}"/>

                    <g:sortableColumn property="listPrice"
                    title="${message(code: 'product.listPrice.label', default: 'product.listPrice.label')}"/>

                    <g:sortableColumn property="lastUpdated"
                    title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>

                </tr>
            </thead>
            <tbody>
                <g:each in="${productList}" status="i" var="product">
                    <tr>
                        <td>
                            <g:link action="show" id="${product.id}">${product.id}</g:link>
                            </td>
                            <td>${product.number}</td>
                        <td>${product.name}</td>
                        <td>${product.variantGroupId}</td>
                        <td><g:formatNumber number="${product.listPrice}" type="currency" currencyCode="USD"/></td>
                        <td><g:formatDate format="yyyy-MMM-dd" date="${product.lastUpdated}"/></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${productCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${productCount}" params="${params}"/>
        </div>
</g:if>

</div> <%-- /.container --%>
