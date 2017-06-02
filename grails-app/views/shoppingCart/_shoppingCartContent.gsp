<sc:each>
    <tr>
        <td>
            <g:set var="image" value="${it['item'].photos[0].photo.getCloudFile("thumb")}"/>
            <cb:image image="${image}"/>
        </td>
        <td>
            ${it['item']?.name}<br/>
            ${it['item']?.shortDescription}<br/>
            ${it['item']?.listPrice}&nbsp;-&nbsp;
            <g:remoteLink action="add2"
                          params="${[id: it['item'].id, class: it['item'].class, version: it['item'].version]}"
                          update="shoppingCartContent"
                          onComplete="Effect.Pulsate('shoppingCartContent', {pulses: 1, duration: 1.0});">
                Add
            </g:remoteLink>&nbsp;-&nbsp;
            <g:remoteLink action="remove"
                          params="${[id: it['item'].id, class: it['item'].class, version: it['item'].version]}"
                          update="shoppingCartContent"
                          onComplete="Effect.Pulsate('shoppingCartContent', {pulses: 1, duration: 1.0});">
                Remove
            </g:remoteLink>&nbsp;-&nbsp;
            <g:remoteLink action="removeAll"
                          params="${[id: it['item'].id, class: it['item'].class, version: it['item'].version]}"
                          update="shoppingCartContent"
                          onComplete="Effect.Pulsate('shoppingCartContent', {pulses: 1, duration: 1.0});">
                Remove All
            </g:remoteLink>

        </td>
        <td>
            ${it['qty']}
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
                ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])?.name}<br/>
                ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])?.shortDescription}<br/>
                ${com.metasieve.shoppingcart.Shoppable.findByShoppingItem(it['item'])?.listPrice}&nbsp;-&nbsp;
            </td>
            <td>
                ${it['qty']}
            </td>
        </tr>
    </g:each>
</g:if>