<p>
    <span class="required-indicator">*</span>&nbsp;=&nbsp;
<g:message code="default.form.required.label" default="Denotes a required field"/>
</p>

<div class="row">
    <div class="form-group col-md-6 ${hasErrors(bean: photo, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="photo.name.label" default="photo.name.label"/>
        </label>
        <g:field type="text" class="form-control" name="name" value="${photo?.name}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: photo, field: 'alt', 'error')} ">
        <label for="alt">
            <g:message code="photo.alt.label" default="photo.alt.label"/>
        </label>
        <g:field type="text" class="form-control" name="alt" value="${photo?.alt}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>

    <div class="form-group col-md-6 ${hasErrors(bean: photo, field: 'title', 'error')} ">
        <label for="title">
            <g:message code="photo.title.label" default="photo.title.label"/>
        </label>
        <g:field type="text" class="form-control" name="title" value="${photo?.title}"
                 maxLength="50" placeholder="50 chars max"/>
    </div>
</div> <%-- ./row --%>