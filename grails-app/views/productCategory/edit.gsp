<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'productCategory.label', default: 'productCategory.label')}"/>
        <title><g:message code="default.edit.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="container">

            <a href="#edit-productCategory" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                    default="Skip to content&hellip;"/></a>

                <g:render template="/common/subnav-list-create"/>

            <div id="edit-productCategory" class="page-header">
                <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

            <g:render template="has-errors"/>

            <g:form resource="${this.productCategory}" method="PUT">
                <g:hiddenField name="version" value="${this.productCategory?.version}"/>
                <fieldset class="form">
                    <g:render template="form"/>
                </fieldset>
                <div>
                    <input class="btn btn-success" type="submit"
                    value="${message(code: 'default.button.update.label', default: 'Update')}"/>
                </div>
            </g:form>

        </div> <%-- /.container --%>
    </body>
</html>
