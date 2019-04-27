<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'productFeature.label', default: 'ProductFeature')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#show-productFeature" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                         default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list-create"/>

    <div id="show-productFeature" class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <div>
        <dl class="dl-horizontal">
            <dt><g:message code="productFeatureCategory.label"
                           default="productFeatureCategory.label"/></dt>
            <dd><f:display bean="productFeature" property="productFeatureCategory"/></dd>

            <dt><g:message code="productFeature.description.label"
                           default="productFeature.description.label"/></dt>
            <dd><f:display bean="productFeature" property="description"/></dd>

            <dt><g:message code="productFeature.shortDescription.label"
                           default="productFeature.shortDescription.label"/></dt>
            <dd><f:display bean="productFeature" property="shortDescription"/></dd>

            <dt><g:message code="default.sequenceNum.label" default="default.sequenceNum.label"/></dt>
            <dd><f:display bean="productFeature" property="sequenceNum"/></dd>

            <dt><g:message code="default.dateCreated.label" default="default.dateCreated.label"/></dt>
            <dd><f:display bean="productFeature" property="dateCreated">
                <g:formatDate format="yyyy-MMM-dd" date="${value}"/></f:display></dd>

            <dt><g:message code="default.lastUpdated.label" default="default.lastUpdated.label"/></dt>
            <dd><f:display bean="productFeature" property="lastUpdated">
                <g:formatDate format="yyyy-MMM-dd" date="${value}"/></f:display></dd>
        </dl>
    </div>

    <g:form resource="${this.productFeature}" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${this.productFeature}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>

</div> <%-- /.container --%>
</body>
</html>
