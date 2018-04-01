<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="list" action="index"><g:message code="default.list.label"
                                                               args="[entityName]"/></g:link></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </ul>
    </div>

    <div id="show-photo" class="content scaffold-show" role="main">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:if test="${flash.message}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
    </g:if>

    <div>
        <dl class="dl-horizontal">

            <dt><g:message code="photo.label" default="photo.label"/></dt>
            <dd><cb:image image="${photo.photo.getCloudFile('small')}"/></dd>

            <dt><g:message code="photo.name.label" default="photo.name.label"/></dt>
            <dd><f:display bean="photo" property="name"/></dd>

            <dt><g:message code="photo.alt.label" default="photo.alt.label"/></dt>
            <dd><f:display bean="photo" property="alt"/></dd>

            <dt><g:message code="photo.title.label" default="photo.title.label"/></dt>
            <dd><f:display bean="photo" property="title"/></dd>

            <dt><g:message code="default.dateCreated.label" default="default.dateCreated.label"/></dt>
            <dd><f:display bean="photo" property="dateCreated"/></dd>

            <dt><g:message code="default.lastUpdated.label" default="default.lastUpdated.label"/></dt>
            <dd><f:display bean="photo" property="lastUpdated"/></dd>

        </dl>
    </div>

    <g:form resource="${this.photo}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.photo}"><g:message code="default.button.edit.label"
                                                                                   default="Edit"/></g:link>

            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
