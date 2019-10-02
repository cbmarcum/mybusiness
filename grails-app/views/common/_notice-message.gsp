<g:if test="${noticeList}">
    <g:each in="${noticeList}" status="i" var="noticeInstance">
        <div class="alert alert-info alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span></button>
                ${noticeInstance.longDescription.encodeAsRaw()}
        </div> <!-- /.alert -->
    </g:each>
</g:if>