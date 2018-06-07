<g:form controller="blog" action="search" params="${params}" role="search"
        style="padding-top: 15px;">
    <div class="input-group">
        <g:textField name="q" value="${params.keyword}" class="form-control" placeholder="Search"/>
        <span class="input-group-btn">
            <button class="btn btn-primary" type="submit"><i class="fab fa-searchengin"></i></button>
        </span>
    </div>
</g:form>
