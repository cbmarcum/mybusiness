<%@ page import="net.codebuilders.mybusiness.Product" %>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'number', 'error')} ">
        <label for="number">
            <g:message code="product.number.label" default="Number"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="number" value="${product?.number}"
                 maxLength="40" placeholder="40 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="product.name.label" default="Name"/>
        </label>
        <g:field type="text" class="form-control" name="name" value="${product?.name}"
                 maxLength="80" placeholder="80 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'brand', 'error')} ">
        <label for="brand">
            <g:message code="product.brand.label" default="Brand"/>
        </label>
        <g:field type="text" class="form-control" name="brand" value="${product?.brand}"
                 maxLength="60" placeholder="60 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'variantGroupId', 'error')} ">
        <label for="variantGroupId">
            <g:message code="product.variantGroupId.label" default="Variant Group Id"/>
        </label>
        <g:field type="text" class="form-control" name="variantGroupId" value="${product?.variantGroupId}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'taxCode', 'error')} ">
        <label for="taxCode">
            <g:message code="product.taxCode.label" default="Tax Code"/>
        </label>
        <g:field type="text" class="form-control" name="taxCode" value="${product?.taxCode}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'listPrice', 'error')}">
        <label for="listPrice">
            <g:message code="product.listPrice.label" default="List Price"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="number" class="form-control" name="listPrice"
                 value="${fieldValue(bean: product, field: 'listPrice')}" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'shipWeight', 'error')}">
        <label for="shipWeight">
            <g:message code="product.shipWeight.label" default="Shipping Weight"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="number" class="form-control" name="shipWeight" required="true"
                 value="${fieldValue(bean: product, field: 'shipWeight')}"/>
    </div>

</div> <%-- /.row --%>

<div class="form-group ${hasErrors(bean: product, field: 'shortDescription', 'error')} ">
    <label for="shortDescription">
        <g:message code="product.shortDescription.label" default="Short Description"/>
    </label>
    <g:field type="text" class="form-control" name="shortDescription" value="${product?.shortDescription}"
             maxLength="200" placeholder="200 chars max"/>
</div>

<div class="form-group ${hasErrors(bean: product, field: 'longDescription', 'error')} ">
    <label for="longDescription">
        <g:message code="product.longDescription.label" default="Long Description"/>
    </label>
    <ckeditor:editor name="longDescription" height="200px" width="100%" toolbar="Basic">
        ${product?.longDescription}
    </ckeditor:editor>
</div> <%-- /.form-group --%>

<div class="form-group ${hasErrors(bean: product, field: 'largeDescription', 'error')} ">
    <label for="largeDescription">
        <g:message code="product.largeDescription.label" default="Large Description"/>
    </label>
    <ckeditor:editor name="largeDescription" height="200px" width="100%" toolbar="Basic">
        ${product?.largeDescription}
    </ckeditor:editor>
</div> <%-- /.form-group --%>

<div class="form-group ${hasErrors(bean: product, field: 'conditionDescription', 'error')} ">
    <label for="conditionDescription">
        <g:message code="product.conditionDescription.label" default="Condition Description"/>
    </label>
    <g:field type="text" class="form-control" name="conditionDescription"
             value="${product?.conditionDescription}"
             maxLength="1000" placeholder="1000 chars max"/>
</div>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'salesDiscontinuationDate', 'error')} ">
        <label for="salesDiscontinuationDate">
            <g:message code="product.salesDiscontinuationDate.label" default="Sales Discontinuation Date"/>
        </label>
        <g:datePicker class="form-control" name="salesDiscontinuationDate" precision="day"
                      value="${product?.salesDiscontinuationDate}" default="none" noSelection="['': '']"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'supportDiscontinuationDate', 'error')} ">
        <label for="supportDiscontinuationDate">
            <g:message code="product.supportDiscontinuationDate.label"
                       default="Support Discontinuation Date"/>
        </label>
        <g:datePicker class="form-control" name="upportDiscontinuationDate" precision="day"
                      value="${product?.supportDiscontinuationDate}" default="none" noSelection="['': '']"/>
    </div>

</div>


<div class="checkbox ${hasErrors(bean: product, field: 'display', 'error')} ">
    <label>
        <g:checkBox name="display" value="${product?.display}"/>
        <g:message code="product.display.label" default="Display"/>
    </label>
</div>

<div class="checkbox ${hasErrors(bean: product, field: 'showcase', 'error')} ">
    <label for="showcase">
        <g:checkBox name="showcase" value="${product?.showcase}"/>
        <g:message code="product.showcase.label" default="Showcase"/>
    </label>

</div>

<div class="checkbox ${hasErrors(bean: product, field: 'outOfStock', 'error')} ">
    <label for="outOfStock">
        <g:checkBox name="outOfStock" value="${product?.outOfStock}"/>
        <g:message code="product.outOfStock.label" default="Out of Stock"/>
    </label>
</div>

<div class="checkbox ${hasErrors(bean: product, field: 'webSell', 'error')} ">
    <label for="webSell">
        <g:checkBox name="webSell" value="${product?.webSell}"/>
        <g:message code="product.webSell.label" default="Web Sell"/>
    </label>
</div>

<div class="row">
    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'goodIdentifications', 'error')} ">
        <label for="goodIdentifications">
            <g:message code="product.goodIdentifications.label" default="Good Identifications"/>
        </label>
        <ul class="one-to-many">
            <g:each in="${product?.goodIdentifications ?}" var="p">
                <li><g:link controller="goodIdentification" action="show"
                            id="${p.id}">${p?.goodIdentificationType.name} - ${p?.value}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="goodIdentification" action="create"
                        params="['product.id': product?.id]">${message(code: 'default.add.label', args: [message(code: 'goodIdentification.label', default: 'GoodIdentification')])}</g:link>
            </li>
        </ul>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'otherAttributes', 'error')} ">
        <label for="otherAttributes">
            <g:message code="product.otherAttributes.label" default="Other Attributes"/>

        </label>

    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'productCategories', 'error')} ">
        <label for="productCategories">
            <g:message code="product.productCategories.label" default="Product Categories"/>

        </label>
        <g:select name="productCategories" from="${net.codebuilders.mybusiness.ProductCategory.list()}"
                  multiple="multiple"
                  optionKey="id" optionValue="description" size="5" value="${product?.productCategories*.id}"
                  class="many-to-many"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'productFeatureAppls', 'error')} ">
        <label for="productFeatureAppls">
            <g:message code="product.productFeatureAppls.label" default="Product Feature Appls"/>

        </label>

        <ul class="one-to-many">
            <g:each in="${product?.productFeatureAppls ?}" var="p">
                <li><g:link controller="productFeatureAppl" action="show"
                            id="${p.id}">${p?.productFeature?.description}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="productFeatureAppl" action="create"
                        params="['product.id': product?.id]">${message(code: 'default.add.label', args: [message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl')])}</g:link>
            </li>
        </ul>

    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'productType', 'error')} required">
        <label for="productType">
            <g:message code="product.productType.label" default="Product Type"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select name="productType" from="${net.codebuilders.mybusiness.ProductType?.values()}"
                  keys="${net.codebuilders.mybusiness.ProductType.values()*.name()}" required=""
                  value="${product?.productType?.name()}"/>
    </div>

</div> <%-- /.row --%>