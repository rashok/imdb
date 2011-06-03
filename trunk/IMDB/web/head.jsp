<%@page import="com.imdb.beans.User"%>
<%@page import="java.util.List"%>

<%! User user; %>
<%! List<String> genres, languages; %>

<% user = (User) session.getAttribute("user"); %>
<% genres = (List<String>) session.getAttribute("genres"); %>
<% languages = (List<String>) session.getAttribute("languages"); %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE HTML>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
    
    <meta http-equiv="content-type" content="text/html;charset=utf-8"> 
<head>

    <!-- Page title -->
    <title>IMDB</title>

    <!-- Meta tags -->
    <meta name="description" content="IMDB - Internet Movies DataBase" />
    <meta name="author" content="Valter Henrique, Vitor Villela" />

    <!-- Set the viewport to the device's width -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- Page icons -->
    <link rel="shortcut icon" href="favicon.ico" />
    <link rel="apple-touch-icon" href="apple-touch-icon.png" />

    <!-- Stylesheets -->
    <link rel="stylesheet" href="styles/styles.css" />

    <!-- Other scripts files located at the bottom of the page -->
    <script src="scripts/modernizr-1.6.min.js"></script>
    
</head>

