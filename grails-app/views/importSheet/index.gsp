<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'importSheet.label', default: 'ImportSheet')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#list-importSheet" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                      default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-create"/>

    <div id="list-photo" class="page=header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>
    <div>
        After uploading a spreadsheet, select the Ready link to process the sheets.<br/>
        Link will change to Completed or Failed when finished.<br/>
        Failed links can be followed to see details.
    </div>

    <g:render template="/common/flash-message"/>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>
                <g:sortableColumn property="id"
                                  title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                <th><g:message code="importSheet.label" default="importSheet.label"/></th>

                <g:sortableColumn property="name"
                                  title="${message(code: 'importSheet.name.label', default: 'importSheet.name.label')}"/>

                <g:sortableColumn property="importSheetStatusType.name"
                                  title="${message(code: 'importSheet.importSheetStatusType.label', default: 'importSheet.importSheetStatusType.label')}"/>

                <g:sortableColumn property="lastUpdated"
                                  title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${importSheetList}" status="i" var="importSheet">
                <tr>
                    <td>
                        <g:link action="show" id="${importSheet.id}">${importSheet.id}</g:link>
                    </td>
                    <td>${importSheet.sheet.getCloudFile('original')}</td>
                    <td>${importSheet.name}</td>
                    <td><g:link action="processSheet" id="${importSheet.id}">${importSheet.importSheetStatusType.name}</g:link></td>
                    <td><g:formatDate format="yyyy-MMM-dd" date="${importSheet.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <g:if test="${importSheetCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${importSheetCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>