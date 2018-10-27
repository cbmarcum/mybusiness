<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'Notice')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
    <ckeditor:resources/>
</head>

<body>
<div class="container">
    <a href="#edit-notice" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="edit-notice" class="content scaffold-edit" role="main">
        <h1><g:message code="default.edit.label" args="[entityName]"/></h1>

        <g:render template="/common/flash-message"/>

        <g:rendor template="has-errors"/>

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
                <f:field bean="notice" property="page"/>
            </fieldset>
            <fieldset class="buttons">
                <input class="save" type="submit"
                       value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            </fieldset>
        </g:form>
    </div>
</div>
</body>
</html>
