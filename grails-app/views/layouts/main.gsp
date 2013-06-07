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
                    <div id="logo" class="five columns alpha">
                        <h1>
                            <g:link uri='/'>
                                <span class="titleRegular">The</span>
                                <span class="titleBold">Airport</span>
                                <span class="titleRegular">Marketing</span>
                                <span class="titleBold">Toolbox</span>
                            </g:link>
                        </h1>
                    </div>
                    <section id="navigation" class="seven columns omega">
                        <nav id="nav-wrap">
                            <ul id="topnav" class="sf-menu">
                                <li><g:link uri='/'>Home</g:link></li>
                                <li><a href="about.html">About Us</a>
                                    <ul>
                                        <li><a href="index2.html">Homepage Flexslider</a></li>
                                        <li><a href="element.html">Elements</a></li>
                                        <li><a href="columns.html">Columns</a></li>
                                        <li><a href="pricing.html">Pricing Tables</a></li>
                                        <li><a href="sidebar_right.html">Page Sidebar Right</a></li>
                                        <li><a href="sidebar_left.html">Page Sidebar Left</a></li>
                                        <li><a href="single.html">Blog Details</a></li>
                                        <li><a href="#">Dropdown</a>
                                            <ul>
                                                <li><a href="#">Only</a></li>
                                                <li><a href="#">Example of</a></li>
                                                <li><a href="#">The Dropdown</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>

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
                    <div class="line-op"></div>
                    <r:img uri="/images/content/pagetitle-img2.png" class="scale-with-grid"/>
                    <div id="pagetitle-container">
                        <h1 class="pagetitle">Welcome</h1>
                        <span class="pagedesc">Your tagline goes here</span>
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
                    <footer id="footer">Copyright &copy;2013. All Rights Reserved.</footer>
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
