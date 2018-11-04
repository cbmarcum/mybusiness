<!doctype html>
<html>

<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="${layout ?: 'main'}"/>
    <meta name="description" content="${g.message(code: 'meta.description.blog')}"/>
    <title>${entry.title}</title>

    <!-- facebook open graph meta tags -->
    <meta property="og:url" content="${grailsApplication.config.grails.serverURL}${request.forwardURI}"/>
    <meta property="og:title" content="${entry.title}"/>
    <meta property="og:site_name" content="Code Builders, LLC Site"/>
    <meta property="og:description" content="${entry.body}" />
    <meta property="og:image"
          content="${grailsApplication.config.grails.serverURL}/assets/mybusiness-blog.png"/>
    <meta property="og:image:width" content="350"/>
    <meta property="og:image:height" content="350"/>
    <meta property="fb:app_id" content="${grailsApplication.config.fb.appid}"/>
    <meta property="fb:admins" content="${grailsApplication.config.fb.admins}"/>
    <meta property="og:type" content="article"/>

</head>

<body id="entry">

<%-- facebook page plugin placeholder--%>
<script>
    window.fbAsyncInit = function () {
        FB.init({
            appId: "${grailsApplication.config.fb.appid}",
            autoLogAppEvents: true,
            xfbml: true,
            version: 'v3.2'
        });
    };

    (function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {
            return;
        }
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>

<div class="container">

    <a href="#page-content" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

    <g:render template="/blogEntry/subnav-list-home-create"/>

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
            <%-- comments:render bean="${entry}"/ --%>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <div id="fb-social"></div>
        </div>
    </div>

</div> <%-- /.container --%>

<!-- add facebook buttons to footer -->
<script>
    document.getElementById("fb-social").innerHTML = '<div class="fb-like" data-href="${grailsApplication.config.grails.serverURL}${request.forwardURI}" data-layout="standard" data-action="like" data-show-faces="false" data-share="true" data-size="large" data-colorscheme="dark"></div>';
</script>

</body>
</html>
