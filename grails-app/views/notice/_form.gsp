<%@ page import="net.codebuilders.mybusiness.Notice" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: notice, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="notice.name.label" default="notice.name.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="name" value="${notice?.name}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: notice, field: 'page', 'error')} ">
        <label for="page">
            <g:message code="notice.page.label" default="notice.page.label"/>
        </label>
        <g:select class="form-control" name="page" from="${['Home', 'Product', 'Blog']}"
                  value="notice?.page"/>
    </div>

</div> <%-- /.row --%>

<div class="form-group ${hasErrors(bean: notice, field: 'longDescription', 'error')} ">
    <label for="longDescription">
        <g:message code="notice.longDescription.label" default="notice.longDescription.label"/>
        <span class="required-indicator">*</span>
    </label>
    <ckeditor:editor name="longDescription" height="200px" width="100%" toolbar="Basic">
        ${notice?.longDescription}
    </ckeditor:editor>
</div> <%-- /.form-group --%>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: notice, field: 'fromDate', 'error')} ">
        <label for="fromDate">
            <g:message code="notice.fromDate.label" default="notice.fromDate.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:datePicker class="form-control" name="fromDate" precision="day"
                      value="${notice?.fromDate}" default="none" noSelection="['': '']"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: notice, field: 'thruDate', 'error')} ">
        <label for="fromDate">
            <g:message code="notice.thruDate.label" default="notice.thruDate.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:datePicker class="form-control" name="thruDate" precision="day"
                      value="${notice?.thruDate}" default="none" noSelection="['': '']"/>
    </div>

    <div class="form-group  col-md-6">
        <div class="checkbox-inline ${hasErrors(bean: notice, field: 'display', 'error')} ">
            <label>
                <g:checkBox name="display" value="${notice?.display}"/>
                <g:message code="notice.display.label" default="notice.display.label"/>
            </label>
        </div>
    </div>

</div> <%-- /.row --%>