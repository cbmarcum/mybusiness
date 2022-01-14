<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>

    <title><g:message code="title.tag.contact"/></title>
    <meta name="description" content="${g.message(code: 'meta.description.contact')}"/>

    <style>
    /* STACKED CAPTCHA WHITE */
    .capbox {
        width: 100%;
        max-width: 240px;
        background: #FFFFFF;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        display: inline-block;
        padding: 3px 10px 5px 15px;
    }
    .capbox-inner {
        font: normal 15px arial, sans-serif;
        color: #000000;
        background: transparent;
        margin: 3px auto 0px auto;
        padding: 0px 10px 5px 3px;
        border-radius: 4px;
    }
    #captchaDiv {
        font: normal 35px Impact, Charcoal, arial, sans-serif;
        font-style: italic;
        color: #000000;
        background: transparent;
        user-select: none;
        padding: 4px;
        border-radius: 4px;
    }
    #captchaInput {
        margin: 3px 0px 1px 0px;
        width: 98%;
    }
    </style>
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


    <div class="col-md-12">
        <p>
            For further information please feel free to call.
        </p>

        <br/>
    </div>

</div> <!-- /.row -->
    <g:render template="/contact/contactForm" />

</div> <!-- /.container -->
</body>
</html>
