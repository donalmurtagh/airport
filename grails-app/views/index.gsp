<head>
    <meta name="layout" content="main"/>
</head>

<body>
<!-- MAIN CONTENT -->
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">
            <h4 class="titleUppercase">Keep In Touch With Us</h4>

            <p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla facilisi. Phasellus velit lectus, mattis vel accumsan et, semper in massa. Etiam aliquam ligula a nulla commodo tempus. Nunc et faucibus nunc. Phasellus congue consequat magna, et tincidunt ante ullamcorper in.</p>

            <div class="separator small"></div>

            <div class="four columns alpha">
                <r:img uri="/images/icons/icon5.png" class="alignleft"/>
                <span class="titleBold">Available Open:</span><br/>
                Monday - Friday / 8:30am - 17.00pm<br/>
                Saturday / OFF
            </div>

            <div class="four columns">
                <r:img uri="/images/icons/icon6.png" class="alignleft"/>
                <span class="titleBold">Corporate Offices:</span><br/>
                SmartGroup – Office Blvd No. 356-357<br/>
                Farmville Town, LA 12345
            </div>

            <div class="four columns omega">
                <r:img uri="/images/icons/icon7.png" class="alignleft"/>
                <span class="titleBold">Contact Info:</span><br/>
                Telp: +800 123 456<br/>
                Email: <a href="mailto:www.templatesquare.com">www.templatesquare.com</a>
            </div>

            <div class="separator line"></div>

            <div class="four columns alpha">

                <sec:ifLoggedIn>
                    <h4 class="titleUppercase">Change Password</h4>

                    <div class="form-container">

                        <form action="${request.contextPath}/j_spring_security_check" method="post">
                            <fieldset>
                                <f:field bean="login" property="j_username" label="Email Address" required="true"/>
                                <f:field bean="login" property="j_password" label="Password">
                                    <g:passwordField name="${property}" class="text-input"/>
                                </f:field>
                                <f:field bean="login" property="_spring_security_remember_me" label="Remember Me"/>
                            </fieldset>

                            <input type="submit" name="submit" class="submit button" id="submit_btn"
                                   value="Login"/><br class="clear"/>

                        </form>
                    </div>
                </sec:ifLoggedIn>


                <sec:ifNotLoggedIn>
                    <h4 class="titleUppercase">Login</h4>

                    <p>To continue past this point, you must login.</p>

                    <div class="form-container">

                        <form action="${request.contextPath}/j_spring_security_check" method="post">
                            <fieldset>
                                <f:field bean="login" property="j_username" label="Email Address" required="true"/>
                                <f:field bean="login" property="j_password" label="Password">
                                    <g:passwordField name="${property}" class="text-input"/>
                                </f:field>
                                <f:field bean="login" property="_spring_security_remember_me" label="Remember Me"/>
                            </fieldset>

                            <input type="submit" name="submit" class="submit button" id="submit_btn"
                                   value="Login"/><br class="clear"/>

                        </form>
                    </div>
                </sec:ifNotLoggedIn>
            </div>

            <div class="eight columns omega"></div>

            <div class="clear"></div><!-- clear float -->
        </section>
    </div>
</div>
<!-- END MAIN CONTENT -->
</body>