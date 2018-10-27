<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="list" controller="blog" action="list"><g:message code="blog.list.link"
                                                                            default="Blog Home"></g:message></g:link>
        </li>
        <g:if test="${entry.id}">
            <li>
                <g:link class="show" controller="blog" action="showEntry"
                        params="[title: entry.title, author: entry.author]">
                    <g:message code="blog.show" default="blog.show"></g:message>
                </g:link>
            </li>
        </g:if>
    </ul>
</div>