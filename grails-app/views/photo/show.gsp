<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
        <title><g:message code="default.show.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="container">

            <a href="#show-photo" class="sr-only sr-only-focusable" tabindex="-1"><g:message code="default.link.skip.label"
                    default="Skip to content&hellip;"/></a>

                <g:render template="/common/subnav-list-create"/>

            <div id="show-photo" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]"/></h1>
            </div>

            <g:render template="/common/flash-message"/>

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
                <div>
                    <g:link class="btn btn-primary" action="edit" resource="${this.photo}"><g:message code="default.button.edit.label"
                            default="Edit"/></g:link>

                    <input class="btn btn-danger" type="submit"
                    value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                </div>
            </g:form>

        </div> <%-- /.container --%>
    </body>
</html>
