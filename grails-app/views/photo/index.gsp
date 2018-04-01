<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#list-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </ul>
    </div>

    <div id="list-photo" class="page=header">
        <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    </div>

    <g:if test="${flash.message}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
    </g:if>

    <div class="table-responsive">
        <table class="table table-condensed">
            <thead>
            <tr>
                <g:sortableColumn property="id"
                                  title="${message(code: 'default.id.label', default: 'default.id.label')}"/>

                <th><g:message code="photo.label" default="photo.label"/></th>

                <g:sortableColumn property="name"
                                  title="${message(code: 'photo.name.label', default: 'photo.name.label')}"/>

                <g:sortableColumn property="alt"
                                  title="${message(code: 'photo.alt.label', default: 'photo.alt.label')}"/>

                <g:sortableColumn property="title"
                                  title="${message(code: 'photo.title.label', default: 'photo.title.label')}"/>

                <g:sortableColumn property="lastUpdated"
                                  title="${message(code: 'default.lastUpdated.label', default: 'default.lastUpdated.label')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${photoList}" status="i" var="photo">
                <tr>
                    <td>
                        <g:link action="show" id="${photo.id}">${photo.id}</g:link>
                    </td>
                    <td><cb:image image="${photo.photo.getCloudFile('thumb')}"/></td>
                    <td>${photo.name}</td>
                    <td>${photo.alt}</td>
                    <td>${photo.title}</td>
                    <td><g:formatDate format="yyyy-MMM-dd" date="${photo.lastUpdated}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>



    <g:if test="${photoCount > params.max}">
        <div class="text-center">
            <cb:bsPaginate total="${photoCount}" params="${params}"/>
        </div>
    </g:if>

</div> <%-- /.container --%>
</body>
</html>