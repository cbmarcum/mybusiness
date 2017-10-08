<g:form controller="product" action="index" params="${params}" class="navbar-form" role="search">
    <div class="input-group">
        <g:textField name="search" value="${params.keyword}" class="form-control" placeholder="Search"/>
        <span class="input-group-btn">
            <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
        </span>
    </div>
</g:form>
