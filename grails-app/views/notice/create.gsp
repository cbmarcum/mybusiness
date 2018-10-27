<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'Notice')}"/>
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

    <div id="create-notice" class="content scaffold-create" role="main">
        <h1><g:message code="default.create.label" args="[entityName]"/></h1>

        <g:render template="/common/flash-message"/>

        <g:rendor template="has-errors"/>

        <g:form action="save">
            <fieldset class="form">
                <f:field class="form-control" bean="notice" property="name"/>
                <div class="fieldcontain ${hasErrors(bean: notice, field: 'longDescription', 'error')} ">
                    <label for="longDescription">
                        <g:message code="notice.longDescription.label" default="Long Description"/>

                    </label>
                    <ckeditor:editor name="longDescription" height="200px" width="90%" toolbar="Basic">
                        ${notice?.longDescription}
                    </ckeditor:editor>
                </div>
                <f:field bean="notice" property="display"/>
                <f:field class="form-control" bean="notice" property="fromDate"/>
                <f:field bean="notice" property="thruDate"/>
                <f:field bean="notice" property="page"/>
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </fieldset>
        </g:form>
    </div>
</div>
</body>
</html>
