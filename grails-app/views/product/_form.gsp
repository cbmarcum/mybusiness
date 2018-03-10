<%@ page import="net.codebuilders.mybusiness.Product" %>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'number', 'error')} ">
    <label for="number">
        <g:message code="product.number.label" default="Number"/>

    </label>
    <g:textField name="number" value="${productInstance?.number}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="product.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${productInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'shortDescription', 'error')} ">
    <label for="shortDescription">
        <g:message code="product.shortDescription.label" default="Short Description"/>

    </label>
    <g:textField name="shortDescription" value="${productInstance?.shortDescription}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'longDescription', 'error')} ">
    <label for="longDescription">
        <g:message code="product.longDescription.label" default="Long Description"/>

    </label>
    <ckeditor:editor name="longDescription" height="400px" width="90%" toolbar="Full">
        ${productInstance?.longDescription}
    </ckeditor:editor>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'largeDescription', 'error')} ">
    <label for="largeDescription">
        <g:message code="product.largeDescription.label" default="Large Description"/>
    </label>
    <ckeditor:editor name="largeDescription" height="400px" width="90%" toolbar="Full">
        ${productInstance?.largeDescription}
    </ckeditor:editor>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'salesDiscontinuationDate', 'error')} ">
    <label for="salesDiscontinuationDate">
        <g:message code="product.salesDiscontinuationDate.label" default="Sales Discontinuation Date"/>

    </label>
    <g:datePicker name="salesDiscontinuationDate" precision="day" value="${productInstance?.salesDiscontinuationDate}"
                  default="none" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'supportDiscontinuationDate', 'error')} ">
    <label for="supportDiscontinuationDate">
        <g:message code="product.supportDiscontinuationDate.label" default="Support Discontinuation Date"/>

    </label>
    <g:datePicker name="supportDiscontinuationDate" precision="day"
                  value="${productInstance?.supportDiscontinuationDate}" default="none" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'display', 'error')} ">
    <label for="display">
        <g:message code="product.display.label" default="Display"/>

    </label>
    <g:checkBox name="display" value="${productInstance?.display}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'showcase', 'error')} ">
    <label for="showcase">
        <g:message code="product.showcase.label" default="Showcase"/>

    </label>
    <g:checkBox name="showcase" value="${productInstance?.showcase}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'outOfStock', 'error')} ">
    <label for="outOfStock">
        <g:message code="product.outOfStock.label" default="Out of Stock"/>

    </label>
    <g:checkBox name="outOfStock" value="${productInstance?.outOfStock}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'webSell', 'error')} ">
    <label for="webSell">
        <g:message code="product.webSell.label" default="Web Sell"/>

    </label>
    <g:checkBox name="webSell" value="${productInstance?.webSell}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'goodIdentifications', 'error')} ">
    <label for="goodIdentifications">
        <g:message code="product.goodIdentifications.label" default="Good Identifications"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${productInstance?.goodIdentifications ?}" var="p">
            <li><g:link controller="goodIdentification" action="show"
                        id="${p.id}">${p?.goodIdentificationType.name} - ${p?.value}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="goodIdentification" action="create"
                    params="['product.id': productInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'goodIdentification.label', default: 'GoodIdentification')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'listPrice', 'error')} required">
    <label for="listPrice">
        <g:message code="product.listPrice.label" default="List Price"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="listPrice" required=""
             value="${fieldValue(bean: productInstance, field: 'listPrice')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'shipWeight', 'error')} required">
    <label for="shipWeight">
        <g:message code="product.shipWeight.label" default="Shipping Weight"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="shipWeight" required=""
             value="${fieldValue(bean: productInstance, field: 'shipWeight')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'otherAttributes', 'error')} ">
    <label for="otherAttributes">
        <g:message code="product.otherAttributes.label" default="Other Attributes"/>

    </label>

</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'productCategories', 'error')} ">
    <label for="productCategories">
        <g:message code="product.productCategories.label" default="Product Categories"/>

    </label>
    <g:select name="productCategories" from="${net.codebuilders.mybusiness.ProductCategory.list()}" multiple="multiple"
              optionKey="id" optionValue="name" size="5" value="${productInstance?.productCategories*.id}"
              class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'productFeatureAppls', 'error')} ">
    <label for="productFeatureAppls">
        <g:message code="product.productFeatureAppls.label" default="Product Feature Appls"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${productInstance?.productFeatureAppls ?}" var="p">
            <li><g:link controller="productFeatureAppl" action="show"
                        id="${p.id}">${p?.productFeature?.description}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="productFeatureAppl" action="create"
                    params="['product.id': productInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'productFeatureAppl.label', default: 'ProductFeatureAppl')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'productFeatureCategories', 'error')} ">
    <label for="productFeatureCategories">
        <g:message code="product.productFeatureCategories.label" default="Product Feature Categories"/>

    </label>
    <g:select name="productFeatureCategories" from="${net.codebuilders.mybusiness.ProductFeatureCategory.list()}"
              multiple="multiple" optionKey="id" optionValue="description" size="5"
              value="${productInstance?.productFeatureCategories*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productInstance, field: 'productType', 'error')} required">
    <label for="productType">
        <g:message code="product.productType.label" default="Product Type"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select name="productType" from="${net.codebuilders.mybusiness.ProductType?.values()}"
              keys="${net.codebuilders.mybusiness.ProductType.values()*.name()}" required=""
              value="${productInstance?.productType?.name()}"/>
</div>


