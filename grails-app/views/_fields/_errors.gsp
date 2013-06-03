<g:if test="${invalid}">
    <ul class="help-inline">
        <g:each in="${errors}" var="error">
            <li>${error}</li>
        </g:each>
    </ul>
</g:if>