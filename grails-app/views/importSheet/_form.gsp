<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">
    <div class="form-group col-md-6 ${hasErrors(bean: importSheet, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="importSheet.name.label" default="importSheet.name.label"/>
        </label>
        <g:field type="text" class="form-control" name="name" value="${importSheet?.name}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: importSheet, field: 'importSheetStatusType', 'error')} required">
        <label for="importSheetStatusType">
            <g:message code="importSheet.importSheetStatusType.label" default="importSheet.importSheetStatusType.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="form-control" name="importSheetStatusType" from="${net.codebuilders.mybusiness.ImportSheetStatusType?.values()}"
                  keys="${net.codebuilders.mybusiness.ImportSheetStatusType.values()*.name()}" required="true"
                  value="${importSheet?.importSheetStatusType?.name()}"/>
    </div>
</div> <%-- ./row --%>