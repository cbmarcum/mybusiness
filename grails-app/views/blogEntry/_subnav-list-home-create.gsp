<div class="nav" role="navigation">
    <ul>
        <li><g:link class="list" controller="blog" action="list">
            <g:message code="blog.list" default="blog.list"></g:message>
        </g:link></li>

        <sec:access expression="hasRole('ROLE_ADMIN')">

            <li><g:link class="home" controller="admin" action="index">
                <g:message code="default.admin.home.label"/>
            </g:link></li>

            <li><g:link class="create" controller="blog" action="createEntry"><g:message
                    code="blog.createEntry"
                    default="blog.createEntry"></g:message></g:link></li>

        </sec:access>
    </ul>
</div>
