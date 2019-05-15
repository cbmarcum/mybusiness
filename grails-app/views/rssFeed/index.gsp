<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'rssFeed.label', default: 'rssFeed.label')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-rssFeed" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-rssFeed" class="page-header" role="main">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>

                <g:sortableColumn property="id"
                                  title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                <g:sortableColumn property="name"
                                  title="${message(code: 'default.name.label', default: 'default.name.label')}"/>

                <g:sortableColumn property="url"
                                  title="${message(code: 'default.url.label', default: 'default.url.label')}"/>

                <g:sortableColumn property="max"
                                  title="${message(code: 'rss.maxFeeds.label', default: 'rss.maxFeeds.label')}"/>

                <g:sortableColumn property="display"
                                  title="${message(code: 'default.display.label', default: 'default.display.label')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${rssFeedList}" status="i" var="rss">
                <tr>
                    <td>
                        <g:link action="show" id="${rss.id}">${rss.id}</g:link>
                    </td>
                    <td>${rss.name}</td>
                    <td>${rss.url}</td>
                    <td>${rss.max}</td>
                    <td>${rss.display}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${rssFeedCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${rssFeedCount}" params="${params}"/>
        </div>
    </g:if>

</div>

</div> <%-- /.container --%>
</body>
</html>