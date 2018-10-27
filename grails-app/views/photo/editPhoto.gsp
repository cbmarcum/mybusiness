<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#edit-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="edit-photo" class="page-header">
        <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <g:render template="has-errors"/>

    <g:form resource="${this.photo}" method="PUT">
        <g:hiddenField name="version" value="${this.photo?.version}"/>

        <div>
            <dl class="dl-horizontal">
                <dt><g:message code="photo.label" default="photo.label"/></dt>
                <dd><cb:image image="${photo.photo.getCloudFile('small')}"/></dd>
            </dl>
        </div>

        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
