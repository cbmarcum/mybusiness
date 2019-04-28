<%@ page import="net.codebuilders.mybusiness.ProductFeatureCategory" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: productFeatureCategory, field: 'description', 'error')} ">
        <label for="description">
            <g:message code="productFeatureCategory.description.label"
                       default="productFeatureCategory.description.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="description"
                 value="${productFeatureCategory?.shortDescription}"
                 maxLength="50" placeholder="50 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeatureCategory, field: 'shortDescription', 'error')} ">
        <label for="shortDescription">
            <g:message code="productFeatureCategory.shortDescription.label"
                       default="productFeatureCategory.shortDescription.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="shortDescription"
                 value="${productFeatureCategory?.shortDescription}"
                 maxLength="25" placeholder="25 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productFeatureCategory, field: 'sequenceNum', 'error')} ">
        <label for="sequenceNum">
            <g:message code="default.sequenceNum.label" default="default.sequenceNum.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="number" class="form-control" name="sequenceNum" value="${productFeatureCategory?.sequenceNum}"
                 step="1" placeholder="Integer" required="true"/>
    </div>

</div> <%-- /.row --%>