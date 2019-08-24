<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'secUserSecRole.label', default: 'SecUserSecRole')}"/>
        <title><g:message code="default.list.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="container">
            <a href="#list-secUserSecRole" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                    default="Skip to content&hellip;"/></a>

                <g:render template="/common/subnav-create"/>

            <div id="list-secUserSecRole" class="page-header">
                <h1><g:message code="default.list.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <g:sortableColumn property="secUser"
                            title="${message(code: 'secUser.label', default: 'secUser.label')}"/>

                            <g:sortableColumn property="secRole"
                            title="${message(code: 'secRole.label', default: 'secRole.label')}"/>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${secUserSecRoleList}" status="i" var="item">
                            <tr>
                                <td>
                                    <g:link action="show" params="[secUser: item.secUser, secRole: item.secRole]">${item.secUser.username}</g:link>
                                    </td>
                                    <td>
                                    <g:link action="show" params="[secUser: item.secUser, secRole: item.secRole]">${item.secRole.authority}</g:link>
                                    </td>
                                </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>


            <g:if test="${secUserSecRoleCount > params.max}">
                <div class="text-center">
                    <cb:bsPaginate total="${secUserSecRoleCount}" params="${params}"/>
                </div>
        </g:if>

    </div> <%-- /.container --%>
</body>
</html>