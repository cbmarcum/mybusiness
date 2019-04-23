<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'productFeatureCategory.label', default: 'ProductFeatureCategory')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-productFeatureCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                 default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-productFeatureCategory" class="page-header">
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

                <g:sortableColumn property="shortDescription"
                                  title="${message(code: 'product.shortDescription.label', default: 'product.shortDescription.label')}"/>

                <g:sortableColumn property="sequenceNum"
                                  title="${message(code: 'default.sequenceNum.label', default: 'default.sequenceNum.label')}"/>

                <g:sortableColumn property="lastUpdated"
                                  title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${productFeatureCategoryList}" status="i" var="pfc">
                <tr>
                    <td>
                        <g:link action="show" id="${pfc.id}">${pfc.id}</g:link>
                    </td>
                    <td>${pfc.description}</td>
                    <td>${pfc.shortDescription}</td>
                    <td>${pfc.sequenceNum}</td>
                    <td><g:formatDate format="yyyy-MMM-dd" date="${pfc.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${productFeatureCategoryCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${productFeatureCategoryCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>