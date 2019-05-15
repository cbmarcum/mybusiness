<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'secUser.label', default: 'SecUser')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-secUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-secUser" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>
    
    <div>
        <dl class="dl-horizontal">
            <dt><g:message code="secUser.username.label" default="secUser.username.label"/></dt>
            <dd><f:display bean="secUser" property="username"/></dd>

            <dt><g:message code="secUser.enabled.label" default="secUser.enabled.label"/></dt>
            <dd><f:display bean="secUser" property="enabled"/></dd>

            <dt><g:message code="secUser.accountExpired.label" default="secUser.accountExpired.label"/></dt>
            <dd><f:display bean="secUser" property="accountExpired"/></dd>

            <dt><g:message code="secUser.accountLocked.label" default="secUser.accountLocked.label"/></dt>
            <dd><f:display bean="secUser" property="accountLocked"/></dd>

            <dt><g:message code="secUser.passwordExpired.label" default="secUser.passwordExpired.label"/></dt>
            <dd><f:display bean="secUser" property="passwordExpired"/></dd>
        </dl>
    </div>

    <g:form resource="${this.secUser}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.secUser}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
