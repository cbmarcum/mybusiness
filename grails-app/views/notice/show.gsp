<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'Notice')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#show-notice" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-notice" class="content scaffold-show" role="main">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>

        <g:render template="/common/flash-message"/>

        <f:display bean="notice"/>
        <g:form resource="${this.notice}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.notice}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
                <input class="delete" type="submit"
                       value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                       onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </fieldset>
        </g:form>
    </div>
</div>
</body>
</html>
