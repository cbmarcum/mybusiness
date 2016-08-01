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

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>

    <title>About MyBusiness</title>

</head>
<body>
    <div class="container">

        <div class="row">

<!-- left-detail -->
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2 class="panel-title">Application Status</h2>
                    </div> <!-- /.panel-heading -->
                    <div class="panel-body">
                        <ul>
                            <li>App version: <g:meta name="app.version"/></li>
                            <li>Grails version: <g:meta name="app.grails.version"/></li>
                            <li>Groovy version: ${GroovySystem.getVersion()}</li>
                            <li>JVM version: ${System.getProperty('java.version')}</li>
                            <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                            <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                            <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                            <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                        </ul>
                    </div> <!-- /.panel-body -->
                </div> <!-- /.panel -->

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h2 class="panel-title">Installed Plugins</h2>
                    </div> <!-- /.panel-heading -->
                    <div class="panel-body">
                        <ul>
                            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                                <li>${plugin.name} - ${plugin.version}</li>
                                </g:each>
                        </ul>
                    </div> <!-- /.panel-body -->
                </div> <!-- /.panel -->

            </div> <!-- /.col -->        
        <!-- end left-detail -->

<!-- right-detail -->
            <div class="col-md-9">
                <div class="page-header">
                    <h1>Welcome to Grails</h1>
                </div> <!-- /.page-header -->
                <p>Congratulations, you have successfully started your first Grails application! At the moment
                    this is the default page, feel free to modify it to either redirect to a controller or display whatever
                    content you may choose. Below is a list of controllers that are currently deployed in this application,
                    click on each to execute its default action:</p>

                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                        </g:each>
                </ul>

            </div> <!-- /.col -->        
        <!-- end right-detail -->




        </div> <!-- ./row -->

    </div> <!-- /.container -->
</body>
</html>
