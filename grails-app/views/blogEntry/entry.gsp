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

    <g:render template="subnav-list-home-create"/>

    <g:render template="/common/flash-message"/>

    <div class="blogEntryDisplay" id="page-content">
        <g:render template="/blogEntry/entry" model="[entry: entry]"></g:render>

        <sec:access expression="hasRole('ROLE_ADMIN')">

            <fieldset class="buttons">
                <g:link class="edit" controller="blog" action="editEntry" id="${entry.id}">
                    <g:message code="blog.edit" default="blog.edit"></g:message>
                </g:link>
                <g:link class="delete" controller="blog" action="deleteEntry" id="${entry.id}">
                    <g:message code="blog.delete" default="blog.delete"></g:message>
                </g:link>
            </fieldset>

        </sec:access>

        <div id="comment" class="entryComments">
            <h2><g:message code="blog.comments.title" default="blog.comments.title"></g:message></h2>
            <comments:render bean="${entry}"/>
        </div>
    </div>

</div> <%-- /.container --%>
</body>
</html>
