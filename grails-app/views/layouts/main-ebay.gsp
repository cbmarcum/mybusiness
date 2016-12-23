<!DOCTYPE html>
<html lang="en">
    <g:render template="/common/mainbs-head" />
<body role="document">

<!-- Fixed navbar -->
    <nav class="navbar navbar-default"><!-- was class="navbar navbar-inverse navbar-fixed-top" -->
        <div class="container">
            <g:render template="/common/menubar-brand" />
            <div id="navbar" class="navbar-collapse collapse">
               
                <g:render template="/common/menubar-main" />
                <g:render template="/common/menubar-ebay-search" />

            </div><!--/.nav-collapse -->
        </div>
    </nav>




  <!-- insert page body -->
    <g:layoutBody/>

    <div class="container">
        <g:render template="/common/demo-notice" />
    </div> <!-- /.container -->

    <div class="footer">
        <div class="container">
            <g:render template="/common/footer" />
        </div> <!-- /.container -->
    </div> <!-- /.footer -->
    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
    <g:javascript library="application"/>
<r:layoutResources/>

</body>
</html>