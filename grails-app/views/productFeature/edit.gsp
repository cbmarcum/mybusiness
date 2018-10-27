<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'productFeature.label', default: 'ProductFeature')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-productFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>

<g:render template="/common/subnav-list-create"/>

<div id="edit-productFeature" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>

    <g:render template="/common/flash-message"/>

    <g:hasErrors bean="${this.productFeature}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.productFeature}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.productFeature}" method="PUT">
        <g:hiddenField name="version" value="${this.productFeature?.version}"/>
        <fieldset class="form">
            <f:all bean="productFeature"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
