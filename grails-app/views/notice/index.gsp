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

            <div id="list-notice" class="page-header">
                <h1><g:message code="default.list.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

            <div class="table-responsive">
                <table class="table table-condensed">
                    <thead>
                        <tr>

                            <g:sortableColumn property="name"
                            title="${message(code: 'default.name.label', default: 'default.name.label')}"/>

                            <g:sortableColumn property="page"
                            title="${message(code: 'notice.page.label', default: 'notice.page.label')}"/>

                            <g:sortableColumn property="fromDate"
                            title="${message(code: 'notice.fromDate.label', default: 'notice.fromDate.label')}"/>

                            <g:sortableColumn property="thruDate"
                            title="${message(code: 'notice.thruDate.label', default: 'notice.thruDate.label')}"/>

                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${noticeList}" status="i" var="item">
                            <tr>
                                <td><g:link action="show" id="${item.name}">${item.name}</g:link></td>
                                <td>${item.page}</td>
                                <td><g:formatDate format="yyyy-MMM-dd" date="${item.fromDate}"/></td>
                                <td><g:formatDate format="yyyy-MMM-dd" date="${item.thruDate}"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div>

            <g:if test="${noticeCount > params.max}">
                <div class="text-center">
                    <cb:bsPaginate total="${noticeCount}" params="${params}"/>
                </div>
        </g:if>

    </div>
</body>
</html>