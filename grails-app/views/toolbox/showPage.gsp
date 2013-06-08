<head>
    <style type="text/css">
    #prev {
        margin-right: 20px;
    }
    </style>
</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <h2>Toolbox Page ${page}</h2>

            <p class="double-bottom-spacer">Select the items that have been completed.</p>

            <g:each in="${toolboxItems}" var="item">

                <div class="bottom-spacer">
                    <label for="item-${item.id}">
                        <g:checkBox name="item-${item.id}" value="${item.id in completedToolboxItemIds}"
                                    onchange="${remoteFunction(action: 'toggleItem', id: item.id)}"/>
                        ${item.text}
                    </label>
                </div>
            </g:each>

            <div class="double-top-spacer">
                <g:if test="${page > 1}">
                    <g:link action="showPage" params="[page: page - 1]" elementId="prev"
                            class="button">&#9668; Prev Page</g:link>
                </g:if>

                <g:if test="${page < lastToolboxPage}">
                    <g:link action="showPage" params="[page: page + 1]" class="button">Next Page &#9658;</g:link>
                </g:if>
            </div>
        </section>
    </div>
</div>
</body>