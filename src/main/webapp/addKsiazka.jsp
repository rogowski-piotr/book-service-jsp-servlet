<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>dodawanie ksiazki</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:import url="/menu"/>

<header><h2>Dodaj nowa ksiazke</h2></header>


<form id="infoForm" action="listaKsiazek" method="post">

    <label for="tytul">Tytul: </label>
    <input id="tytul" name="tytul" type="text" required/>

    <% List listaWydawnictw = (List)session.getAttribute("listaWydawnictw");%>
    <label for="wydawnictwo">wybierz wydawnictwo</label>
    <select name="wydawnictwo" id="wydawnictwo">
        <c:forEach items="${listaWydawnictw}" var="wydawnictwo">
            <option value="<c:out value="${wydawnictwo.idw}"/>">
                <c:out value="${wydawnictwo.nazwa}"/>
            </option>
        </c:forEach>
    </select>

    <% List listaKategorii = (List)session.getAttribute("listaKategorii");%>
    <label for="kategoria">wybierz kategorie</label>
    <select name="kategoria" id="kategoria">
        <c:forEach items="${listaKategorii}" var="kategoria">
            <option value="<c:out value="${kategoria.idk}"/>">
                <c:out value="${kategoria.opis}"/>
            </option>
        </c:forEach>
    </select>

    <input type="submit" value="dodaj"/>
</form>

<a href="listaKsiazek">powrot</a>

</body>
</html>
