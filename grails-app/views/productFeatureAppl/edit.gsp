<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'productFeatureAppl.label', default: 'productFeatureAppl.label')}"/>
        <title><g:message code="default.edit.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="container">
            <a href="#edit-productFeatureAppl" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                    default="Skip to content&hellip;"/></a>

                <g:render template="/common/subnav-list-create"/>

            <div id="edit-productFeatureAppl" class="page-header">
                <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

            <g:render template="has-errors"/>

            <g:form resource="${this.productFeatureAppl}" method="PUT">
                <g:hiddenField name="version" value="${this.productFeatureAppl?.version}"/>
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
