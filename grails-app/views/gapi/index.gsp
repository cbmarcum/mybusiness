<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
</head>

<body>
<!-- content tag="nav" was here -->


<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
    </div>
</div>


<div class="container">

    <div class="row">
        <div class="col-md-6 p-x-md">

            <div class="page-header">
                <h2>Google Map</h2>
            </div> <!-- .page-header -->
        <!-- 4:3 aspect ratio -->
            <div class="embed-responsive embed-responsive-4by3">
                <iframe class="embed-responsive-item" width="640" height="480" frameborder="0" scrolling="no"
                        marginheight="0" marginwidth="0"
                        src="${grailsApplication.config.mybusiness.map.src}"></iframe>
            </div>


        </div> <!-- /.col -->

        <div class="col-md-6 p-x-md">
            <div class="page-header">
                <h2>Google Reviews</h2>
            </div> <!-- .page-header -->
        <g:render template="/gapi/googleReviews"/>

        </div> <!-- /.col -->

    </div> <!-- /.row -->

</div> <!-- .container -->

</body>
</html>
