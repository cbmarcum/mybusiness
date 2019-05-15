<%@ page import="net.codebuilders.mybusiness.SecUserSecRole" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: secUserSecRole, field: 'secUser', 'error')} ">
        <label for="secUser">
            <g:message code="secUser.label" default="secUser.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="selectpicker form-control" name="secUser" from="${net.codebuilders.mybusiness.SecUser.list()}"
                  data-size="4"
                  value="${secUserSecRole?.secUser}" required="true" multiple data-max-options="1"
                  data-live-search="true" title="Select a user"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: secUserSecRole, field: 'secRole', 'error')} ">
        <label for="secRole">
            <g:message code="secRole.label" default="secRole.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select class="selectpicker form-control" name="secRole" from="${net.codebuilders.mybusiness.SecRole.list()}"
                  data-size="4"
                  value="${secUserSecRole?.secRole}" required="true" multiple data-max-options="1"
                  data-live-search="true" title="Select a role"/>
    </div>

</div> <%-- /.row --%>