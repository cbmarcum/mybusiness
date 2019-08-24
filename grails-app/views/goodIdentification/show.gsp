<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName"
        value="${message(code: 'goodIdentification.label', default: 'goodIdentification.label')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="container">
            <a href="#show-goodIdentification" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                    default="Skip to content&hellip;"/></a>

                <g:render template="/common/subnav-list-create"/>

            <div id="show-goodIdentification" class="page-header">
                <h1><g:message code="default.show.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

            <div>
                <dl class="dl-horizontal">
                    <dt><g:message code="product.label" default="product.label"/></dt>
                    <dd><f:display bean="goodIdentification" property="product"/></dd>

                    <dt><g:message code="goodIdentificationType.label" default="goodIdentificationType.label"/></dt>
                    <dd><f:display bean="goodIdentification" property="goodIdentificationType"/></dd>

                    <dt><g:message code="default.value.label" default="default.value.label"/></dt>
                    <dd><f:display bean="goodIdentification" property="value"/></dd>

                    <dt><g:message code="default.dateCreated.label" default="default.dateCreated.label"/></dt>
                    <dd><f:display bean="goodIdentification" property="dateCreated">
                        <g:formatDate format="yyyy-MMM-dd" date="${value}"/>
                    </f:display></dd>

                    <dt><g:message code="default.lastUpdated.label" default="default.lastUpdated.label"/></dt>
                    <dd><f:display bean="goodIdentification" property="lastUpdated">
                        <g:formatDate format="yyyy-MMM-dd" date="${value}"/>
                    </f:display></dd>

                </dl>
            </div>

            <g:form resource="${this.goodIdentification}" method="DELETE">
                <div>
                    <g:link class="btn btn-primary" action="edit" resource="${this.goodIdentification}"><g:message
                            code="default.button.edit.label" default="Edit"/></g:link>
                    <input class="btn btn-danger" type="submit"
                    value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                </div>
            </g:form>

        </div> <%-- /.container --%>
    </body>
</html>
