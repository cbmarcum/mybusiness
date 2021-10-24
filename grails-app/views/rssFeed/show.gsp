<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'rssFeed.label', default: 'RssFeed')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-rssFeed" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                       default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-rssFeed" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div>
        <dl class="row">
            <dt class="col-md-2"><g:message code="default.name.label" default="default.name.label"/></dt>
            <dd class="col-md-10"><f:display bean="rssFeed" property="name"/></dd>

            <dt class="col-md-2"><g:message code="default.url.label" default="default.url.label"/></dt>
            <dd class="col-md-10"><f:display bean="rssFeed" property="url"/></dd>

            <dt class="col-md-2"><g:message code="rss.maxFeeds.label" default="rss.maxFeeds.label"/></dt>
            <dd class="col-md-10"><f:display bean="rssFeed" property="max"/></dd>

            <dt class="col-md-2"><g:message code="default.display.label" default="default.display.label"/></dt>
            <dd class="col-md-10"><f:display bean="rssFeed" property="display"/></dd>
        </dl>
    </div>

    <g:form resource="${this.rssFeed}" method="DELETE">
        <div class="mb-3">
            <g:link class="btn btn-primary" action="edit" resource="${this.rssFeed}"><g:message
                    code="default.button.edit.label"
                    default="Edit"/></g:link>
            <input class="btn btn-danger" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </div>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
