<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
    <asset:stylesheet src="bootstrap-datepicker3.css"/>
    <asset:javascript src="bootstrap-datepicker.js"/>
</head>

<body>
    <div class="container">
        <a href="#create-product" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                default="Skip to content&hellip;"/></a>

            <g:render template="/common/subnav-list"/>

        <div id="create-product" class="page-header">
            <h1><g:message code="default.create.label" args="[entityName]"/></h1>
        </div>

        <g:render template="/common/flash-message"/>

        <g:render template="has-errors"/>

        <g:form action="save">
            <fieldset class="form">
                <g:render template="form"/>
            </fieldset>
            <div>
                <g:submitButton name="create" class="btn btn-success"
                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </div>
        </g:form>

    </div> <!-- /.container -->
</body>
</html>
