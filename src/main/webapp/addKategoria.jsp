<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>dodawanie kategorii</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:import url="/menu"/>

<header><h2>Dodaj nowa kategorie</h2></header>


<form id="infoForm" action="kategorie" method="post">
    <label for="opis">Opis: </label>
    <input id="opis" name="opis" type="text" required/>

    <input type="submit" value="dodaj"/>
</form>

<a href="listaKategorii.jsp">powrot</a>

</body>
</html>
