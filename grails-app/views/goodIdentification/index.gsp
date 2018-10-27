<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'goodIdentification.label', default: 'GoodIdentification')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-goodIdentification" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                             default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-goodIdentification" class="page-header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>
                <g:sortableColumn property="value"
                                  title="${message(code: 'default.value.label', default: 'Value')}"/>

                <g:sortableColumn property="goodIdentificationType"
                                  title="${message(code: 'goodIdentificationType.label', default: 'Good Identification Type')}"/>

                <g:sortableColumn property="product"
                                  title="${message(code: 'product.label', default: 'Product')}"/>

                <g:sortableColumn property="lastUpdated"
                                  title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${goodIdentificationList}" status="i" var="goodId">
                <tr>
                    <td>
                        <g:link action="show" id="${goodId.id}">${goodId.value}</g:link>
                    </td>
                    <td>${goodId.goodIdentificationType.name}</td>
                    <td>${goodId.product}</td>
                    <td><g:formatDate format="yyyy-MMM-dd" date="${goodId.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>


    <g:if test="${goodIdentificationCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${goodIdentificationCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>