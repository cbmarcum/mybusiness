<div>
<!-- display errors in form -->
    <g:if test="${flash.message}">
        <div class="alert alert-warning" role="alert">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${orderContact}">
        <!--        <g:set var="contacts" value="flash.contacts"/> -->
        <div class="alert alert-danger" role="alert">
            <g:renderErrors bean="${orderContact}" as="list"/>
        </div>
    </g:hasErrors>

<!-- form sends back to controller -->
    <g:form>
        <!-- hidden fields can go here -->
        <fieldset class="form">
            <!-- <legend>Client Information</legend>-->
            <p>
                <span class="required-indicator">*</span>&nbsp;=&nbsp;
            <g:message code="default.form.required.label" default="Denotes required field"/>
            </p>

            <div class="row">

                <!-- Contact -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="mbContact"><g:message code="default.form.contact.label" default="Contact Name"/></label>
                    <g:select class="form-select" name="mbContact" from="${contactList}" value="${contactCurrent}"/>
                </div>


                <!-- Full Name -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="name"><g:message code="default.form.fullname.label" default="Full Name"/>
                        <span class="required-indicator">*</span></label>
                    <g:field type="text" class="form-control" name="name" value="${orderContact?.fullName}"
                             placeholder="Full Name" maxLength="50" required="true"/>
                </div>

                <!-- Company Name -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="company"><g:message code="default.form.company.label" default="Company"/></label>
                    <g:field type="text" class="form-control" name="company" value="${orderContact?.company}"
                             placeholder="Company Name"/>
                </div>

                <!-- Address -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="address"><g:message code="default.form.address.label" default="Address"/></label>
                    <g:field type="text" class="form-control" name="address" value="${orderContact?.address}"
                             placeholder="Address"/>
                </div>

                <!-- City -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="city"><g:message code="default.form.city.label" default="City"/></label>
                    <g:field type="text" class="form-control" name="city" value="${orderContact?.city}"
                             placeholder="City"/>
                </div>

                <!-- State -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="state"><g:message code="default.form.state.label" default="State"/></label>
                    <g:field type="text" class="form-control" name="state" value="${orderContact?.state}"
                             placeholder="State"/>
                </div>



                <!-- Postal Code -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="postal"><g:message code="default.form.postalcode.label" default="Postal Code"/></label>
                    <g:field type="text" class="form-control" name="postal" value="${orderContact?.postal}"
                             placeholder="Postal Code"/>
                </div>


                <!-- email -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="email1"><g:message code="default.form.email.label" default="E-Mail"/>
                        <span class="required-indicator">*</span></label>
                    <g:field type="email" class="form-control" name="email1" value="${orderContact?.email}"
                             placeholder="E-Mail" required="true"/>
                </div>

                <!-- phone -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="phone"><g:message code="default.form.phone.label" default="Phone"/>
                        <span class="required-indicator">*</span></label>
                    <g:field type="tel" class="form-control" name="phone" value="${orderContact?.phone}"
                             placeholder="(XXX) XXX-XXXX"
                             pattern="\\D*([2-9]\\d{2})(\\D*)([2-9]\\d{2})(\\D*)(\\d{4})\\D*" required="true"/>
                </div>

                <!-- cc me checkbox -->
                <div class="col-md-12 mb-3">
                    <div class="form-check">
                        <g:checkBox class="form-check-input" id="ccMe" name="ccMe" value="${true}" />
                        <label class="form-check-label" for="ccMe"><g:message code="default.form.ccme.label" default="cc Me"/></label>
                    </div>
                </div>

                <!-- Questions or Comments -->
                <div class="col-md-6 mb-3">
                    <label class="form-label" for="comment"><g:message code="default.form.comments.label" default="Comments"/>
                        <span class="required-indicator">*</span></label>
                    <g:textArea class="form-control" name="comment" value="${orderContact?.comment}" rows="30"
                                columns="400"
                                style="height:150px" required="true"/>
                </div>

                <!-- using recaptcha plugin -->
                <div class="col-md-6 mb-3">

                    <recaptcha:ifEnabled>
                        <recaptcha:recaptcha theme="${grailsApplication.config.mybusiness.recaptcha.style}"/>
                    </recaptcha:ifEnabled>
                </div>

                <div class="col-sm-10 col-md-6 mb-3">

                    <g:actionSubmit class="btn btn-default" action="email"
                                    value="${message(code: 'default.button.submit.label', default: 'Submit')}"/>
                </div>
            </div> <!-- /.row -->
        </fieldset>
    </g:form>

</div>


