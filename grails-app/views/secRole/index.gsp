<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'secRole.label', default: 'SecRole')}"/>
        <title><g:message code="default.list.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="container">
            <a href="#list-secRole" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                    default="Skip to content&hellip;"/></a>

                <g:render template="/common/subnav-create"/>

            <div id="list-secRole" class="page-header">
                <h1><g:message code="default.list.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <g:sortableColumn property="id"
                            title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                            <g:sortableColumn property="authority"
                            title="${message(code: 'secRole.authority.label', default: 'secRole.authority.label')}"/>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${secRoleList}" status="i" var="role">
                            <tr>
                                <td>
                                    <g:link action="show" id="${role.id}">${role.id}</g:link>
                                    </td>
                                    <td>${role.authority}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>

            <g:if test="${secRoleCount > params.max}">
                <div class="text-center">
                    <cb:bsPaginate total="${secRoleCount}" params="${params}"/>
                </div>
        </g:if>

    </div> <%-- /.container --%>
</body>
</html>