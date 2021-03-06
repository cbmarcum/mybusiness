<!-- Hero Section-->
<section class="hero">
    <div class="container">
        <g:render template="/common/notice-message"/>
        <%-- No Breadcrumbs --%>

                <!-- Hero Content-->
        <div class="hero-content pb-5 text-center">
            <h1 class="hero-heading"><g:message code="default.list.label" args="[entityName]"/></h1>
            <div class="row">
                <div class="col-xl-8 offset-xl-2"><p class="lead text-muted">${productCategory}
                        <g:if test="${params.keyword}"> - Search for ${params.keyword} </g:if>
                    </p>
                </div>
            </div>
        </div>
    </div>
</section>
<main>
    <div class="container">
        <div class="row">
            <!-- Grid -->
            <div class="products-grid col-12 sidebar-none">
                <header class="product-grid-header">
                    <div class="text-muted">
                        Listings may only show the primary variations. Search results will show all matching variations. Products with variations can be changed on their detail pages.
                    </div>
                </header>
                <div class="row">

                    <g:each in="${productList}" status="i" var="product">

                        <g:if test="${product.photos[0]}">
                            <g:set var="large" value="${product.photos[0].photo.getCloudFile("large")}"/>
                        </g:if>
                        <g:else>
                            <g:set var="large" value="${noImage}"/>
                        </g:else>

                                <!-- product-->
                        <div class="col-xl-3 col-lg-4 col-sm-6">
                            <div class="product">
                                <%-- test for an image --%>

                                <div class="product-image">
                                    <cb:imageByClass clazz="img-fluid" large="${large}" alt="something" />
                                    <div class="product-hover-overlay"><g:link action="detail" id="${product.id}" class="product-hover-overlay-link"></g:link>
                                        <div class="product-hover-overlay-buttons"><g:link action="detail" id="${product.id}" class="btn btn-dark btn-buy"><i class="fa-search fa"></i><span class="btn-buy-label ml-2">View</span></g:link>
                                            </div>
                                        </div>
                                    </div>


                                <div class="py-2">
                                    <p class="text-muted text-sm mb-1">${product.name}</p>
                                    <h3 class="h6 text-uppercase mb-1">
                                        <sec:access expression="hasRole('ROLE_ADMIN')">
                                            <g:link action="show" id="${product.id}" class="text-dark">${product.number} </g:link><br/>
                                            </sec:access>
                                            <sec:noAccess expression="hasRole('ROLE_ADMIN')">
                                            <g:link action="detail" id="${product.id}" class="text-dark">${product.number} </g:link><br/>
                                            </sec:noAccess>
                                        </h3>
                                        <span class="text-muted"><g:formatNumber number="${product.listPrice}" type="currency" currencyCode="USD"/></span>
                                </div>
                            </div>
                        </div>
                        <!-- /product-->
                    </g:each>



                </div> <%-- ./row --%>

                <g:if test="${productCount > params.max}">
                <cb:bsPaginate total="${productCount}" params="${params}"/>
            </g:if>

        </div>
        <!-- / Grid End-->
    </div>
</div><!-- /container -->
</main>
