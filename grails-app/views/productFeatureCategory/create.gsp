<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'productFeatureCategory.label', default: 'productFeatureCategory.label')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#create-productFeatureCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                   default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list"/>

    <div id="create-productFeatureCategory" class="page-header">
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

</div> <%-- /.container --%>
</body>
</html>
