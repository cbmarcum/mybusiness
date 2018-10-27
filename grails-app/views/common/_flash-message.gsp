<g:if test="${flash.message}">
    <div class="alert alert-info alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <i class="fas fa-info-circle fa-2x"></i>&nbsp;${flash.message}</div>
</g:if>