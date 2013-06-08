<head>

</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <h4 class="titleUppercase">Change Password</h4>

            <p class="bottom-spacer">Enter your new password below, twice.</p>

            <div class="four columns alpha">
                <div class="form-container">

                    <g:form action="resetPassword">
                        <g:hiddenField name='t' value='${token}'/>
                        <fieldset>
                            <f:field bean="command" property="password" label="New Password">
                                <g:passwordField name="${property}" class="text-input"/>
                            </f:field>

                            <f:field bean="command" property="password2" label="Confirm New Password">
                                <g:passwordField name="${property}" class="text-input"/>
                            </f:field>
                        </fieldset>
                        <input type="submit" name="submit" class="submit button" value="Submit" style="margin-top: 0"/>
                    </g:form>
                </div>
            </div>
        </section>
    </div>
</div>

</body>
