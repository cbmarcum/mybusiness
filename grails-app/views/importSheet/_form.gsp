<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">
    <div class="form-group col-md-6 ${hasErrors(bean: importSheet, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="importSheet.name.label" default="importSheet.name.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="name" value="${importSheet?.name}"
                 maxLength="50" placeholder="50 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: importSheet, field: 'importSheetStatusType', 'error')}">
        <label for="importSheetStatusType">
            <g:message code="importSheet.importSheetStatusType.label"
                       default="importSheet.importSheetStatusType.label"/>
        </label>
        <g:field type="text" class="form-control" name="importSheetStatusType" value="${importSheet?.importSheetStatusType?.name()}"
                 disabled="true"/>

    </div>
</div> <%-- ./row --%>