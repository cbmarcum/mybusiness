<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-productFeatureAppl" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                             default="Skip to content&hellip;"/></a>

    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </ul>
    </div>

    <div id="list-productFeatureAppl" class="page-header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:if test="${flash.message}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
    </g:if>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>

                <g:sortableColumn property="id"
                                  title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                <g:sortableColumn property="product"
                                  title="${message(code: 'product.label', default: 'product.label')}"/>

                <g:sortableColumn property="productFeature"
                                  title="${message(code: 'productFeature.label', default: 'productFeature.label')}"/>

                <g:sortableColumn property="productFeatureApplType"
                                  title="${message(code: 'productFeatureApplType.label', default: 'productFeatureApplType.label')}"/>

                <g:sortableColumn property="display"
                                  title="${message(code: 'default.display.label', default: 'default.display.label')}"/>

                <g:sortableColumn property="lastUpdated"
                                  title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${productFeatureApplList}" status="i" var="pfa">
                <tr>
                    <td>
                        <g:link action="show" id="${pfa.id}">${pfa.id}</g:link>
                    </td>
                    <td>${pfa.product}</td>
                    <td>${pfa.productFeature}</td>
                    <td>${pfa.productFeatureApplType.name}</td>
                    <td>${pfa.display}</td>
                    <td><g:formatDate format="yyyy-MMM-dd" date="${pfa.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${productFeatureApplCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${productFeatureApplCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>