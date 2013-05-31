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
                    <div id="logo" class="three columns alpha"><h1><a href="index.html"><span class="circle"><img src="images/icons/logo.png" alt=""/></span>
                        <span class="titleBold">Smart</span><span class="titleRegular">Group</span></a></h1></div>
                    <section id="navigation" class="nine columns omega">
                        <nav id="nav-wrap">
                            <ul id="topnav" class="sf-menu">
                                <li><a href="index.html">Home</a></li>
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
                                <li><a href="service.html">Services</a></li>
                                <li><a href="#">Projects</a>
                                    <ul>
                                        <li><a href="portfolio2.html">Portfolio 2 Column</a></li>
                                        <li><a href="portfolio3.html">Portfolio 3 Column</a></li>
                                        <li><a href="portfolio4.html">Portfolio 4 Column</a></li>
                                    </ul>
                                </li>
                                <li><a href="blog.html">Blog</a></li>
                                <li class="current"><a href="contact.html">Contact</a></li>
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
                    <img src="images/content/pagetitle-img2.png" alt="" class="scale-with-grid" />
                    <div id="pagetitle-container">
                        <h1 class="pagetitle">Contact</h1>
                        <span class="pagedesc">This can be your tagline or something you want. Lorem ipsum dolor</span>
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
<r:layoutResources/>
</body>
</html>
