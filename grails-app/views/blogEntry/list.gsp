<!doctype html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="main"/>
    <meta name="description" content="${g.message(code: 'meta.description.blog')}"/>
    <plugin:isAvailable name="feeds">
        <feed:meta kind="rss" version="2.0" controller="blog" action="feed" params="[format: 'rss']"/>
    </plugin:isAvailable>

    <title><g:message code="blog.list" default="blog.list"/></title>
</head>

<body>
<div class="container">

    <a href="#page-content" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>
    <g:render template="/blogEntry/subnav-home-create"/>

    <g:render template="/common/flash-message"/>

    <g:render template="/common/notice-message"/>

    <div style="padding-top: 20px;">
        <h3>
            A blog about Code Builders, MyBusiness, and other software development topics.
        </h3>
    </div>

    <div id="page-content" class="row">

        <%-- left-detail --%>
        <div class="col-md-9">

            <div id="blogEntries" class="blogEntries">
                <g:set var="first" value="${entries ? entries.iterator().next() : null}"/>
                <div class="firstEntry">
                    <g:if test="${first}">
                        <g:render template="/blogEntry/entry" model="[entry: first]"></g:render>
                    </g:if>
                </div>
                <g:set var="remaining" value="${entries - first}"></g:set>
                <g:if test="${remaining}">
                    <g:render template="/blogEntry/recentEntries" model="[entries: remaining]"/>

                </g:if>
            </div>

        </div> <%-- /.col --%>
    <%-- end left-detail --%>

    <%-- right-detail --%>
        <div class="col-md-3">

            <plugin:isAvailable name="feeds">
                <div class="menuButton">
                    <g:link class="feed" controller="blog" action="feed" params="[format: 'rss']">
                        <g:message code="grails.blog.rss.link" default="grails.blog.rss.link"></g:message>
                    </g:link>
                </div>
            </plugin:isAvailable>

            <g:render template="/blogEntry/menubar-search"/>

            <div class="blogQuickLinks">

                <div id="blogAuthors" class="blogAuthors">
                    <h2 class="authorsTitle">
                        <g:message code="blog.authors.title" default="blog.authors.title"></g:message>
                    </h2>

                    <div class="authorsList">
                        <g:each var="author" in="${authors}">
                            <div class="author">
                                <g:link controller="blog" action="showEntry"
                                        params="[author: author]">${author}</g:link>
                            </div>
                        </g:each>
                    </div>
                </div>

                <div id="blogTags" class="blogTags">
                    <h2 class="tagsTitle">
                        <g:message code="blog.tags.title" default="blog.tags.title"></g:message>
                    </h2>

                    <div class="tagList">
                        <g:each var="tag" in="${tagNames}">
                            <div class="tag">
                                <g:link controller="blog" action="byTag" params="[tag: tag]">${tag}</g:link>
                            </div>
                        </g:each>

                    </div>
                </div>

            </div> <%-- blogQuickLinks --%>

        </div> <%-- /.col --%>
    <%-- end right-detail --%>

    </div> <%-- ./row --%>

</div> <%-- /.container --%>

</body>
</html>
