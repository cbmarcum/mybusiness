<g:if test="${noticeList}">
    <g:each in="${noticeList}" status="i" var="noticeInstance">
        <div class="alert alert-info alert-dismissible d-flex align-items-center fade show" role="alert">
                ${noticeInstance.longDescription.encodeAsRaw()}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div> <!-- /.alert -->
    </g:each>
</g:if>