<%@ page import="net.codebuilders.mybusiness.ProductCategory" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: productCategory, field: 'description', 'error')} ">
        <label for="description">
            <g:message code="default.description.label"
                       default="default.description.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="description"
                 value="${productCategory?.description}"
                 maxLength="50" placeholder="50 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: productCategory, field: 'parent', 'error')} ">
        <label for="parent">
            <g:message code="productCategory.parent.label" default="productCategory.parent.label"/>
        </label>
        <g:select class="selectpicker form-control" name="parent" from="${net.codebuilders.mybusiness.ProductCategory.list()}"
                  optionKey="id" data-size="4"
                  value="${productCategory?.parent?.id}" noSelection="${['null': 'No Parent']}"/>
    </div>

</div> <%-- /.row --%>