<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div class="container">

    <div>
        <g:link class="btn btn-default" controller="admin" action="index"><g:message
                code="default.admin.home.label"/></g:link>
        <g:link class="btn btn-default" action="index"><g:message code="default.list.label"
                                                                  args="[entityName]"/></g:link>
        <g:link class="btn btn-default" action="create"><g:message code="default.new.label"
                                                                   args="[entityName]"/></g:link>
    </div>

    <div class="page-header">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    </div> <!-- /.page-header -->

    <g:if test="${flash.message}">
        <div class="alert alert-warning" role="alert">${flash.message}</div>
    </g:if>


    <div class="row">

        <dl class="dl-horizontal">
            <dt><g:message code="product.number.label" default="Number"/></dt>
            <dd><f:display bean="product" property="number"/></dd>

            <dt><g:message code="product.name.label" default="Name"/></dt>
            <dd><f:display bean="product" property="name"/></dd>

            <dt><g:message code="product.brand.label" default="Brand"/></dt>
            <dd><f:display bean="product" property="brand"/></dd>

            <dt><g:message code="product.shortDescription.label" default="Short Description"/></dt>
            <dd><f:display bean="product" property="shortDescription"/></dd>

            <dt><g:message code="product.longDescription.label" default="Long Description"/></dt>
            <dd><f:display bean="product" property="longDescription"/></dd>

            <dt><g:message code="product.largeDescription.label" default="Large Description"/></dt>
            <dd><f:display bean="product" property="largeDescription"/></dd>

            <dt><g:message code="product.conditionDescription.label" default="Condition Description"/></dt>
            <dd><f:display bean="product" property="conditionDescription"/></dd>

            <dt><g:message code="product.variantGroupId.label" default="Variant Group Id"/></dt>
            <dd><f:display bean="product" property="variantGroupId"/></dd>

            <dt><g:message code="product.taxCode.label" default="Tax Code"/></dt>
            <dd><f:display bean="product" property="taxCode"/></dd>

            <dt><g:message code="product.listPrice.label" default="List Price"/></dt>
            <dd><f:display bean="product" property="listPrice"/></dd>

            <dt><g:message code="product.shipWeight.label" default="Shipping Weight"/></dt>
            <dd><f:display bean="product" property="shipWeight"/></dd>

            <dt><g:message code="product.productType.label" default="Type"/></dt>
            <dd><f:display bean="product" property="productType"/></dd>

            <dt><g:message code="product.productConditionType.label" default="Condition"/></dt>
            <dd><f:display bean="product" property="productConditionType"/></dd>

            <dt><g:message code="product.primaryVariant.label" default="Primary Variant"/></dt>
            <dd><f:display bean="product" property="primaryVariant"/></dd>

            <dt><g:message code="product.display.label" default="Display"/></dt>
            <dd><f:display bean="product" property="display"/></dd>

            <dt><g:message code="product.showcase.label" default="Showcase"/></dt>
            <dd><f:display bean="product" property="showcase"/></dd>

            <dt><g:message code="product.outOfStock.label" default="Out of Stock"/></dt>
            <dd><f:display bean="product" property="outOfStock"/></dd>

            <dt><g:message code="product.webSell.label" default="Web Sell"/></dt>
            <dd><f:display bean="product" property="webSell"/></dd>

            <dt><g:message code="default.dateCreated.label" default="Created Date"/></dt>
            <dd><f:display bean="product" property="dateCreated"/></dd>

            <dt><g:message code="default.lastUpdated.label" default="Last Updated Date"/></dt>
            <dd><f:display bean="product" property="lastUpdated"/></dd>

            <dt><g:message code="product.salesDiscontinuationDate.label" default="Sales Discontinuation Date"/></dt>
            <dd><f:display bean="product" property="salesDiscontinuationDate"/></dd>

            <dt><g:message code="product.supportDiscontinuationDate.label" default="Support Discontinuation Date"/></dt>
            <dd><f:display bean="product" property="supportDiscontinuationDate"/></dd>

            <dt><g:message code="product.goodIdentifications.label" default="Good Identifications"/></dt>
            <dd>
                <g:each in="${product.goodIdentifications}" var="pg">
                    <b>${pg.goodIdentificationType}</b> - ${pg.value}<br/>
                </g:each>
            </dd>

            <dt><g:message code="product.productCategories.label" default="Product Categories"/></dt>
            <dd>
                <g:each in="${product.productCategories}" var="pc">
                    ${pc.description}<br/>
                </g:each>
            </dd>

            <dt><g:message code="product.productFeatureAppls.label" default="Features"/></dt>
            <dd>
                <g:each in="${product.productFeatureAppls}" var="pfa">
                    <b>${pfa.productFeature.productFeatureCategory.description}</b> - ${pfa.productFeature.description}<br/>
                </g:each>
            </dd>

            <dt><g:message code="product.otherAttributes.label" default="Other Attributes"/></dt>
            <dd>
                <g:each in="${product.otherAttributes}" var="oa">
                    ${oa}<br/>
                </g:each>
            </dd>

        </dl>

        <g:form resource="${this.product}" method="DELETE">
            <fieldset>
                <g:link class="btn btn-primary" action="edit" resource="${this.product}">
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>
                <!-- form action -->
                <input class="btn btn-danger" type="submit"
                       value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                       onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </fieldset>
        </g:form>

    </div> <!-- .row -->

    <h2><g:message code="product.photos.label" default="Photos"/></h2>

    <div class="table-responsive">
        <table class="table">

            <tbody>
            <g:each in="${product.photos}" var="photo">
                <tr>
                    <td width="160px">
                        <cb:image image="${photo.photo.getCloudFile('small')}"/><br/>
                    </td>
                    <td>
                        <dl class="dl-horizontal">
                            <dt><g:message code="photo.name.label" default="Name"/></dt>
                            <dd>${photo.name}</dd>
                            <dt><g:message code="photo.alt.label" default="Alt"/></dt>
                            <dd>${photo.alt}</dd>
                            <dt><g:message code="photo.title.label" default="Title"/></dt>
                            <dd>${photo.title}</dd>
                        </dl>
                    </td>
                </tr>

            </g:each>

            </tbody>
        </table>
    </div><!-- .table-responsive -->

</div> <!-- /.container -->
</body>
</html>
