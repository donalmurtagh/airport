<head>
    <style type="text/css">
    input.button {
        display: block;
        margin-top: 10px;
    }

    .text-input {
        width: 90%;
    }

    .button.small {
        padding: 4px;
    }

    .center {
        text-align: center;
    }

    .collapse-border {
        border-right: none;
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

                    <g:if test="${users}">
                        <table>
                            <thead>
                            <tr>
                                <th>Email</th>
                                <th colspan="2">Account Status</th>
                                <th>Registered On</th>
                                <th class="collapse-border"></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>

                            <g:each in="${users}" status="i" var="user">

                                <tr class="${i % 2 == 0 ? 'even' : ''}">
                                    <td>${user.username}</td>
                                    <td class="collapse-border">${user.enabled ? 'Enabled' : 'Disabled'}</td>
                                    <td>
                                        <g:if test="${user.enabled}">
                                            <g:link action="toggleEnabled" id="${user.id}" class="button small"
                                                    title="Disable this account">Change</g:link>
                                        </g:if>
                                        <g:else>
                                            <g:link action="toggleEnabled" id="${user.id}" class="button small"
                                                    title="Enable this account">Change</g:link>
                                        </g:else>
                                    </td>
                                    <td><g:formatDate date="${user.dateCreated}"/></td>
                                    <td class="center collapse-border">
                                        <g:link action="resendInvite" id="${user.id}" class="button small"
                                                title="Send another invitation email to this user"
                                                onclick="return confirm('${g.message(code: 'user.resendInvite.confirm')}');">Resend Invite</g:link>
                                    </td>
                                    <td class="center">
                                        <g:link action="delete" id="${user.id}" class="button small"
                                                title="Permanently delete this user"
                                                onclick="return confirm('${g.message(code: 'user.delete.confirm')}');">Delete</g:link>
                                    </td>
                                </tr>
                            </g:each>

                            </tbody>
                        </table>
                    </g:if>
                    <g:else>
                        There are currently no registered users. Use the Add User form to register users.
                    </g:else>
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