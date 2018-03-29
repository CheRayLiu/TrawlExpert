<%@ page import="java.util.*" %>
<!--
Double Handle Slider Modified from: http://jqueryui.com/slider/#range
-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="insert, some, keywords"> <!--TODO-->
    <meta name="description" content="insert a description"> <!--TODO-->
    <title>TrawlTool</title>
    <link rel="stylesheet" type="text/css" href="style.css">

    <!--JQuery-->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <!-- MUST load JQuery Library before loading this-->
    <script src="script.js"></script>

    <!--Fonts-->
    <!--Open Sans Rg-400/Semi-600/Bd-700-->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">

    <!--Plugins-->
</head>
<body>
    <header>
        <div class="headerWrapper">
            <a href="index.jsp" >TrawlTool</a>
            <span class="nav-bar">
                <a href="about.html">About</a> |
                <a href="index.html" target="_blank">Gitlab/Github Nonfunc!</a>
            </span>
        </div>
    </header>


    <section id="main">
        <section id="formWrapper">
            <section id="pickSciRanks">
                Phylum:
                <select name="pickSciR" id="pickPhylum" size="1" onChange="changedSciR('phylum')"><!--Dynamically Filled--></select>

                Class:
                <select name="pickSciR" id="pickClass" size="1" onChange="changedSciR('class')">
                    <option value="00">Arthropoda</option>
                    <option value="01">Chordata</option>
                    <option value="03">Mollusca</option>
                </select>
                Order:
                <select name="pickSciR" size="1" onChange="alert()"><!--Dynamically Filled--></select>
                Family:
                <select name="pickSciR" size="1"><!--Dynamically Filled--></select>
                Genus:
                <select name="pickSciR" size="1"><!--Dynamically Filled--></select>
                Species:
                <select name="pickSciR" size="1"><!--Dynamically Filled--></select>
            </section>

            <section id="yearIn">
                Year Range:
                <span id="fromtoYear">InnerHtml</span>
                <form>
                    <div id="slider-range"></div>
                </form>
            </section>

            <section id="outputIn">
                <%--Map and Histogram must have the same name for default checkbox to function correctly--%>
                <input type="radio" name="pickOutput" value="map"> Map
                <input type="radio" name="pickOutput" value="histogram" checked> Histogram
                <button type="button" onclick="">Load</button>
                <button type="button" onclick="">View Individual Records</button> <!--To records.html-->
            </section>
        </section>


        <section id="outputWrapper">
            <section id="outputDetails">Stuff like population count, entries found, etc. go here</section>
            <section id="outputBox">Map, Histogram Box. Histogram selected by default but we might want to have a loading screen instead.
                <div id="console">~~~ PSEUDO-CONSOLE ~~~<br></div>
            </section>
        </section>
    </section>
    <%--<footer>--%>
        <%--Footer--%>
    <%--</footer>--%>
</body>
</html>

    <%--Notes--%>
    <%--http://www.color-hex.com/color/002856--%>
    <%--http://www.color-hex.com/color-palette/58262--%>

