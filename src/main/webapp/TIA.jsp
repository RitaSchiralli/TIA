<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
    	<title>Turismo in Puglia</title>
    	
    	<!-- Stili uniba -->
    	<!-- <link href="http://fonts.googleapis.com/css?family=Tinos:400,700,400italic" rel="stylesheet" type="text/css" />
    	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,700,600" rel="stylesheet" type="text/css" />
    	<base href="http://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento-di-informatica" />
    	<meta property="og:site_name" content="Universita' degli Studi di Bari Aldo Moro" />
    	<meta property="og:type" content="article" />
    	<meta property="og:locale" content="it_IT" />
    	<meta property="og:image" content="http://www.uniba.it/logounibafacebook.jpg" />
    	<meta property="fb:app_id" content="1512166522334131" />
        <meta property="og:title" content="Dipartimento di Informatica" />
    
        <meta property="og:description" content="" />
        <script type="text/javascript" src="http://www.uniba.it/portal_javascripts/Uniba4%20Skin/jquery-cachekey0431.js"></script>
        <script type="text/javascript" src="http://www.uniba.it/portal_javascripts/Uniba4%20Skin/resourceanythingslider.min-cachekey4417.js"></script>
        <script type="text/javascript" src="https://static.ict.uniba.it/uniba.widgets/rubrica/uniba.rubrica.js"></script>
        <script type="text/javascript" src="++resource++uniba.xdvskin.js/slidehpdipartimento.min.js"></script>
        <style type="text/css" media="all">
        	@import url(http://www.uniba.it/portal_css/Uniba4%20Skin/authoring-cachekey9262.css);
        </style>
        <style type="text/css" media="all">
        	@import url(http://www.uniba.it/portal_css/Uniba4%20Skin/resourcetinymce.stylesheetstinymce-cachekey9559.css);
        </style>
        <style type="text/css">
        	@import url(http://www.uniba.it/portal_css/Uniba4%20Skin/resourcesmart_link-cachekey7088.css);
        </style>
        <style type="text/css" media="all">
        	@import url(http://www.uniba.it/portal_css/Uniba4%20Skin/zone-cachekey4656.css);
        </style>
        <link rel="kss-base-url" href="http://www.uniba.it/ricerca/dipartimenti/informatica/dipartimento-di-informatica/" />
        <link rel="stylesheet" type="text/css" media="screen" href="http://www.uniba.it/portal_css/Uniba4%20Skin/base-cachekey5251.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="http://www.uniba.it/portal_css/Uniba4%20Skin/portlets-cachekey7141.css" />
        <link rel="stylesheet" type="text/css" href="http://www.uniba.it/portal_css/Uniba4%20Skin/resourcetcp_stylesheetscookiepolicy-cachekey4562.css" />
        <link rel="stylesheet" type="text/css" media="all" href="http://www.uniba.it/portal_css/Uniba4%20Skin/resourceContentWellPortlets.stylesContentWellPortlets-cachekey3064.css" />
        <link rel="stylesheet" type="text/css" href="http://www.uniba.it/portal_css/Uniba4%20Skin/resourcecollective.carouselcarousel-cachekey0007.css" />
        <link rel="stylesheet" data-rel="kinetic-stylesheet" type="text/kss" href="http://www.uniba.it/portal_kss/Uniba4%20Skin/at-cachekey1943.kss" />-->
        <link rel="shortcut icon" type="image/x-icon" href="http://www.uniba.it/favicon.ico" />
        <!-- <link rel="apple-touch-icon" href="http://www.uniba.it/touch_icon.png" />
        <link rel="home" href="http://www.uniba.it/ricerca/dipartimenti/lelia" title="Pagina principale" />
        <link rel="contents" href="http://www.uniba.it/ricerca/dipartimenti/lelia/sitemap" title="Mappa del sito" />
        <link rel="search" href="http://www.uniba.it/ricerca/dipartimenti/lelia/search_form" title="Cerca nel sito" />
        <link rel="stylesheet" type="text/css" href="++resource++uniba.xdvskin.stylesheets/slidehpdipartimento.min.css" /> -->
      <!-- fine Stili uniba -->
    	
        <!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> -->
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script type="text/javascript" src="src/AutocompleteObject.js"></script>
        
        
        <!-- <script type="text/javascript" src="src/CanaliInput.js"></script> -->
        <script type="text/javascript" src="src/CanaliInput2.js"></script>
        
        
        <link rel="stylesheet" href="styles/dialog.css" type="text/css" />
        <link rel="stylesheet" href="styles/token-input.css" type="text/css" />
        <link rel="stylesheet" href="http://yellowstone.cs.ucla.edu/wis/css/normalize.min.css" />
        <!-- <link rel="stylesheet" href="http://yellowstone.cs.ucla.edu/wis/css/main.css" /> -->
        <script type="text/javascript">
            $(document).ready(function () {
                //new CanaliInput("inputDiv", "autocomplete", "translate", "query", "results",  "settings", 2, 200);
                new CanaliInput2("inputDiv", "w2v", "translate", "query", "results",  "settings", 2, 200,"map");
            });
            function toggle(i) {
                if (i===1) {
                    $("#toggle1").toggle("slide");
                    $("#toggle2").toggle("slide");
                } else {
                    $("#toggle3").toggle("slide");
                    $("#toggle4").toggle("slide");                    
                }
            }
            function openDialog(dialog_name, donttoggle) {
                var dialog = $("#" + dialog_name)
                var maskHeight = $(document).height();
                var maskWidth = $(window).width();
                var dialogTop = 100;
                var dialogLeft = (maskWidth / 2) - (dialog.width() / 2);
                $('#dialog-overlay').css({height: maskHeight, width: maskWidth}).show();
                var dialogHeigth = maskHeight - 2 * dialogTop;
                dialog.css({top: dialogTop, left: dialogLeft, height: dialogHeigth}).show();
                if (!donttoggle)
                    toggle();
            }
            function openEntityDialog(text, uri) {
                $('#alert').hide();
                $('#entityToDescribe').setEntity(text, uri);
                openDialog("describe_entity", true);
            }
            function openAlertDialog(msg) {
                var dialog = $("#alert");
                dialog.html(msg);
                openDialog("alert", true);
            }
        </script>
        <style>
            .toggle {
                width: 200px;
                height: 100px;
                position: absolute;                
                top: 0px;
                right: 0px;                
            }
            
            #map {
            height: 400px;
        	width: 100%;
       		}

        </style>
        <script>
            (function (i, s, o, g, r, a, m) {
                i['GoogleAnalyticsObject'] = r;
                i[r] = i[r] || function () {
                    (i[r].q = i[r].q || []).push(arguments)
                }, i[r].l = 1 * new Date();
                a = s.createElement(o),
                        m = s.getElementsByTagName(o)[0];
                a.async = 1;
                a.src = g;
                m.parentNode.insertBefore(a, m)
            })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

            ga('create', 'UA-68452078-1', 'auto');
            ga('send', 'pageview');
        </script>           
    </head>
        
	<!-- inizio pezzo di uniba -->
    <div class="cell width-full position-0" style="background-color:#004071">
    	<a id="portal-logo" accesskey="1" title="Torna in homepage" href="http://www.uniba.it/ricerca/dipartimenti/informatica">
    	<img src="http://www.uniba.it/ricerca/dipartimenti/informatica/unibalogo_locale/image" alt="LOGOTIPO di Dipartimento di Informatica" title="LOGOTIPO di Dipartimento di Informatica" height="96" width="748" /></a>
    	<!-- <a id="idzona" class="hiddenStructure" data-globalnav="False" title="informatica">informatica</a> -->
    	<!-- <div id="headerstampa">
    		<img src="http://www.uniba.it/ricerca/dipartimenti/informatica/++resource++uniba.xdvskin.images/unibalogo-print.jpg" alt="Logotipo di UniBa" />
    		<span id="zonaTitle">Dipartimento di Informatica</span>
    		<div style="clear: both;">
    		</div> -->
    	</div>
        <!--<div id="viewlet-cookiepolicy" style="display:none">
        	<div id="viewlet-cookiepolicy-wrapper">
        		<h3 id="tlspu_cookiepolicy_title">Questo sito utilizza Cookie</h3>
        		<p id="tlspu_cookiepolicy_message">Questo sito utilizza solo cookie tecnici, propri e di terze parti, per il corretto funzionamento delle pagine web.
        			<a href="http://www.uniba.it/informativaprivacy">Informativa privacy</a>
        		</p>
				<form id="tlspu_cookiepolicy_form">
        			<input type="checkbox" id="tlspu_cookiepolicy_agreed" value="confirmed" />
        			<label for="tlspu_cookiepolicy_agreed">Ho letto e compreso questo messaggio</label>
        			<button disabled="disabled" id="tlspu_cookiepolicy_button" value="hide">Nascondi questo messaggio</button>
        		</form>
        	</div>
        </div>
	</div> -->
	<!-- fine pezzo di uniba -->

    <body style="margin-bottom: 200px;">    
        <div class="header-container">
            <header class="wrapper clearfix">
                <table border="0" cellspacing="20px" cellpadding="10px">
                    <tr>
                        <!-- <td><img src="http://yellowstone.cs.ucla.edu/wis/img/logo1.jpg" width="200px" alt="ScAI"> </td> -->
                        <td>    <div class="title-container">
                                <!-- <h1 class="title">CANaLI: a Context-Aware controlled Natural Language Interface</h1> -->
                                <h1 class="title">Chiedi informazioni sul turismo in Puglia!</h1>
                            </div>
                        </td>
                    </tr>
                </table>
                <!-- <div class="nav-container">
                    <nav>
                        <ul>
                            <li><a href="about.jsp">About</a></li>
                            <li><a href="index.jsp">Demo</a></li>
                            <li><a href="qald.jsp">Experiments</a></li>
                        </ul>
                    </nav>
                </div> -->
            </header>
        </div> 
        <div style="margin-top:50px; font-size:14px; line-height: 16px; position:relative;">
        	        
            <div id="inputDiv">Input div</div>
        
            <!-- <div style="margin: 0 auto; width:800px; font-size: 0.8em; text-align: right">Currently using <% out.print(System.getProperty("kb.name"));%></div>
            <div id="toggle1" class="toggle"><img src="open-settings.png" onclick="toggle(1)" width="50px" align="right" title="Open settings"/></div>
            <div id="toggle2" class="toggle" style="display: none; width: 300px">
                <div style="float: left; height: 200px"><img src="close-settings.png" onclick="toggle(1)" width="50px" title="Close settings"/></div>
                <div id="settings" style="width: 250px; height: 200px; border: 1px; border-color: #8496ba; margin-left: 60px">
                </div>
            </div> -->
        </div>
        <!-- <div style="margin-top:100px; font-size:14px; line-height: 16px; position:relative;">
            <div id="toggle3" class="toggle"><img src="open-settings.png" onclick="toggle(2)" width="50px" align="right" title="Open settings"/></div>
            <div id="toggle4" class="toggle" style="display: none; width: 300px">
                <div style="float: left; height: 200px"><img src="close-settings.png" onclick="toggle(2)" width="50px" title="Close settings"/></div>
                <div id="settings" style="width: 250px; height: 200px; border: 1px; border-color: #8496ba; margin-left: 60px">
                    <a href="javascript:void(0);" onclick='openDialog("describe_entity")'>Describe entity</a><br />
                    <br />
                    <a href="javascript:void(0);" onclick='openDialog("describe_property")'>Describe property</a><br />
                </div>
            </div>
        </div> -->
        <div style="margin-left: auto; margin-right: auto; margin-top: 100px; font-size: 14px; line-height: 16px" id="results">
        </div>
        <!-- <div id="dialog-overlay"></div>
        <div id="describe_entity" class="dialog-box">
            Select an entity <input id="entityToDescribe" />
            <br />
            <div id="entity_describe_results"></div>
        </div>
        <div id="describe_property" class="dialog-box">
            Select a property <input id="propertyToDescribe" />
            <br />
            <div id="property_describe_results"> </div>
        </div> -->
        <div id="alert" class="dialog-box">
            This is an alert!
        </div>
        <div id="map"></div><!-- RITA -->
    </body>
</html>