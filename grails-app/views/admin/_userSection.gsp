<h3>Users and Roles</h3>

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
