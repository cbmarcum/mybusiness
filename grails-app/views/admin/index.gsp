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
    <div class="page-header">
        <h1><g:message code="administration.index.title"/></h1>
    </div> <!-- /.page-header -->
    <p>
        <b>The Administrative Home Page</b> is the place with instructions and links
    to view and edit main Product database table and helper tables such as
    Product Feature, Product Feature Category, and Product Feature Application.
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

</div> <!-- /.container -->
</body>
</html>
