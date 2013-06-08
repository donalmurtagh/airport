<head>

</head>

<body>
<div id="outermain">
    <div class="container">
        <section id="maincontent" class="twelve columns">

            <h4 class="titleUppercase">Reset Password</h4>
            <p class="bottom-spacer">To reset your password, enter your email address below. We will send you an email with instructions for completing the process.</p>

            <div class="four columns alpha">
                <div class="form-container">

                    <g:form controller="user" action="forgotPassword">
                        <fieldset>
                            <f:field bean="forgotPassword" property="username" label="Email address"/>
                        </fieldset>

                        <input type="submit" name="submit" class="submit button" value="Submit" style="margin-top: 0"/>

                    </g:form>
                </div>
            </div>
        </section>
    </div>
</div>

</body>
