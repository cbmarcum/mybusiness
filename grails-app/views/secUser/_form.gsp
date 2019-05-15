<%@ page import="net.codebuilders.mybusiness.SecUser" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: secUser, field: 'username', 'error')} ">
        <label for="username">
            <g:message code="secUser.username.label" default="secUser.username.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="username"
                 value="${secUser?.username}"
                 maxLength="255" placeholder="255 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: secUser, field: 'password', 'error')} ">
        <label for="password">
            <g:message code="secUser.password.label" default="secUser.password.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="password" class="form-control" name="password"
                 value="${secUser?.password}"
                 maxLength="255" placeholder="255 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6">
        <div class="checkbox-inline ${hasErrors(bean: secUser, field: 'enabled', 'error')} ">
            <label>
                <g:checkBox name="enabled" value="${secUser?.enabled}"/>
                <g:message code="secUser.enabled.label" default="secUser.enabled.label"/>
            </label>
        </div>
    </div>

    <div class="form-group col-md-6">
        <div class="checkbox-inline ${hasErrors(bean: secUser, field: 'accountExpired', 'error')} ">
            <label>
                <g:checkBox name="accountExpired" value="${secUser?.accountExpired}"/>
                <g:message code="secUser.accountExpired.label" default="secUser.accountExpired.label"/>
            </label>
        </div>
    </div>

    <div class="form-group col-md-6">
        <div class="checkbox-inline ${hasErrors(bean: secUser, field: 'accountLocked', 'error')} ">
            <label>
                <g:checkBox name="accountLocked" value="${secUser?.accountLocked}"/>
                <g:message code="secUser.accountLocked.label" default="secUser.accountLocked.label"/>
            </label>
        </div>
    </div>

    <div class="form-group col-md-6">
        <div class="checkbox-inline ${hasErrors(bean: secUser, field: 'passwordExpired', 'error')} ">
            <label>
                <g:checkBox name="passwordExpired" value="${secUser?.passwordExpired}"/>
                <g:message code="secUser.passwordExpired.label" default="secUser.passwordExpired.label"/>
            </label>
        </div>
    </div>

</div> <%-- /.row --%>