<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
        <title><g:message code="default.edit.label" args="[entityName]"/></title>
    </head>

    <body>
        <a href="#edit-photo" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                default="Skip to content&hellip;"/></a>

            <g:render template="/common/subnav-list-create"/>

        <div id="edit-photo" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]"/></h1>

            <g:render template="/common/flash-message"/>

            <g:render template="has-errors"/>

            <g:form resource="${this.photo}" method="PUT">
                <g:hiddenField name="version" value="${this.photo?.version}"/>
                <fieldset class="form">
                    <f:all bean="photo"/>
                </fieldset>
                <div>
                    <input class="btn btn-success" type="submit"
                    value="${message(code: 'default.button.update.label', default: 'Update')}"/>
                </div>
            </g:form>
        </div>
    </body>
</html>
