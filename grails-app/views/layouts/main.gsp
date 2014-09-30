<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="The Airport Marketing Toolbox"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">

    <r:require module="base"/>
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<div id="bodychild">
    <div id="outercontainer">
        <!-- HEADER -->
        <div id="outerheader">
            <div class="container">
                <header id="top" class="twelve columns">
                    <div id="logo" class="four columns alpha">
                        <span style="float: left;">
                            <g:link uri='/'>
                                <r:img uri="/images/logo.jpg" class="scale-with-grid"/>
                            </g:link>
                        </span>

                        <span id="buttons">
                            <r:img uri="/images/eu/baltic-bird.jpg" class="scale-with-grid"/>
                            <r:img uri="/images/eu/flag.jpg" class="scale-with-grid"/>
                        </span>
                    </div>
                    <section id="navigation" class="eight columns omega">
                        <nav id="nav-wrap">
                            <ul id="topnav" class="sf-menu">

                                <sec:ifLoggedIn>
                                    <li><g:link uri='/'>Home</g:link></li>
                                    <li><a href="javascript:void(0)">Marketing</a>
                                        <ul>
                                            <li><a href="javascript:void(0)">Airlines</a>
                                                <ul>
                                                    <g:each var="toolbox" in="${toolboxes}">
                                                        <li>
                                                            <g:link controller="toolbox" action="show"
                                                                    id="${toolbox.id}">${toolbox.name.encodeAsHTML()}</g:link>
                                                        </li>
                                                    </g:each>
                                                </ul>
                                            </li>

                                            <li><a href="javascript:void(0)">Passengers</a>
                                                <ul>
                                                    <li><g:link uri="/digital">Digital</g:link></li>
                                                    <li><g:link uri="/otherPassengers">Other</g:link></li>
                                                </ul>
                                            </li>

                                            <li><g:link uri="/otherCustomers">Other Customers</g:link></li>
                                        </ul>
                                    </li>

                                    <li><g:link uri="/media">Media Page</g:link></li>
                                    <li><g:link uri="/pilot">Pilot Project</g:link></li>
                                    <li><g:link uri="/reports">Output Reports</g:link></li>
                                    <li><g:link controller="logout">Logout</g:link></li>
                                </sec:ifLoggedIn>

                                <sec:ifAllGranted roles="ROLE_ADMIN">
                                    <li><g:link controller="user" action="listUsers">Admin</g:link></li>
                                </sec:ifAllGranted>
                            </ul><!-- topnav -->
                        </nav><!-- nav -->
                        <div class="clear"></div>
                    </section>

                    <div class="clear"></div>
                </header>
            </div>
        </div>
        <!-- END HEADER -->

        <!-- AFTER HEADER -->
        <div id="outerafterheader">
            <div class="container">
                <div id="afterheader" class="twelve columns pattern2">
                    <div id="pagetitle-container">
                        <h1 class="pagetitle">${pageProperty(name: 'title') ?: 'Welcome'}</h1>
                        <h2 class="pagetitle">The Airport Marketing Toolbox</h2>
                    </div>
                </div>
            </div>
        </div>
        <!-- END AFTER HEADER -->

        <g:layoutBody/>

        <!-- FOOTER -->
        <div id="outerfooter">
            <div class="container">
                <div id="footercontainer" class="twelve columns">
                    <footer id="footer">&copy; ${new Date()[Calendar.YEAR]} All Rights Reserved</footer>

                    <div id="toTop"></div>
                </div>
            </div>
        </div>
        <!-- END FOOTER -->
    </div>
</div>

%{--Show flash messages in the top message bar--}%
<g:render template="/common/notificationBar"/>

<sec:ifLoggedIn>
    <div id="contact">
        <a href="mailto:info@ic-aviation.com" title="Click here to contact us">
            <span>
                <r:img uri="/images/icons/icon-s3.png" class="scale-with-grid"/>
            </span>
            <span id="contact-label">
                Need<br/>Assistance?
            </span>
        </a>
    </div>
</sec:ifLoggedIn>

<r:layoutResources/>
</body>
</html>
