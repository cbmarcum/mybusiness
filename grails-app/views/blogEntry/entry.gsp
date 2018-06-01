<!doctype html>
<html>

<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="${layout ?: 'main'}"/>
    <meta name="description" content="${g.message(code: 'meta.description.blog')}"/>
    <title>${entry.title}</title>

</head>

<body id="entry">
<div class="container">

    <a href="#page-content" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

    <div class="nav" role="navigation">
        <ul>
            <li><g:link class="list" controller="blog" action="list">
                <g:message code="blog.list" default="blog.list"></g:message>
            </g:link></li>

            <sec:access expression="hasRole('ROLE_ADMIN')">

                <li><g:link class="home" controller="admin" action="index">
                    <g:message code="default.admin.home.label"/>
                </g:link></li>

                <li><g:link class="create" controller="blog" action="createEntry"><g:message
                        code="blog.createEntry"
                        default="blog.createEntry"></g:message></g:link></li>
                <li><g:link class="edit" controller="blog" action="editEntry" id="${entry.id}">
                    <g:message code="blog.edit" default="blog.edit"></g:message>
                </g:link></li>

                <li>
                    <g:link class="delete" controller="blog" action="deleteEntry" id="${entry.id}">
                        <g:message code="blog.delete" default="log.delete"></g:message>
                    </g:link></li>

            </sec:access>
        </ul>
    </div>

    <g:if test="${flash.message}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
    </g:if>

    <div class="blogEntryDisplay" id="page-content">
        <g:render template="/blogEntry/entry" model="[entry: entry]"></g:render>

        <div id="comment" class="entryComments">
            <h2><g:message code="blog.comments.title" default="blog.comments.title"></g:message></h2>
            <comments:render bean="${entry}"/>
        </div>
    </div>

</div> <%-- /.container --%>
</body>
</html>
