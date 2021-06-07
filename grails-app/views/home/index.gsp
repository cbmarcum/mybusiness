
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'home.label', default: 'Welcome')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="mainPage" id="mainPage">
    <g:render template="/common/notice-message" model="[noticeList:noticeList]"/>
    <g:if test="${categories}">
        <div id="failSegment"></div>
        <div class="col-sm-12">
            <div class="col-sm-2">
                <div class="category-listing">
                    <g:set var="catSize" value="${categories?.size()?:0}"/>
                    <g:each in="${categories}" var="category" >
                        <ul class="menu">
                        <g:each in="${category}" var="subCat" status="ji">

                                <li>
                                    <g:link controller="shop" action="category" id="${subCat?.id}">${subCat?.description} </g:link>
                                    <g:if test="${ji+1 < catSize}">/</g:if>
                                </li>
                        </g:each>
                        </ul>
                        </g:each>


                </div>
            </div>
            <div class="col-sm-10">
            </div>
        </div>
    </g:if>

    <div class="container">
    <g:each in="${windowsList}" var="product">
        <div class="col-lg-3 col-md-4 col-sm-12">
            <div class="card product_item">
                <div class="body">
                    <div class="cp_img">
                        <g:set var="photo" value="${product?.photos?.sort{a,b->a?.sequence<=>b?.sequence}[0]}"/>
                        <g:if test="${photo}">
                            <g:set var="large" value="${photo.photo?.getCloudFile("large")}"/>
                            <g:set var="original" value="${photo.photo?.getCloudFile("original")}"/>
                        </g:if>
                        <g:else>
                            <g:set var="large" value="${net.codebuilders.mybusiness.SiteHelper.noImage}"/>
                        </g:else>
                        <div class="pop item ${counter==0 ?'active':''}">
                        <g:if test="${original}">
                            <cb:imageByClass clazz="hidden" large="${original}" alt="${photo?.alt}" title="${photo?.title}"/>
                        </g:if>
                          <cb:imageByClass clazz="img-fluid" large="${large}" alt="${photo?.alt}" title="${photo?.title}"/>
                        </div>
                        <g:set var="currentlyInCart" value="${(session?.cartCounter[product?.id]?:0)}"/>
                        <g:set var="availableStock" value="${((product.inventoryCount)-currentlyInCart)}"/>
                        <div class="hover">
                            <g:if test="${availableStock>=1}">
                                <button onclick="ajaxCall('addToCart', ${product.id},${availableStock})" class="btn btn-primary btn-lg waves-effect"><i class="fa fa-plus"></i></button>
                            </g:if>
                            <button onclick="ajaxCall('checkout', ${product.id},${availableStock})" class="checkOut btn btn-primary btn-lg waves-effect"><i class="fa fa-shopping-cart"></i></button>
                        </div>
                        <div class="row text-center">
                            <g:message code="inCartInStock.label" args="[currentlyInCart,availableStock]"/>
                        </div>
                    </div>
                    <div class="product_details">
                        <h5>
                            <g:link controller="shop" action="product" id="${product.id}">${product.name}</g:link></h5>

                        <!-- $ {product.productCategory.flatHTMLWalkThroughParentsByName.encodeAsRaw() } -->
                        <g:if test="${product.brand}">
                            <p><g:message code="product.brand.label"/>:
                                ${product.brand}</p>
                        </g:if>


                        <p>${product.shortDescription}</p>
                        <ul class="product_price list-unstyled">
                            <!--li class="old_price">£${product?.listPrice}</li -->
                            <li class="new_price">
                            <g:formatNumber number="${product.listPrice}" type="currency"  currencyCode="GB"/>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
    </div>
</div>
<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" data-dismiss="modal">
        <div class="modal-content"  >
            <div class="modal-body">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <img src="" class="imagepreview" style="width: 100%;" >
            </div>
        </div>
    </div>
</div>
<script>
    function ajaxCall(method,id,available) {
        if (available>=1) {
            var url="${createLink(controller:'shoppingCart', action: "add")}/?id="+id
            $.ajax({
                type: 'POST',
                url: url,
                success: function(data){
                    if (method=='addToCart') {
                        location.reload();
                    } else {
                        window.location.href="${createLink(controller:'shoppingCart', action: "checkout")}";
                        e.stopPropagation();
                        e.preventDefault();
                    }
                },
                error: function (xhr, textStatus, error) {
                    $("#failSegment").html("${g.message(code:'formError.label')}"+xhr.status).addClass('has-warning').delay(10).fadeIn('normal', function () {
                        $(this).delay(1500);
                    }).hide();
                }
            });
        } else {
            if (method=='addToCart') {
                location.reload();
            } else {
                window.location.href="${createLink(controller:'shoppingCart', action: "checkout")}";
            }
        }

    }
    $(function() {



    })
    $('.pop').on('click', function() {
        $('.imagepreview').attr('src', $(this).find('img').attr('src'));
        $('#imagemodal').modal('show');
    });
</script>
</body>
</html>