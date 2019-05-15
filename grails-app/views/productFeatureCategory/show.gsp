<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'productFeatureCategory.label', default: 'productFeatureCategory.label')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-productFeatureCategory" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                 default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-productFeatureCategory" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div>
        <dl class="dl-horizontal">
            <dt><g:message code="productFeatureCategory.description.label"
                           default="productFeatureCategory.description.label"/></dt>
            <dd><f:display bean="productFeatureCategory" property="description"/></dd>

            <dt><g:message code="productFeatureCategory.shortDescription.label"
                           default="productFeatureCategory.shortDescription.label"/></dt>
            <dd><f:display bean="productFeatureCategory" property="shortDescription"/></dd>

            <dt><g:message code="default.sequenceNum.label" default="default.sequenceNum.label"/></dt>
            <dd><f:display bean="productFeatureCategory" property="sequenceNum"/></dd>

            <dt><g:message code="default.dateCreated.label" default="default.dateCreated.label"/></dt>
            <dd><f:display bean="productFeatureCategory" property="dateCreated"/></dd>

            <dt><g:message code="default.lastUpdated.label" default="default.lastUpdated.label"/></dt>
            <dd><f:display bean="productFeatureCategory" property="lastUpdated"/></dd>
        </dl>

        <g:form resource="${this.productFeatureCategory}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.productFeatureCategory}"><g:message
                        code="default.button.edit.label" default="Edit"/></g:link>
                <input class="delete" type="submit"
                       value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                       onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </fieldset>
        </g:form>
    </div>

</div> <%-- /.container --%>
</body>
</html>
