<%@ page import="net.codebuilders.mybusiness.RssFeed" %>

<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="form-group col-md-6 ${hasErrors(bean: rssFeed, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="default.name.label" default="default.name.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="text" class="form-control" name="name"
                 value="${rssFeed?.name}"
                 maxLength="50" placeholder="50 chars max" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: rssFeed, field: 'url', 'error')} ">
        <label for="url">
            <g:message code="default.url.label"
                       default="default.url.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="url" class="form-control" name="url"
                 value="${rssFeed?.url}"
                 placeholder="valid URL" required="true"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: rssFeed, field: 'max', 'error')} ">
        <label for="max">
            <g:message code="rss.maxFeeds.label" default="rss.maxFeeds.label"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field type="number" class="form-control" name="max" value="${rssFeed?.max}"
                 step="1" placeholder="Integer" required="true"/>
    </div>

    <div class="form-group col-md-6">
        <div class="checkbox-inline ${hasErrors(bean: rssFeed, field: 'display', 'error')} ">
            <label>
                <g:checkBox name="display" value="${rssFeed?.display}"/>
                <g:message code="default.display.label" default="default.display.label"/>
            </label>
        </div>
    </div>

</div> <%-- /.row --%>