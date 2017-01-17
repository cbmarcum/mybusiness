<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
           aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>About MyBusiness</h1>

        <div class="container" id="more">
            <g:if test="${noticeInstanceList}">
                <!-- <h2><g:message code="notice.label"/></h2> -->
                <g:each in="${noticeInstanceList}" status="i" var="noticeInstance">
                    <div class="alert alert-info" role="alert">

                        <h2>${fieldValue(bean: noticeInstance, field: "name")}</h2>

                        <p>
                            ${raw(noticeInstance.longDescription)}
                        </p>

                    <!-- only create show link to administrator -->
                        <sec:ifAnyGranted roles='ROLE_ADMIN'>
                            <p>
                                Display = <g:formatBoolean boolean="${noticeInstance.display}"/>
                                <br/>
                                From Date = <g:formatDate format="yyyy-MM-dd" date="${noticeInstance.fromDate}"/>
                                <br/>
                                Thru Date = <g:formatDate format="yyyy-MM-dd" date="${noticeInstance.thruDate}"/>
                                <br/>
                                <g:link controller="notice" action="show" id="${noticeInstance.id}">Edit Notice</g:link>
                            </p>
                        </sec:ifAnyGranted>
                    </div> <!-- /.alert -->
                </g:each>
            </g:if>
        </div> <!-- container -->

        <div class="container">
            <h2>RSS Feeds</h2>
            <g:if test="${feedList}">
                <g:each in="${feedList}" status="i" var="feed">
                    <p><a href="${feed.link}">${feed.title}</a></p>
                </g:each>
            </g:if>
            <g:else>
                <p>No Feeds Available</p>
            </g:else>

        </div> <!-- container -->

        <p>
            We can begin to add our own menus now...
        </p>

        <div id="controllers" role="navigation">
            <h2>Available Controllers:</h2>
            <ul>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                    <li class="controller">
                        <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                    </li>
                </g:each>
            </ul>
        </div>

    </section>
</div>

</body>
</html>
