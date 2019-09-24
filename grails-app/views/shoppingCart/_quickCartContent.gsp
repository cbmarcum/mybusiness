<sc:each>

    <%-- each in --%>
    <!-- cart item-->
    <div class="navbar-cart-product">
        <div class="d-flex align-items-center">
            <g:set var="image" value="${it['item'].photos[0].photo.getCloudFile("thumb")}"/>
            <g:link controller='product' action='detail' id="${it['item'].id}">
                <cb:image image="${image}" class="img-fluid navbar-cart-product-image"/>
            </g:link>
            <div class="w-100">
                <div class="pl-3">
                    <g:link controller='product' action='detail' id="${it['item'].id}" class="navbar-cart-product-link">
                        ${it['item'].name}
                    </g:link>
                    <small class="d-block text-muted">Quantity: ${it['qty']}</small>
                    <strong class="d-block text-sm">
                        <g:formatNumber number="${it['item'].listPrice}" type="currency" currencyCode="USD"/>
                    </strong>
                </div>
            </div>
        </div>
    </div>

</sc:each>