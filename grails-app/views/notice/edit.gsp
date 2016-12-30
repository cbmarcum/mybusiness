<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'Notice')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-notice" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="edit-notice" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.notice}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.notice}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.notice}" method="PUT">
        <g:hiddenField name="version" value="${this.notice?.version}"/>
        <fieldset class="form">
            <f:field bean="notice" property="name"/>
            <div class="fieldcontain ${hasErrors(bean: notice, field: 'longDescription', 'error')} ">
                <label for="longDescription">
                    <g:message code="notice.longDescription.label" default="Long Description"/>

                </label>
                <ckeditor:editor name="longDescription" height="200px" width="90%" toolbar="Basic">
                    ${notice?.longDescription}
                </ckeditor:editor>
            </div>
            <f:field bean="notice" property="display"/>
            <f:field bean="notice" property="fromDate"/>
            <f:field bean="notice" property="thruDate"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
