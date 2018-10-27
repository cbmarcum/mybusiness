<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'Notice')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#list-notice" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-notice" class="content scaffold-list" role="main">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>

        <g:render template="/common/flash-message"/>

        <f:table collection="${noticeList}"/>

        <div class="pagination">
            <g:paginate total="${noticeCount ?: 0}"/>
        </div>
    </div>
</div>
</body>
</html>