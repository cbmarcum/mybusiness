<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'productFeature.label', default: 'ProductFeature')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-productFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                       default="Skip to content&hellip;"/></a>

<g:render template="/common/subnav-list"/>

<div id="create-productFeature" class="page-header">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
</div>

<g:render template="/common/flash-message"/>

<g:render template="has-errors"/>

<g:form action="save">
    <fieldset class="form">
        <f:all bean="productFeature"/>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="create" class="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
    </fieldset>
</g:form>

</body>
</html>
