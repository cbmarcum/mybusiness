<g:if test="${noticeList}">
    <g:each in="${noticeList}" status="i" var="noticeInstance">
        <div class="alert alert-info alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">X</span></button>
            <p>
                ${noticeInstance.longDescription.encodeAsRaw()}
            </p>
        </div> <!-- /.alert -->
    </g:each>
</g:if>