<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: productFeatureAppl, field: 'product', 'error')} ">
        <label for="product">
            <g:message code="product.label" default="product.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="selectpicker form-control" name="product" from="${net.codebuilders.mybusiness.Product?.list()}"
                  optionKey="id" size="5" value="${productFeatureAppl?.product?.id}"
                  required="true" multiple data-max-options="1" data-live-search="true" title="Select a product by sku or id"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeatureAppl, field: 'productFeature', 'error')} ">
        <label for="product">
            <g:message code="productFeature.label" default="productFeature.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="selectpicker form-control" name="productFeature"
                  from="${net.codebuilders.mybusiness.ProductFeature?.list()}"
                  optionKey="id" size="5" value="${productFeatureAppl?.productFeature?.id}"
                  required="true" multiple data-max-options="1" data-live-search="true" title="Select product feature"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeatureAppl, field: 'productFeatureApplType', 'error')} required">
        <label for="productFeatureApplType">
            <g:message code="productFeatureApplType.label" default="productFeatureApplType.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" name="productFeatureApplType"
                  from="${net.codebuilders.mybusiness.ProductFeatureApplType?.values()}"
                  keys="${net.codebuilders.mybusiness.ProductFeatureApplType.values()*.name()}" required="true"
                  value="${productFeatureAppl?.productFeatureApplType?.name()}"/>
    </div>

    <div class="checkbox-inline ${hasErrors(bean: productFeatureAppl, field: 'display', 'error')} ">
        <label>
            <g:checkBox name="display" value="${productFeatureAppl?.display}"/>
            <g:message code="default.display.label" default="default.display.label"/>
        </label>
    </div>

</div> <%-- /.row --%>
