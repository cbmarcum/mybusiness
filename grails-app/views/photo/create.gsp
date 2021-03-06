<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
    </head>

    <body>
        <a href="#create-photo" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                default="Skip to content&hellip;"/></a>

            <g:render template="/common/subnav-list"/>

        <div id="create-photo" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]"/></h1>

            <g:render template="/common/flash-message"/>

            <g:render template="has-errors"/>

            <g:form resource="${this.photo}" method="POST">
                <fieldset class="form">
                    <f:all bean="photo"/>
                </fieldset>
                <div>
                    <g:submitButton name="create" class="btn btn-success"
                    value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                </div>
            </g:form>
        </div>
    </body>
</html>
