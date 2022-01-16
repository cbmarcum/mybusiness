<h3>Product Workflow</h3>

<p>
    <b>Workflow when adding a new product and features should be:</b> <br/>
    Product Feature Category > Product Feature > Product >
    The remain steps have no particular order:
    Standard Feature Application, Product Feature Application,
    Product Category, and Good Identification.
</p>

<h3>Product</h3>

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

<h3>Product Feature Category</h3>

<p>
    <b>Product Feature Category</b> is a table for grouping product features like
colors, and sizes to be used as selectable option
categories on product detail pages.
</p>

<p>
    <g:link controller="productFeatureCategory" action="index">Product Feature Category List</g:link>
</p>

<h3>Product Feature</h3>

<p>
    <b>Product Feature</b> is a table for features and options available with Products.
This could be colors like blue, red and sizes like small, large, etc.
</p>

<p>
    <g:link controller="productFeature" action="index">Product Feature List</g:link>
</p>

<h3>Product Feature Application</h3>

<p>
    <b>Product Feature Application</b> is a join table that ties a Product Feature to
a Product.  This is used to build the list of selectable options by feature
category on a product's detail page.
</p>

<p>
    <g:link controller="productFeatureAppl" action="index">Product Feature Application List</g:link>
</p>

<h3>Product Category</h3>

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

<h3>Good Identification</h3>

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

<h3>Import Sheet</h3>

<p>
    <b>Import Sheet</b> is a table to hold product spreadsheets used to add and update
product listings as well as comments generated during processing.
</p>

<p>
    <g:link controller="importSheet" action="index">Import Sheet List</g:link>
</p>

<h3>Specials</h3>

<p>
    <b>Special</b> is a table for date based product specials or sales to display on
the Specials page. <br/>
    Specials can be added and removed from the page through the administration page.
    Specials have a begin and end date as well as an enable/disable toggle.
</p>

<p>
    <g:link controller="special" action="index">Special List</g:link>
</p>

<h3>Payments</h3>

<p>
    Payments should be confirmed through PayPal's website.
</p>

<p>
    <g:link controller="payment" action="index">Payment List</g:link>
</p>
