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
            to view and edit main Product database table and helper tables such as
            Product Feature, Product Feature Category, and Product Feature Application as will as other tasks.
            </p>

            <h2>Product Workflow</h2>

            <p>
                <b>Workflow when adding a new product and features should be:</b> <br/>
                Product Feature Category > Product Feature > Product >
                The remain steps have no particular order:
                Standard Feature Application, Product Feature Application,
                Product Category, and Good Identification.
            </p>

            <h2>Product</h2>

            <p><b>Product</b> is a table for items displayed in the application product pages.</p>

            <p>
                Product images must be in GIF, PNG, and JPG format.  JPG (JPEG) files can be
                used if the server the MyBusiness application is running on has Oracle Java 7
                and not OpenJDK version of Java. Images can be edited and converted
                to using many image editing programs such as Adobe Photoshop or the free
                open-source alternative GIMP available at <g:link base="www.gimp.org">www.gimp.org</g:link><br/>
                Images are copied and scaled to small (150px W x 150px H) and large (300px W x 300px H).<br/>
                It is best if images are proportional to this (1X Width x 1X Height) before uploading
                to eliminate cropping of image.<br/>
                Current image file size is limited to 15,000,000 bytes. This can be adjusted in the code.
            </p>

            <p>
                <g:link controller="product" action="index">Product List</g:link>
            </p>

            <h2>Product Feature Category</h2>

            <p>
                <b>Product Feature Category</b> is a table for grouping product features like
            colors, and sizes to be used as selectable option
            categories on product detail pages.
            </p>

            <p>
                <g:link controller="productFeatureCategory" action="index">Product Feature Category List</g:link>
            </p>

            <h2>Product Feature</h2>

            <p>
                <b>Product Feature</b> is a table for features and options available with Products.
            This could be colors like blue, red and sizes like small, large, etc.
            </p>

            <p>
                <g:link controller="productFeature" action="index">Product Feature List</g:link>
            </p>

            <h2>Standard Feature Application</h2>

            <p>
                <b>Standard Feature Application</b> is a join table that ties a Product Feature to
            a Product.  This builds the list of standard features on a product's detail page.
            </p>

            <p>
                <g:link controller="standardFeatureAppl" action="index">Standard Feature Application List</g:link>
            </p>

            <h2>Product Feature Application</h2>

            <p>
                <b>Product Feature Application</b> is a join table that ties a Product Feature to
            a Product.  The purpose of this is to enable applying a cost and a p/n code to a feature by
            what product it is applied to.  This builds the list of selectable options by feature
            category on a product's detail page.
            </p>

            <p>
                <g:link controller="productFeatureAppl" action="index">Product Feature Application List</g:link>
            </p>

            <h2>Product Category</h2>

            <p>
                <b>Product Category</b> is a table for product categories. Product may
            belong to many categories. Examples would be "Pistols", "Holsters",
            and "Ammunition".
            </p>

            <p>
                <g:link controller="productCategory" action="index">Product Category List</g:link>
            </p>

            <h2>Good Identification</h2>

            <p>
                <b>Good Identification</b> is a table to hold various codes and id numbers
            a product may have. Product has a "number" field that is for the primary
            number used to identify it and is used in product listing. Good Identification
            is for additional codes such a SKU, UPC, etc.
            </p>

            <p>
                <g:link controller="goodIdentification" action="index">Good Identification List</g:link>
            </p>

            <h2>Specials</h2>

            <p>
                <b>Special</b> is a table for date based product specials or sales to display on
            the Specials page. <br/>
                Specials can be added and removed from the page through the administration page.
                Specials have a begin and end date as well as an enable/disable toggle.
            </p>

            <p>
                <g:link controller="special" action="index">Special List</g:link>
            </p>

            <h2>Notices</h2>

            <p>
                <b>Notice</b> is a table for date based notices used on the home page. <br/>
                Important notices can be added and removed from the top of the home page through
                the administration page. Notices have a begin and end date as well as an
                enable/disable toggle. A popular use of this feature is for holiday schedules.
            </p>

            <p>
                <g:link controller="notice" action="index">Notice List</g:link>
            </p>

            <h2>Payments</h2>

            <p>
                Payments should be confirmed through PayPal's website.
            </p>

            <p>
                <g:link controller="payment" action="index">Payment List</g:link>
            </p>

            <h2>RSS Feeds</h2>

            <p>
                <b>RssFeed</b> is a table for RSS feeds used on the home page. <br/>
                RSS feeds can be added and removed from the feed section of the home page through
                the administration page. Feeds have a enable/disable toggle and a max number of items to display.
                A popular use of this feature is for news feeds. More than one feed source can be displayed at a time.
            </p>

            <p>
                <g:link controller="rssFeed" action="index">RSS Feed List</g:link>
            </p>

            <h2>Users and Roles</h2>

            <p>
                Users are assigned roles that determine what privileges they have within the application.
                <br/>
                Please do not forget the 'admin' user password.
                <br/>
                Once a password is created, it is stored in the database encrypted.
                The encrypted value is what is shown in the list and form fields.
                The encrypted value cannot be used to login, only the original password.
                If the password field is changed, then that password is re-encrypted.
                <br/>

            <p>
                A user with administrator role creates a new user on user create form.<br/>
                An email is sent to user with a password and a link to the site.<br/>
                User can then logon.<br/>
                User can also choose "lost password" and will be emailed a link (that includes a token identifying them to the application) back to site reset password page.
            </p>

            <p>
                <g:link controller="secUser" action="index">User List</g:link><br/>
                <g:link controller="secRole" action="index">Role List</g:link><br/>
                <g:link controller="secUserSecRole" action="index">User Role List</g:link>
            </p>
        </div> <%-- /.col --%>
    </div> <%-- /.row --%>
</div> <!-- /.container -->
</body>
</html>
