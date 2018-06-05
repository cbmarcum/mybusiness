<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="${layout ?: 'main'}"/>
    <title><g:message code="blog.create.title" default="Create Entry"/></title>
    <ckeditor:resources/>
</head>

<body>
<div class="container">

    <div class="nav" role="navigation">
        <ul>
            <li>
                <g:link class="list" controller="blog" action="list"><g:message code="blog.list.link"
                                                                                default="Blog Home"></g:message></g:link>
            </li>
            <g:if test="${entry.id}">
                <li>
                    <g:link class="show" controller="blog" action="showEntry"
                            params="[title: entry.title, author: entry.author]">
                        <g:message code="blog.show" default="blog.show"></g:message>
                    </g:link>
                </li>
            </g:if>
        </ul>
    </div>

    <div id="createEntry" class="createEntry">
        <h1>
            <g:if test="${entry.id}">
                <g:message code="blog.edit" default="blog.edit"></g:message>
            </g:if>
            <g:else>
                <g:message code="blog.createEntry" default="blog.createEntry"></g:message>
            </g:else>
        </h1>

        <g:renderErrors bean="${entry}"></g:renderErrors>

        <g:form class="createEntryForm" id="createEntryForm" name="createEntryForm"
                url="[controller: 'blog', action: 'publish']">
            <g:if test="${entry.id}">
                <g:hiddenField name="id" value="${entry.id}"/>
            </g:if>

            <p>
                To create SEO friendly URL's please use '-' between words in the title instead of spaces.
            </p>

            <div class="form-group ${hasErrors(bean: entry, field: 'title', 'error')} ">
                <label for="entry.title"><g:message code="blog.title.label" default="blog.title.label"/></label>
                <g:field type="text" class="form-control" name="entry.title" value="${entry.title}"
                         maxLength="80" placeholder="80 chars max"/>
            </div>

            <div class="form-group ${hasErrors(bean: entry, field: 'entry.body', 'error')} ">
                <label for="entry.body">
                    <g:message code="blog.body.label" default="blog.body.label"/>
                </label>
                <ckeditor:editor name="entry.body" height="400px" width="100%" toolbar="Full">
                    ${entry?.body}
                </ckeditor:editor>
            </div> <%-- /.form-group --%>

            <div class="form-group ${hasErrors(bean: entry, field: 'blog.tags.label', 'error')} ">
                <label for="tags"><g:message code="blog.tags.label" default="blog.tags.label"/></label>
                <g:field type="text" class="form-control" name="tags" value="${entry.tags.join(',')}"
                         maxLength="80" placeholder="80 chars max"/>
            </div>

            <g:actionSubmit action="publish" value="${message(code: 'blog.publish.button', default: 'Publish')}"/>
        <%--<g:actionSubmit action="save" value="${message(code:'blog.save.button',default:'Save Draft')}" />--%>
        </g:form>
    </div>

</div> <%-- /.container --%>
</body>
</html>
