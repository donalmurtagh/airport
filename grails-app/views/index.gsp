<head>
</head>

<body>
<!-- MAIN CONTENT -->
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <div class="four columns alpha">

                <sec:ifLoggedIn>
                    <h4 class="titleUppercase">Change Password</h4>

                    <div class="form-container">

                        <g:form controller="user" action="changePassword" method="post">
                            <fieldset>
                                <f:field bean="changePassword" property="currentPassword" label="Current Password">
                                    <g:passwordField name="${property}" class="text-input"/>
                                </f:field>

                                <f:field bean="changePassword" property="newPassword" label="New Password">
                                    <g:passwordField name="${property}" class="text-input"/>
                                </f:field>

                                <f:field bean="changePassword" property="newPasswordConfirm" label="Confirm New Password">
                                    <g:passwordField name="${property}" class="text-input"/>
                                </f:field>
                            </fieldset>

                            <input type="submit" name="submit" class="submit button" value="Change"/>

                        </g:form>
                    </div>
                </sec:ifLoggedIn>

                <sec:ifNotLoggedIn>
                    <h4 class="titleUppercase">Login</h4>

                    <p class="bottom-spacer">To continue past this point, you must login.</p>

                    <div class="form-container">

                        <form action="${request.contextPath}/j_spring_security_check" method="post">
                            <fieldset>
                                <f:field bean="login" property="j_username" label="Email Address" required="true"/>
                                <f:field bean="login" property="j_password" label="Password">
                                    <g:passwordField name="${property}" class="text-input"/>
                                </f:field>
                                <f:field bean="login" property="_spring_security_remember_me" label="Remember Me"/>
                            </fieldset>

                            <input type="submit" name="submit" class="submit button" value="Login"/>
                        </form>
                    </div>

                    <p class="double-top-spacer">Forgot password? <g:link controller="user" action="beginPasswordReset">Recover it here</g:link>.</p>
                </sec:ifNotLoggedIn>
            </div>

            <div class="eight columns omega"></div>

            <div class="clear"></div><!-- clear float -->
        </section>
    </div>
</div>
<!-- END MAIN CONTENT -->
</body>