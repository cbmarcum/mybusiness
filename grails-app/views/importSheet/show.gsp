<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'importSheet.label', default: 'ImportSheet')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-importSheet" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                      default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-importSheet" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div>
        <dl class="dl-horizontal">

            <dt><g:message code="importSheet.name.label" default="importSheet.name.label"/></dt>
            <dd><f:display bean="importSheet" property="name"/></dd>

            <dt><g:message code="importSheet.importSheetStatusType.label" default="importSheet.importSheetStatusType.label"/></dt>
            <dd><f:display bean="importSheet" property="importSheetStatusType.name"/></dd>

            <dt><g:message code="importSheet.comments.label" default="importSheet.comments.label"/></dt>
            <dd><f:display bean="importSheet" property="comments"/></dd>

            <dt><g:message code="default.dateCreated.label" default="default.dateCreated.label"/></dt>
            <dd><f:display bean="importSheet" property="dateCreated"/></dd>

            <dt><g:message code="default.lastUpdated.label" default="default.lastUpdated.label"/></dt>
            <dd><f:display bean="importSheet" property="lastUpdated"/></dd>

        </dl>
    </div>

    <g:form resource="${this.importSheet}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.importSheet}"><g:message code="default.button.edit.label"
                                                                                   default="Edit"/></g:link>

            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
