<head>
    <style type="text/css">
    .item-title {
        font-weight: normal;
    }
    </style>
</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <h2>${toolbox.name}</h2>

            <p class="double-bottom-spacer">Select the items that have been completed.</p>

            <g:each in="${toolbox.toolboxSections}" var="section">
                <h3>${section.name.encodeAsHTML()}</h3>

                <g:each in="${section.toolboxItems}" var="item">

                    <div class="bottom-spacer">
                        <h4 class="item-title">${item.heading.encodeAsHTML()}</h4>

                        <label for="item-${item.id}">

                            <g:checkBox name="item-${item.id}" value="${item.id in completedToolboxItemIds}"
                                        onchange="${remoteFunction(action: 'toggleItem', id: item.id)}"/>
                            ${item.text}
                        </label>
                    </div>
                </g:each>
            </g:each>
        </section>
    </div>
</div>
</body>