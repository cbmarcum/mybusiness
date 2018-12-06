<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'importSheet.label', default: 'ImportSheet')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">

    <a href="#create-importSheet" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                        default="Skip to content&hellip;"/></a>

    <g:render template="/common/subnav-list"/>

    <div id="create-importSheet" class="page-header">
        <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    </div>

    <g:render template="/common/flash-message"/>

    <g:render template="has-errors"/>

    <g:uploadForm name="upload" url="[action: 'upload', controller: 'importSheet']">
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>

        <div class="row">
            <div class="form-group col-md-6">
                <label for="sheet">Spreadsheet</label>
                <input type="file" id="sheet" name="sheet"/>

                <p class="help-block">Select an .ods or .xls spreadsheet</p>
            </div>
        </div>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>

</div> <%-- /.container --%>
</body>
</html>
