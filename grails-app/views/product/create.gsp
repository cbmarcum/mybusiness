<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
    <ckeditor:resources/>
    <asset:stylesheet src="bootstrap-datepicker3.css"/>
    <asset:javascript src="bootstrap-datepicker.js"/>
</head>

<body>
<div class="container">
    <a href="#create-product" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                    default="Skip to content&hellip;"/></a>

    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            <li><g:link class="list" action="index"><g:message code="default.list.label"
                                                               args="[entityName]"/></g:link></li>
        </ul>
    </div>

    <div id="create-product" class="page-header">
        <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.product}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.product}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="save">
        <fieldset class="form">
            <div class="row">

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'number', 'error')} ">
                    <label for="number">
                        <g:message code="product.number.label" default="Number"/>
                        <span class="required-indicator">*</span>
                    </label>
                    <g:field type="text" class="form-control" name="number" value="${product?.number}"
                             maxLength="40" placeholder="40 chars max" required="true"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'name', 'error')} ">
                    <label for="name">
                        <g:message code="product.name.label" default="Name"/>
                    </label>
                    <g:field type="text" class="form-control" name="name" value="${product?.name}"
                             maxLength="80" placeholder="80 chars max"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'brand', 'error')} ">
                    <label for="brand">
                        <g:message code="product.brand.label" default="Brand"/>
                    </label>
                    <g:field type="text" class="form-control" name="brand" value="${product?.brand}"
                             maxLength="60" placeholder="60 chars max"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'variantGroupId', 'error')} ">
                    <label for="variantGroupId">
                        <g:message code="product.variantGroupId.label" default="Variant Group Id"/>
                    </label>
                    <g:field type="text" class="form-control" name="variantGroupId" value="${product?.variantGroupId}"
                             maxLength="50" placeholder="50 chars max"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'taxCode', 'error')} ">
                    <label for="taxCode">
                        <g:message code="product.taxCode.label" default="Tax Code"/>
                    </label>
                    <g:field type="text" class="form-control" name="taxCode" value="${product?.taxCode}"
                             maxLength="50" placeholder="50 chars max"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'listPrice', 'error')} required">
                    <label for="listPrice">
                        <g:message code="product.listPrice.label" default="List Price"/>
                        <span class="required-indicator">*</span>
                    </label>
                    <g:field type="number" class="form-control" name="listPrice" required=""
                             value="${fieldValue(bean: product, field: 'listPrice')}"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'shipWeight', 'error')} required">
                    <label for="shipWeight">
                        <g:message code="product.shipWeight.label" default="Shipping Weight"/>
                        <span class="required-indicator">*</span>
                    </label>
                    <g:field type="number" class="form-control" name="shipWeight" required=""
                             value="${fieldValue(bean: product, field: 'shipWeight')}"/>
                </div>

            </div> <%-- /.row --%>

            <div class="form-group ${hasErrors(bean: product, field: 'shortDescription', 'error')} ">
                <label for="shortDescription">
                    <g:message code="product.shortDescription.label" default="Short Description"/>
                </label>
                <g:field type="text" class="form-control" name="shortDescription" value="${product?.shortDescription}"
                         maxLength="200" placeholder="200 chars max"/>
            </div>

            <div class="form-group ${hasErrors(bean: product, field: 'longDescription', 'error')} ">
                <label for="longDescription">
                    <g:message code="product.longDescription.label" default="Long Description"/>
                </label>
                <ckeditor:editor name="longDescription" height="200px" width="100%" toolbar="Basic">
                    ${product?.longDescription}
                </ckeditor:editor>
            </div> <%-- /.form-group --%>

            <div class="form-group ${hasErrors(bean: product, field: 'largeDescription', 'error')} ">
                <label for="largeDescription">
                    <g:message code="product.largeDescription.label" default="Large Description"/>
                </label>
                <ckeditor:editor name="largeDescription" height="200px" width="100%" toolbar="Basic">
                    ${product?.largeDescription}
                </ckeditor:editor>
            </div> <%-- /.form-group --%>

            <div class="form-group ${hasErrors(bean: product, field: 'conditionDescription', 'error')} ">
                <label for="conditionDescription">
                    <g:message code="product.conditionDescription.label" default="Condition Description"/>
                </label>
                <g:field type="text" class="form-control" name="conditionDescription"
                         value="${product?.conditionDescription}"
                         maxLength="1000" placeholder="1000 chars max"/>
            </div>

            <div class="row">
                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'salesDiscontinuationDate', 'error')} ">
                    <label for="salesDiscontinuationDate">
                        <g:message code="product.salesDiscontinuationDate.label" default="Sales Discontinuation Date"/>
                    </label>
                    <g:datePicker class="form-control" name="salesDiscontinuationDate" precision="day"
                                  value="${product?.salesDiscontinuationDate}" default="none" noSelection="['': '']"/>
                </div>

                <div class="form-group col-md-6 ${hasErrors(bean: product, field: 'supportDiscontinuationDate', 'error')} ">
                    <label for="supportDiscontinuationDate">
                        <g:message code="product.supportDiscontinuationDate.label"
                                   default="Support Discontinuation Date"/>
                    </label>
                    <g:datePicker class="form-control" name="supportDiscontinuationDate" precision="day"
                                  value="${product?.supportDiscontinuationDate}" default="none" noSelection="['': '']"/>
                </div>
            </div>

            <div class="checkbox ${hasErrors(bean: product, field: 'display', 'error')} ">
                <label>
                    <g:checkBox name="display" value="${product?.display}"/>
                    <g:message code="product.display.label" default="Display"/>
                </label>
            </div>

            <div class="checkbox ${hasErrors(bean: product, field: 'showcase', 'error')} ">
                <label for="showcase">
                    <g:checkBox name="showcase" value="${product?.showcase}"/>
                    <g:message code="product.showcase.label" default="Showcase"/>
                </label>

            </div>

            <div class="checkbox ${hasErrors(bean: product, field: 'outOfStock', 'error')} ">
                <label for="outOfStock">
                    <g:checkBox name="outOfStock" value="${product?.outOfStock}"/>
                    <g:message code="product.outOfStock.label" default="Out of Stock"/>
                </label>
            </div>

            <div class="input-group date" data-provide="datepicker">
                <input type="text" class="form-control">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-th"></span>
                </div>
            </div>

        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>

</div> <!-- /.container -->
</body>
</html>
