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
            to view and edit the main Product database table and helper tables such as
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
                Product images use Photos listed in the next section. Multiples photos and be linked to a Product.<br/>
            </p>

            <p>
                There is an HTML editor used for product descriptions.  The editor can also be used to
                display images on the page.
                For information on the editor see: <a href="#editor">HTML Editor</a>
            </p>

            <p>
                <g:link controller="product" action="index">Product List</g:link>
            </p>

            <p><b>Photo</b> is a table for photos with links to the photo files on the filesystem or CDN.<br/>
                images must be in PNG, and JPG format.
                Uploaded images should be larger than 400px W x 400px H.
                Images can be edited and converted using many image editing programs such as Adobe Photoshop or the free
                open-source alternative GIMP available at <g:link base="www.gimp.org">www.gimp.org</g:link><br/>
                Uploaded images are copied and scaled to thumb (50px W x 50px H), small (150px W x 150px H),
                and large (400px W x 400px H).<br/>
                It is best if images are proportional to this (1X Width x 1X Height) before uploading
                to eliminate cropping of image.
                Current image file size is limited to 1MB. This can be adjusted in the code.
            </p>

            <p>
                <g:link controller="photo" action="index">Photo List</g:link>
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

            <h2>Product Feature Application</h2>

            <p>
                <b>Product Feature Application</b> is a join table that ties a Product Feature to
            a Product.  This is used to build the list of selectable options by feature
            category on a product's detail page.
            </p>

            <p>
                <g:link controller="productFeatureAppl" action="index">Product Feature Application List</g:link>
            </p>

            <h2>Product Category</h2>

            <p>
                <b>Product Category</b> is a table for product categories. Products may
            belong to many categories. Examples would be "Dog Supplies", "Collars",
            and "New Products".  
            Product Categories have a hierarchy that allows sub-categories to be more specific.
            When selecting Product Categories using the Product create or edit forms you only 
            need to select the most specific ones. For example selecting Collars will 
            automatically add Dog Supplies. If this also a New Product you will need to 
            select this also as New Products is not a ancestor of Collars.
            </p>

            <p>
                <g:link controller="productCategory" action="index">Product Category List</g:link>
            </p>

            <h2>Good Identification</h2>

            <p>
                <b>Good Identification</b> is a table to hold various codes and id numbers
            a product may have. Product has a "number" field that is for the primary
            number used to identify it and is used in product listings This could be any 
            id you want but is usually the SKU. Good Identification
            is for additional codes such a SKU, UPC, etc.
            </p>

            <p>
                <g:link controller="goodIdentification" action="index">Good Identification List</g:link>
            </p>

            <h2>Import Sheet</h2>

            <p>
                <b>Import Sheet</b> is a table to hold product spreadsheets used to add and update
            product listings as well as comments generated during processing.
            </p>

            <p>
                <g:link controller="importSheet" action="index">Import Sheet List</g:link>
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

            <h2>Blog Entry</h2>

            <p>
                <b>Blog Entry</b> is a table for storing blog entries by users.<br/>
                Blogs have multiple associated Tags which can be useful for classifying topics
                of the blog entry.  Once Tags are added to the entry using a comma separated list
                they will appear on the right side of the page as links.<br/>
                Entry lists can also be viewed using an <b>Author/YYYY/MM/DD</b> format such as:<br/>
                <b>http://codebuilders.net/blog/Carl/2019/01/11</b> <br/>
                You can also be less specific by omitting parameters from the end going backwards like: <br/>
                <b>http://codebuilders.net/blog/Carl/2019</b> <br/>
                For information on the editor see: <a href="#editor">HTML Editor</a>
            </p>

            <p>
                <g:link controller="blog" action="list">Blog</g:link>
            </p>

            <h2 id="editor">HTML Editor</h2>

            <p>
                <b>HTML Editor</b> is a WYSIWYG (what you see is what you get) HTML editor used in places where HTML data
            needs to be entered and stored in the database like in product descriptions and blog entries. In the editor
            you can switch between source code and WYSIWYG modes. In WYSIWYG mode there are a number of
            formatting tools similar to what you would find in a word processor.
            </p>

            <p>
                <b>Images</b> can be added by uploading to them the server or by providing a link to an external
            image hosted elsewhere like Amazon s3 storage.<br/>
                To make images responsive and scale properly on smaller devices:
            <ul>
                <li>use images of the correct size when viewing on desktop computer without scaling.
                For an image, that means about 700 pixels wide maximum.</li>
                <li>Add the CSS style class <b>img-responsive</b> to image.</li>
            </ul>

            <p>
                Caution: Images uploaded using the editor are stored on the server's filesystem and could
                lost between backups in the unlikely event of a catastrophic server failure.
            </p>
        </p>

        </div> <%-- /.col --%>
    </div> <%-- /.row --%>
</div> <!-- /.container -->
</body>
</html>
