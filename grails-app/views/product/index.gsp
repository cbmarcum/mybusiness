<!doctype html>
<html lang="en">
    <head>
        <meta name="layout" content="main" />

        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}"/>
        <g:set var="entityCategory" value="${productCategory}"/>

        <title><g:message code="default.list.label" args="[entityName]"/></title>
        <meta name="description" content="${g.message(code: 'meta.description.home')}"/>

        <!-- facebook open graph meta tags -->
        <g:render template="fb-meta-tags-index"/>

    </head>

    <body>
    <sec:access expression="hasRole('ROLE_ADMIN')">
        <g:render template="body-admin"/>
    </sec:access>
    <sec:noAccess expression="hasRole('ROLE_ADMIN')">
        <g:render template="body-public"/>
    </sec:noAccess>
</body>
</html>
