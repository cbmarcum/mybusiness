<g:if test="${noticeList}">
    <g:each in="${noticeList}" status="i" var="noticeInstance">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">Close</span></button>

            <h2>${fieldValue(bean: noticeInstance, field: "name")}</h2>

            <p>
                ${noticeInstance.longDescription.encodeAsRaw()}
            </p>

        <!-- only create show link to administrator -->
            <sec:access expression="hasRole('ROLE_ADMIN')">
                <p>
                    Display = <g:formatBoolean boolean="${noticeInstance.display}"/>
                    <br/>
                    From Date = <g:formatDate format="yyyy-MM-dd" date="${noticeInstance.fromDate}"/>
                    <br/>
                    Thru Date = <g:formatDate format="yyyy-MM-dd" date="${noticeInstance.thruDate}"/>
                    <br/>
                    <g:link controller="notice" action="show" id="${noticeInstance.id}">Edit Notice</g:link>
                </p>
            </sec:access>
        </div> <!-- /.alert -->
    </g:each>
</g:if>