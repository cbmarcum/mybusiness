<meta property="og:url" content="${grailsApplication.config.grails.serverURL}${request.forwardURI}"/>
<meta property="og:title" content="${product.name}"/>
<meta property="og:site_name" content="Code Builders, LLC Site"/>
<meta property="og:description" content="${product?.shortDescription}" />
<g:if test="${product?.photos}">
    <meta property="og:image" content="${product.photos[0].photo.url("large")}"/>
    <meta property="og:image:width" content="400"/>
    <meta property="og:image:height" content="400"/>
</g:if>
<g:else>
    <meta property="og:image" content="${grailsApplication.config.grails.serverURL}/assets/fb-mb-image-1200x630.png"/>
    <meta property="og:image:width" content="1200"/>
    <meta property="og:image:height" content="630"/>
</g:else>
<meta property="fb:app_id" content="${grailsApplication.config.fb.appid}"/>
<meta property="fb:admins" content="${grailsApplication.config.fb.admins}"/>
<meta property="og:type" content="product"/>
