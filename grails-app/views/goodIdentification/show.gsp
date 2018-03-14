<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'product.goodIdentification.label', default: 'Good Identification')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <a href="#show-goodIdentification" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
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

    <div id="show-goodIdentification" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:if test="${flash.message}">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
    </g:if>

    <div>
        <dl class="dl-horizontal">
            <dt><g:message code="product.label" default="Product"/></dt>
            <dd><f:display bean="goodIdentification" property="product"/></dd>

            <dt><g:message code="product.goodIdentificationType.label" default="Good Identification Type"/></dt>
            <dd><f:display bean="goodIdentification" property="goodIdentificationType"/></dd>

            <dt><g:message code="default.value.label" default="Value"/></dt>
            <dd><f:display bean="goodIdentification" property="value"/></dd>

        </dl>
    </div>


    <g:form resource="${this.goodIdentification}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.goodIdentification}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
