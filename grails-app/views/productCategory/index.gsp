<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-productCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-productCategory" class="page-header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>

                <g:sortableColumn property="id"
                                  title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                <g:sortableColumn property="description"
                                  title="${message(code: 'default.description.label', default: 'default.description.label')}"/>

                <g:sortableColumn property="parent"
                                  title="${message(code: 'default.parent.label', default: 'default.parent.label')}"/>

                <g:sortableColumn property="lastUpdated"
                                  title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${productCategoryList}" status="i" var="pc">
                <tr>
                    <td>
                        <g:link action="show" id="${pc.id}">${pc.id}</g:link>
                    </td>
                    <td>${pc.description}</td>
                    <td>${pc.parent}</td>
                    <td><g:formatDate format="yyyy-MMM-dd" date="${pc.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${productCategoryCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${productCategoryCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>