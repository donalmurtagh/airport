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

            <p class="double-bottom-spacer">
                For more information about each topic, click on the sections below to expand them. Use the checkboxes
                to indicate which items have been completed.
            </p>

            <g:each in="${toolbox.toolboxSections}" var="section">
                <h3>${section.name.encodeAsHTML()}</h3>

                <div class="toggle">

                    <g:each in="${section.toolboxItems}" var="item">

                        <h2 class="trigger"><span>${item.heading.encodeAsHTML()}</span></h2>

                        <div class="toggle_container">
                            <div class="block">
                                <p>
                                    ${item.text}
                                    <g:checkBox name="item-${item.id}" value="${item.id in completedToolboxItemIds}"
                                            onchange="${remoteFunction(action: 'toggleItem', id: item.id)}"/>
                                </p>
                            </div>
                        </div>
                    </g:each>
                </div>
            </g:each>
        </section>
    </div>
</div>
</body>