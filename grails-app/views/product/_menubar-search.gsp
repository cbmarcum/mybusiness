<g:form controller="product" action="index" params="${params}" role="search"
        style="padding-top: 15px;">
    <div class="input-group">
        <g:textField name="q" value="${params.keyword}" class="form-control" placeholder="Search"
            style="height:36px"/>
        <span class="input-group-btn">
            <button class="btn btn-primary" type="submit"><i class="fas fa-search fa-lg"></i></button>
        </span>
    </div>
</g:form>
