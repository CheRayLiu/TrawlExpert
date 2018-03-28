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
        <a href="index.html" >TrawlTool</a>
    </header>

    <span id="menu-bar">
        <a href="index.html">About</a> |
        <a href="index.html" target="_blank">Gitlab/Github</a>
    </span>

    <section id="formWrapper">
        <section id="pickSciRanks">
            <%--<form method="POST" action="doBioLookup.do"> <!--.do extension is not necessary. This field is the servlet's url in web.xml-->--%>
            <form>
                Phylum:
                <%--<select name="pickPhy" size="1" onChange="this.form.submit()">--%>
                <select name="pickSciR" id="pickPhylum" size="1" onChange="changedSciR('phylum')">

                </select>

                Class:
                <select name="pickSciR" id="class" size="1" onChange="changedSciR('class')">
                    <option>SomeClass</option>
                </select>

                Order:
                <select name="order" size="1" onChange="alert()">
                    <option value="00">Arthropoda</option>
                    <option value="01">Chordata</option>
                    <option value="03">Mollusca</option>
                </select>

                Family:
                <select name="family" size="1">
                </select>

                Genus:
                <select name="genus" size="1">
                </select>

                Species:
                <select name="species" size="1">

                </select>
                <!--<input type="SUBMIT">-->
            </form>
        </section>

        <section id="yearIn">
            Year Range:
            <span id="fromtoYear">InnerHtml</span>
            <form>
                <!--<input type="number" id="fromYear" class="yearN" value="2000" min="1960" max="2016" oninput="brightN()">-->
                <div id="slider-range"></div>
            </form>
        </section>

        <section id="outputIn">
            <form>
                <!--TODO: Set Default button entered-->
                <input type="radio" value="map"> Map
                <input type="radio" value="histogram"> Histogram
                <input type="submit">
            </form>
            <form method="POST" action=""">
                <input type="submit" value="Individual Records"> <!--To records.html-->
            </form>
        </section>
    </section>

    <section id="outputWrapper">
        <section id="outputDetails">Stuff like population count, entries found, etc. go here</section>
        <section id="outputBox">Map, Histogram Box</section>
        <section id="console">~~~ PSEUDO-CONSOLE ~~~<br></section>
    </section>

    <%--<footer>--%>
        <%--Footer--%>
    <%--</footer>--%>
</body>
</html>
