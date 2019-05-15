<%@ page import="net.codebuilders.mybusiness.SecRole" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: secRole, field: 'authority', 'error')} ">
        <label for="authority">
            <g:message code="secRole.authority.label" default="secRole.authority.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="authority"
                 value="${secRole?.authority}"
                 maxLength="255" placeholder="255 chars max" required="true"/>
    </div>

</div> <%-- /.row --%>