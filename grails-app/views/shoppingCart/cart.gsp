<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>
        <g:message code="shoppingCart.label"/>
    </title>
</head>
<body>
<div class="container">
<div class="page-header">
    <g:render template="/common/flash-message"/>
    <div class="row">
        <div style="display: table; margin: auto;">
            <span class="step step_complete"> <a href="#" class="check-bc">
                <g:message code="cart.label"/>
            </a>
                <span class="step_line step_complete"> </span>
                <span class="step_line backline"> </span> </span>
            <span class="step_thankyou step_complete">
                <g:message code="checkout.label"/>
            </span>
            <span class="step_thankyou check-bc step_complete">
                <g:message code="thankyou.label"/>
            </span>
        </div>
    </div>
</div>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div id="failSegment"></div>
<g:if test="${session.cart && session.cart.size()>0}">

    <div class="container">
        <div class="col-md-6 col-md-offset-2">
            <table class="table-striped ">
                <tbody id="shoppingCartContent">
                <g:render template="shoppingCartContent"/>
                </tbody>
            </table>
        </div>
        <div class="col-sm-4 col-md-3">
            <div class="row removable" style="">
                <div class="panel panel-info" id="userPanelInfo">
                    <div class="panel-body min-padding">
                        <div class="card-text text-left">
                            <sec:ifLoggedIn>
                                <btn class="checkOut btn btn-lg btn-primary form-control checkout-button">
                                    <g:message code="gotoCheckout.label"/>
                                </btn>
                            </sec:ifLoggedIn>
                            <sec:ifNotLoggedIn>
                                <btn class="guestCheckOut btn btn-lg btn-primary form-control checkout-button">
                                    <g:message code="gotoCheckout.label"/>
                                </btn>
                            </sec:ifNotLoggedIn>
                            <btn class="shopOn btn btn-lg btn-default form-control checkout-button">
                                <g:message code="continueShopping.label"/>
                            </btn>
                            <div class="h4 padTotalPrice">
                                <span class="h3 cart total">
                                    <g:message code="payment.total.label"/>
                                </span>
                                <span class="cart total-value h3">
                                    <strong>
                                        <sc:currentTotal>
                                            <g:hiddenField name="finalTotal" value="${finalTotal}"/>
                                            <g:formatNumber number="${finalTotal}"
                                                            type="currency"
                                                            currencyCode="${mybusiness.listeners.SiteConfigListener.currencyCode?.toString()}"/>
                                        </sc:currentTotal>
                                    </strong>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="modal fade" id="cartmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" data-dismiss="modal">
            <div class="modal-content"  >
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <btn class="authCheckOut btn btn-lg btn-primary form-control checkout-button">
                        <g:message code="continueSignin.label"/>
                    </btn>
                    <btn class="checkOut btn btn-lg btn-default form-control checkout-button">
                        <g:message code="continueGuest.label"/>
                    </btn>
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div>


    <!--
     <tr>
                    <td colspan="4">
                        <h3 class="h6 pt-4 "><span class="badge badge-success mr-2">Note</span>Additional comments</h3>
                        <textarea class="form-control mb-3" id="order-comments" rows="5"></textarea>
                    </td>

                </tr>
    -->
</g:if>
<g:else>
    <p>
        You have an empty shopping cart. Please review items <g:link controller="shop" action="index">for sale</g:link>
    </p>

</g:else>
</div>
<script>
    $('.shopOn').on('click', function() {
        window.location.href="${createLink(controller:'shop', action: "index")}"
    })
    $('.checkOut').on('click', function() {
        window.location.href="${createLink(controller:'shoppingCart', action: "checkout")}"
    })
    $('.authCheckOut').on('click', function() {
        window.location.href="${createLink(controller:'shoppingCart', action: "login")}"
    })

    $('.guestCheckOut').on('click', function() {
        //var editUrl="${createLink(action: 'editName')}";
        //showDialog('"+editUrl+"','','','');
        $('#cartmodal').modal('show');
    })

</script>
</body>
</html>