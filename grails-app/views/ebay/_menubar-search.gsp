
    <g:form controller="ebay" action="index"  params="${params}" role="search">
        <div class="input-group">
            <g:textField name="q" value="${params.keywords}" class="form-control" placeholder="Search" 
                style="height:36px"/>
            <span class="input-group-btn">
                <button class="btn btn-primary" type="submit"><i class="fas fa-search fa-lg"></i></button>
            </span>
        </div>
    </g:form>
