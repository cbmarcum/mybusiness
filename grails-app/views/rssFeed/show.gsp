<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'rssFeed.label', default: 'RssFeed')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/></title>
    </head>

    <body>
        <a href="#show-rssFeed" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                default="Skip to content&hellip;"/></a>

            <g:render template="/common/subnav-list-create"/>

        <div id="show-rssFeed" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="rssFeed"/>
            <g:form resource="${this.rssFeed}" method="DELETE">
                <div>
                    <g:link class="btn btn-primary" action="edit" resource="${this.rssFeed}"><g:message code="default.button.edit.label"
                            default="Edit"/></g:link>
                    <input class="btn btn-danger" type="submit"
                    value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                </div>
            </g:form>
        </div>
    </body>
</html>
