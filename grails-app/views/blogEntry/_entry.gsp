<div id="entry${entry.id}" class="blogEntry">
    <g:render template="/blogEntry/entryTitle" model="[entry: entry]"></g:render>
    <div class="entryBody">
        ${entry.body.encodeAsRaw()}
    </div>
    <g:render template="/blogEntry/entryDetails" model="[entry:entry]"></g:render>
</div>
