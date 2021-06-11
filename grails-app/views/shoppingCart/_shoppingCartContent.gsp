<sc:each>
    <tr>
        <td>
            <g:if test="${it['item'].photo}">
                <g:set var="image" value="${it['item'].photo.photo.getCloudFile("thumb")}"/>
            </g:if>
            <g:else>
                <g:set var="image" value="${net.codebuilders.mybusiness.SiteHelper.thumbNoImage}"/>
            </g:else>
            <g:link title="${g.message(code:'viewProduct.label' , args:[it.item.name])}" controller="shop" action="product" id="${it.item.id}" >
                <cb:image image="${image}"/>
            </g:link>
        </td>
        <td>
            <g:link   title="${g.message(code:'viewProduct.label' , args:[it.item.name])}"  controller="shop" action="product" id="${it.item.id}">
                ${it['item']?.name}
            </g:link>  <br/>
            ${it['item']?.shortDescription}<br/>
            <g:formatNumber number="${it?.item?.listPrice}" type="currency"
                            currencyCode="GB"/>
        </td>
        <td>
            <div class="pt-2 pt-sm-0 pl-sm-3 mx-auto mx-sm-0 text-center text-sm-left" style="max-width: 10rem;">
                <div class="form-group mb-2">
                    <g:set var="availableStock" value="${((it?.item?.inventoryCount)-(session?.cartCounter[it['item']?.id]?:0))}"/>

                    <g:select  class="form-control" from="${0..(it?.item?.inventoryCount>10?10:it?.item?.inventoryCount)}" value="${session?.cartCounter[it['item']?.id]}"
                               name="total${it?.item?.id}" id="qty_${it['item']?.id}"  onchange="updateCart(${it.item.id})" />
                    <g:message code="availableStock.label" args="[availableStock]"/>
                </div>
            </div>
        </td>
        <td>
            <g:formatNumber number="${it.itemTotal}" type="currency"
                            currencyCode="GB"/>
        </td>
    </tr>
</sc:each>
<g:if test="${checkedOutItems}">
    <tr>
        <td><h2>Checked out items</h2></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>

    </tr>
    <g:each in="${checkedOutItems}" var="item">
        <tr>
            <td>
                <bi:img size="small" bean="${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])}"/>
            </td>
            <td>
                <g:link controller="shop" action="product" id="${it.item.id}">
                    ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])?.name}
                </g:link>
                <br/>
                ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])?.shortDescription}<br/>
                ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])?.listPrice}&nbsp;-&nbsp;
            </td>
            <td>
                ${it['qty']}
            </td>
            <td>
                <g:formatNumber number="${it.itemTotal}" type="currency"  currencyCode="${mybusiness.listeners.SiteConfigListener.currencyCode?.toString()}"/>
            </td>
        </tr>
    </g:each>

</g:if>

<script>
    function removeFromCart(id) {
        var url="${createLink(controller:'shoppingCart', action: "removeAllFromCart")}/?id="+id
        postAction(url)
    }
    function updateCart(id) {
        var qty = $('#qty_'+id).val()
        var url="${createLink(controller:'shoppingCart', action: "updateCart")}/?id="+id+"&qty="+qty
        postAction(url)

    }
    function postAction(url) {
        $.ajax({
            type: 'POST',
            url: url,
            success: function(data){
                location.reload();
            },
            error: function (xhr, textStatus, error) {
                $("#failSegment").html("${g.message(code:'formError.label')}"+xhr.status).addClass('has-warning').delay(10).fadeIn('normal', function () {
                    $(this).delay(1500);
                }).hide();
                //console.log(xhr.responseText+''+JSON.stringify(error)+' '+JSON.stringify(textStatus));
            }
        });
    }
</script>