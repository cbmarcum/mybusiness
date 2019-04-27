<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'notice.label', default: 'Notice')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-notice" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-notice" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div>
        <dl class="dl-horizontal">
            <dt><g:message code="default.name.label" default="default.name.label"/></dt>
            <dd><f:display bean="notice" property="name"/></dd>

            <dt><g:message code="notice.longDescription.label" default="notice.longDescription.label"/></dt>
            <dd><f:display bean="notice" property="longDescription"/></dd>

            <dt><g:message code="notice.page.label" default="notice.page.label"/></dt>
            <dd><f:display bean="notice" property="page"/></dd>

            <dt><g:message code="notice.fromDate.label" default="notice.fromDate.label"/></dt>
            <dd><f:display bean="notice" property="fromDate"/>
                <g:formatDate format="yyyy-MMM-dd" date="${value}"/></dd>

            <dt><g:message code="notice.thruDate.label" default="notice.thruDate.label"/></dt>
            <dd><f:display bean="notice" property="thruDate"/>
                <g:formatDate format="yyyy-MMM-dd" date="${value}"/></dd>
        </dl>
    </div>

    <g:form resource="${this.notice}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.notice}"><g:message code="default.button.edit.label"
                                                                                    default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
