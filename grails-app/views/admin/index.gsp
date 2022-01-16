<!--
Copyright 2016 Code Builders, LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main"/>

    <title><g:message code="administration.index.title"/></title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h1><g:message code="administration.index.title"/></h1>
            </div> <!-- /.page-header -->
            <p>
                Welcome to the <b>Administrative Home Page</b>. Here you will find instructions and links
            to view and edit the main database and helper tables such as as well as other tasks.
            </p>

            <h2>Product Section</h2>
            <g:render template="/admin/productSection"/>

            <h2>Common Section</h2>
            <g:render template="/admin/noticeSection"/>

            <g:render template="/admin/rssFeedSection"/>

            <g:render template="/admin/userSection"/>

            <g:render template="/admin/blogSection"/>

            <g:render template="/admin/htmlEditorSection"/>

        </div> <%-- /.col --%>
    </div> <%-- /.row --%>
</div> <!-- /.container -->
</body>
</html>
