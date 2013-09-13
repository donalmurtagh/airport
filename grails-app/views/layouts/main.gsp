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
                    <div id="logo" class="two columns alpha">
                        <h1>
                            <g:link uri='/'>
                                <r:img uri="/images/logo.jpg" class="scale-with-grid"/>
                            </g:link>
                        </h1>
                    </div>
                    <section id="navigation" class="ten columns omega">
                        <nav id="nav-wrap">
                            <ul id="topnav" class="sf-menu">
                                <li><g:link uri='/'>Home</g:link></li>

                                <sec:ifLoggedIn>
                                    <li><a href="javascript:void(0)">Pilot Project</a></li>
                                    <li><a href="javascript:void(0)">Documents</a></li>
                                    <li><a href="javascript:void(0)">Media</a></li>

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
                                            <li><g:link uri="/googleAdwords">Google Adwords</g:link></li>
                                            <li><a href="javascript:void(0)">Other Digital</a></li>
                                            <li><a href="javascript:void(0)">Gorilla</a></li>
                                            <li><a href="javascript:void(0)">Best Practice</a></li>
                                            <li><a href="javascript:void(0)">TV/Radio</a></li>
                                            <li><a href="javascript:void(0)">Events</a></li>
                                        </ul>
                                    </li>

                                    <li><g:link uri="/otherCustomers">Other Customers</g:link></li>
                                </sec:ifLoggedIn>

                                <sec:ifAllGranted roles="ROLE_ADMIN">
                                    <li><g:link controller="user" action="listUsers">Admin</g:link></li>
                                </sec:ifAllGranted>

                                <sec:ifLoggedIn>
                                    <li><g:link controller="logout">Logout</g:link></li>
                                </sec:ifLoggedIn>
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
                        <h1 class="pagetitle">Welcome to The Airport Marketing Toolbox</h1>
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
                    <footer id="footer">&copy; 2013 All Rights Reserved</footer>

                    <div id="toTop"></div>
                </div>
            </div>
        </div>
        <!-- END FOOTER -->
    </div>
</div>

%{--Show flash messages in the top message bar--}%
<g:render template="/common/notificationBar"/>

<r:layoutResources/>
</body>
</html>
