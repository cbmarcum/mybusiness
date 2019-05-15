<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'secUser.label', default: 'secUser.label')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-secUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-secUser" class="page-header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>
                <g:sortableColumn property="id"
                                  title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                <g:sortableColumn property="username"
                                  title="${message(code: 'secUser.username.label', default: 'secUser.username.label')}"/>

                <g:sortableColumn property="enabled"
                                  title="${message(code: 'secUser.enabled.label', default: 'secUser.enabled.label')}"/>

                <g:sortableColumn property="accountExpired"
                                  title="${message(code: 'secUser.accountExpired.label', default: 'secUser.accountExpired.label')}"/>

                <g:sortableColumn property="accountLocked"
                                  title="${message(code: 'secUser.accountLocked.label', default: 'secUser.accountLocked.label')}"/>

                <g:sortableColumn property="passwordExpired"
                                  title="${message(code: 'secUser.passwordExpired.label', default: 'secUser.passwordExpired.label')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${secUserList}" status="i" var="user">
                <tr>
                    <td>
                        <g:link action="show" id="${user.id}">${user.id}</g:link>
                    </td>
                    <td>${user.username}</td>
                    <td>${user.enabled}</td>
                    <td>${user.accountExpired}</td>
                    <td>${user.accountLocked}</td>
                    <td>${user.passwordExpired}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${secUserCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${secUserCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>