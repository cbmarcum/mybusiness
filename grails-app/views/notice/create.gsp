<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'notice.label')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
    <asset:stylesheet src="bootstrap-datepicker3.css"/>
    <asset:javascript src="bootstrap-datepicker.js"/>
</head>

<body>
<div class="container">
    <a href="#create-notice" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                   default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list"/>

    <div id="create-notice" class="page-header">
        <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <g:render template="has-errors"/>

    <g:form action="save">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>

</div>
</body>
</html>
