<%@ page import="net.codebuilders.mybusiness.Notice" %>

<p>
    <svg class="bi text-danger" width="16" height="16">
        <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
    </svg>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="col-md-6 mb-3 ${hasErrors(bean: notice, field: 'name', 'error')} ">
        <label class="form-label" for="name">
            <g:message code="notice.name.label" default="notice.name.label"/>
            <svg class="bi text-danger" width="16" height="16">
                <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
            </svg>
        </label>
        <g:field type="text" class="form-control" name="name" value="${notice?.name}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="col-md-6 mb-3 ${hasErrors(bean: notice, field: 'page', 'error')} ">
        <label class="form-label" for="page">
            <g:message code="notice.page.label" default="notice.page.label"/>
        </label>
        <g:select class="form-select" name="page" from="${['Home', 'Product', 'Blog']}"
                  value="notice?.page"/>
    </div>

</div> <%-- /.row --%>

<div class="mb-3 ${hasErrors(bean: notice, field: 'longDescription', 'error')} ">
    <label class="form-label" for="longDescription">
        <g:message code="notice.longDescription.label" default="notice.longDescription.label"/>
        <svg class="bi text-danger" width="16" height="16">
            <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
        </svg>
    </label>
    <ckeditor:editor name="longDescription" height="200px" width="100%" toolbar="Basic">
        ${notice?.longDescription}
    </ckeditor:editor>
</div>

<div class="row">

    <div class="col-md-6 mb-3 ${hasErrors(bean: notice, field: 'fromDate', 'error')} ">
        <label class="form-label" for="fromDate">
            <g:message code="notice.fromDate.label" default="notice.fromDate.label"/>
            <svg class="bi text-danger" width="16" height="16">
                <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
            </svg>
        </label>
        <g:datePicker class="form-control" name="fromDate" precision="day"
                      value="${notice?.fromDate}" default="none" noSelection="['': '']"/>
    </div>

    <div class="col-md-6 mb-3 ${hasErrors(bean: notice, field: 'thruDate', 'error')} ">
        <label class="form-label" for="fromDate">
            <g:message code="notice.thruDate.label" default="notice.thruDate.label"/>
            <svg class="bi text-danger" width="16" height="16">
                <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
            </svg>
        </label>
        <g:datePicker class="form-control" name="thruDate" precision="day"
                      value="${notice?.thruDate}" default="none" noSelection="['': '']"/>
    </div>

    <div class="col-md-6 mb-3">
        <div class="form-check ${hasErrors(bean: notice, field: 'display', 'error')} ">
            <g:checkBox class="form-check-input" id="display" name="display" value="${notice?.display}"/>
            <label class="form-check-label" for="display">
                <g:message code="notice.display.label" default="notice.display.label"/>
            </label>
        </div>
    </div>

</div> <%-- /.row --%>