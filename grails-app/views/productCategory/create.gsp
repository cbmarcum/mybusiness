<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'productCategory.label', default: 'ProductCategory')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-productCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                        default="Skip to content&hellip;"/></a>

<g:render template="/common/subnav-list"/>

<div id="create-productCategory" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>

    <g:render template="/common/flash-message"/>

    <g:hasErrors bean="${this.productCategory}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.productCategory}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save">
        <fieldset class="form">
            <f:all bean="productCategory"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
