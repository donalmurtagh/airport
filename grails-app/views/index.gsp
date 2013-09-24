<head>
    <style type="text/css">
        div.eight.columns ol, div.eight.columns p {
            margin-bottom: 10px;
        }
    </style>
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
                                <f:field bean="login" property="j_username" label="Email Address" required="true" />
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

            <div class="eight columns omega">
                <h1>Welcome to IC Aviation’s Airport Marketing Toolbox</h1>

                <p>
                    This marketing tool is truly unique in the industry. Please log in to enter Europe’s only website
                    that gives a comprehensive, step by step marketing guide on how to market airports towards their
                    various customer groups. Review detailed suggestions from IC Aviation’s team of highly experienced
                    consultants about:
                </p>

                <ol>
                    <li>
                        How to market your airport towards airlines - understand what airlines are looking for in
                        today’s competitive market place and learn how to make your airport stand out.
                    </li>
                    <li>
                        Learn how to market your airport towards your passengers. Get on board with the latest digital
                        media and look at using traditional marketing strategies more effectively. Build a new marketing
                        plan that will revolutionise the image of your airport to new and existing passengers.
                    </li>
                    <li>
                        Get thinking about other customers who you need to impress: politicians, owners, local residents,
                        etc. Learn about novel marketing strategies that can bring together these diverse customer groups
                        so that the airport has full support from everyone involved.
                    </li>
                </ol>

                <p>
                    Plus, find plenty of other useful industry information about how your airport can improve every
                    aspect of its marketing strategy.
                </p>

                <sec:ifNotLoggedIn>
                    <p>
                        Please <a href="mailto:info@ic-aviation.com"><strong>contact us</strong></a> for a personalised login to
                        IC Aviation’s Airport Marketing Toolbox.
                    </p>
                </sec:ifNotLoggedIn>
            </div>

            <div class="clear"></div><!-- clear float -->
        </section>
    </div>
</div>
<!-- END MAIN CONTENT -->
</body>