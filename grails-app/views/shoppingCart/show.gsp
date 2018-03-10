<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Shopping Cart</title>

</head>

<body>

<div class="container">
    <div class="page-header">
        <h1>Shopping Cart</h1>
    </div> <!-- /.page-header -->
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <p>
        Use <b>Add</b> and <b>Remove</b> to change item quantities and <b>Remove All</b>
        to completely remove an item.<br/>
        <b>Checkout with PayPal</b> will continue to PayPal purchase.
    </p>

    <p>
        PayPal checkout will add shipping, insurance, and state sales tax for OH.
        Shipping insurance is calculated at 0.24 per 100.00.
        Ohio State sales tax will apply for Ohio residents only and will vary by county.
    </p>

    <div class="table-responsive">
        <table class="table">

            <thead>
            <tr>
                <td>&nbsp;</td>
                <td>Item to Purchase</td>
                <td>Qty</td>
            </tr>
            </thead>

            <tbody id="shoppingCartContent">
            <g:render template="shoppingCartContent"/>
            </tbody>
        </table>
    </div>
    <br/>

    <p>
        <g:link action="checkOut">
            <img src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" align="left" style="margin-right:7px;">
        </g:link>
    </p>

</div> <!-- /.container -->
</body>
</html>