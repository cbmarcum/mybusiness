<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>

    <title><g:message code="title.tag.contact"/></title>
    <meta name="description" content="${g.message(code: 'meta.description.contact')}"/>

</head>

<body>

<div class="container">
    <div class="page-header">
        <h1>Contact Us</h1>
    </div> <!-- /.page-header -->

    <div class="row">
    <div class="col-md-6">
    <address>
        <div class="center-block">
            <h2>My Business</h2>
            My Address<br>
            My City, ST 12345<br>
            <strong>Phone:</strong>  (123) 456-7890<br>
        </div><!-- /.center-block -->
    </address>
    </div> <!-- /.col -->
    <div class="col-md-6">

        <p>
            <b>Store Hours</b><br/>
            Monday-Friday: 8 AM to 8 PM<br>
            Saturday: 9 AM to Noon<br/>
            Closed Sunday<br/>
        </p>
    </div> <!-- /.col -->

</div> <!-- /.row -->
    <div>
        <p>
            For further information please feel free to call.
        </p>

        <br/>
    </div>

    <g:render template="/contact/contactForm" />

</div> <!-- /.container -->
</body>
</html>
