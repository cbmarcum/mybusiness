<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: goodIdentification, field: 'product', 'error')} ">
        <label for="product">
            <g:message code="product.label" default="product.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" name="product" from="${net.codebuilders.mybusiness.Product?.list()}"
                  optionKey="id" size="5" value="${goodIdentification?.product?.id}"
                  required="true" />
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: goodIdentification, field: 'goodIdentificationType', 'error')} required">
        <label for="goodIdentificationType">
            <g:message code="goodIdentificationType.label" default="goodIdentificationType.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" name="goodIdentificationType" from="${net.codebuilders.mybusiness.GoodIdentificationType?.values()}"
                  keys="${net.codebuilders.mybusiness.GoodIdentificationType.values()*.name()}" required="true"
                  value="${goodIdentification?.goodIdentificationType?.name()}"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: goodIdentification, field: 'value', 'error')} ">
        <label for="value">
            <g:message code="default.value.label" default="default.value.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="value" value="${goodIdentification?.value}"
                 maxLength="50" placeholder="50 chars max" required="true" />
    </div>

</div> <%-- /.row --%>
