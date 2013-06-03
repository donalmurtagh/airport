<div class="control-group ${invalid ? 'error' : ''}">
    <label class="control-label" for="${property}">${required ? "$label <span class='required'>*</span>" : label}</label>
    <div class="controls">
        <%= widget %>

        <g:render template="/_fields/errors"/>
    </div>
</div>