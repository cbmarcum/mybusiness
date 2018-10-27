<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'photo.label', default: 'Photo')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#create-photo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list"/>

    <div id="create-photo" class="page-header">
        <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <g:render template="has-errors"/>

    <g:uploadForm name="upload" url="[action: 'upload', controller: 'photo']">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>

        <input type="file" name="photo"/><br/>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>

</div> <%-- /.container --%>
</body>
</html>
