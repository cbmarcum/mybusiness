<%@ page import="net.codebuilders.mybusiness.RssFeed" %>

<p>
    <svg class="bi text-danger" width="16" height="16">
        <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
    </svg>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">

    <div class="col-md-6 mb-3 ${hasErrors(bean: rssFeed, field: 'name', 'error')} ">
        <label class="form-label" for="name">
            <g:message code="default.name.label" default="default.name.label"/>
            <svg class="bi text-danger" width="16" height="16">
                <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
            </svg>
        </label>
        <g:field type="text" class="form-control" name="name"
                 value="${rssFeed?.name}"
                 maxLength="50" placeholder="50 chars max" required="true"/>
    </div>

    <div class="col-md-6 mb-3 ${hasErrors(bean: rssFeed, field: 'url', 'error')} ">
        <label class="form-label" for="url">
            <g:message code="default.url.label"
                       default="default.url.label"/>
            <svg class="bi text-danger" width="16" height="16">
                <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
            </svg>
        </label>
        <g:field type="url" class="form-control" name="url"
                 value="${rssFeed?.url}"
                 placeholder="valid URL" required="true"/>
    </div>

    <div class="col-md-6 mb-3 ${hasErrors(bean: rssFeed, field: 'max', 'error')} ">
        <label class="form-label" for="max">
            <g:message code="rss.maxFeeds.label" default="rss.maxFeeds.label"/>
            <svg class="bi text-danger" width="16" height="16">
                <use xlink:href="/assets/bootstrap-icons.svg#suit-diamond-fill"/>
            </svg>
        </label>
        <g:field type="number" class="form-control" name="max" value="${rssFeed?.max}"
                 step="1" placeholder="Integer" required="true"/>
    </div>

    <div class="col-md-6 mb-3">
        <div class="form-check ${hasErrors(bean: rssFeed, field: 'display', 'error')} ">
            <g:checkBox class="form-check-input" id="display" name="display" value="${rssFeed?.display}"/>
            <label class="form-check-label" for="display">
                <g:message code="default.display.label" default="default.display.label"/>
            </label>
        </div>
    </div>

</div> <%-- /.row --%>