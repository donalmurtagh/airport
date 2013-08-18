<head>
    <style type="text/css">
        .item-title {
            font-weight: normal;
        }

        .toggle_container ul li {
            padding-bottom: 0;
        }

        .toggle_container ol ul {
            margin-left: 15px;
        }

        .toggle_container ol {
            margin-top: 5px;
            margin-bottom: 5px;
        }

    </style>
</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <h2>${toolbox.name}</h2>

            <g:each in="${toolbox.toolboxSections}" var="section">
                <h3 class="double-top-spacer">${section.heading.encodeAsHTML()}</h3>

                <p class="bottom-spacer">${section.text.encodeAsHTML()}</p>

                <div class="toggle">

                    <g:each in="${section.toolboxItems}" var="item">

                        <h2 class="trigger"><span>${item.heading.encodeAsHTML()}</span></h2>

                        <div class="toggle_container">
                            <div class="block">
                                ${item.text}
                                <g:checkBox style="float: left;" name="item-${item.id}"
                                            value="${item.id in completedToolboxItemIds}"
                                            onchange="${remoteFunction(action: 'toggleItem', id: item.id)}"/>
                            </div>
                        </div>
                    </g:each>
                </div>
            </g:each>
        </section>
    </div>
</div>
</body>