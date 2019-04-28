<g:hasErrors bean="${this.productFeature}">
    <div class="alert alert-danger alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <i class="fas fa-exclamation-triangle fa-2x">&nbsp;</i>
        <ul>
            <g:eachError bean="${this.productFeature}" var="error">
                <li>
                    <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>
                    <g:message error="${error}"/>
                </li>
            </g:eachError>
        </ul>
    </div>
</g:hasErrors>