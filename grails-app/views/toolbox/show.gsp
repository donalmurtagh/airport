<head>
    <style type="text/css">
        .toggle_container label {
            color: #676767;
            font-weight: bold;
        }

        .label-holder {
            margin-left: 20px;
            margin-bottom: 10px;
        }

        #charges {
            width: 50%;
            margin: 10px auto 0 auto;
        }

        #charges td {
            text-align: center;
        }
    </style>

    <title>${toolbox.name}</title>
    <r:require module="toolbox"/>
</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <h2>${toolbox.name}</h2>

            <g:each in="${toolbox.toolboxSections}" var="section">
                <h3 class="double-top-spacer">${section.heading.encodeAsHTML()}</h3>

                ${section.text}

                <div class="toggle top-spacer">

                    <g:each in="${section.toolboxItems}" var="item">

                        <h2 class="trigger"><span>${item.heading.encodeAsHTML()}</span></h2>

                        <div class="toggle_container">
                            <div class="block">${item.text}</div>

                            <g:if test="${section.showCheckboxes}">
                                <div class="label-holder">
                                    <label for="item-${item.id}">
                                        <g:checkBox name="item-${item.id}"
                                                    value="${item.id in completedToolboxItemIds}"
                                                    onchange="${remoteFunction(action: 'toggleItem', id: item.id)}"/>
                                        Completed
                                    </label>
                                </div>
                            </g:if>
                        </div>
                    </g:each>
                </div>
            </g:each>
        </section>
    </div>
</div>
</body>