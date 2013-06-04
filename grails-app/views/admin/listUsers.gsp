<head>
    <style type="text/css">
        input.button {
            display: block;
            margin-top: 10px;
        }

        .text-input {
            width: 90%;
        }
    </style>
</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">
            <section class="positionright nine columns omega" id="content">
                <div class="padcontent">
                    <h2>Current Users</h2>

                </div>
            </section>

            <aside class="positionleft three columns alpha" id="sidebar">

                <div class="widget-container">
                    <h2 class="widget-title"><span class="title-bg">Add User</span></h2>
                    <g:form action="addUser" method="post">
                        <fieldset>
                            <f:field bean="newUser" property="username" label="Email Address"/>
                        </fieldset>

                        <input type="submit" class="button" id="new-user" value="Add"/><br class="clear"/>
                    </g:form>
                </div>
            </aside>
            <div class="clear"></div>
        </section>
    </div>
</div>
</body>