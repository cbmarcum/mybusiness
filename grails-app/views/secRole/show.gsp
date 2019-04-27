<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'secRole.label', default: 'secRole.label')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-secRole" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-secRole" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div>
        <dl class="dl-horizontal">
            <dt><g:message code="secRole.authority.label" default="secRole.authority.label"/></dt>
            <dd><f:display bean="secRole" property="authority"/></dd>
        </dl>
    </div>

    <g:form resource="${this.secRole}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.secRole}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
