<%@ page import="net.codebuilders.mybusiness.ProductFeature" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: productFeature, field: 'productFeatureCategory', 'error')} ">
        <label for="productFeatureCategory">
            <g:message code="productFeatureCategory.label" default="productFeatureCategory.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" name="productFeatureCategory" from="${net.codebuilders.mybusiness.ProductFeatureCategory.list()}"
                  optionKey="id" optionValue="description"
                  value="${productFeature?.productFeatureCategory?.id}"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeature, field: 'description', 'error')} ">
        <label for="description">
            <g:message code="productFeature.description.label"
                       default="productFeature.description.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="description"
                 value="${productFeature?.description}"
                 maxLength="50" placeholder="50 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeature, field: 'shortDescription', 'error')} ">
        <label for="shortDescription">
            <g:message code="productFeature.shortDescription.label"
                       default="productFeature.shortDescription.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="shortDescription"
                 value="${productFeature?.shortDescription}"
                 maxLength="25" placeholder="25 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeature, field: 'sequenceNum', 'error')} ">
        <label for="sequenceNum">
            <g:message code="default.sequenceNum.label" default="default.sequenceNum.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="number" class="form-control" name="sequenceNum" value="${productFeature?.sequenceNum}"
                 step="1" placeholder="Integer" required="true"/>
    </div>

</div> <%-- /.row --%>