<%@ page import="java.util.List" %>
<%@ page import="pl.piotr.model.Kategoria" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>lista ksiazek</title>
    <link href="styles/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<c:import url="/menu"/>

<div id="zawartosc">
    <p>
        Witamy w naszej aplikacji, która ma pomagać w wyborze książek. Obecnie
        możesz tu znaleźć informacje na temat ciekawych książek z
        informatyki.
    </p>
</div>

<% Kategoria kategoria = (Kategoria)session.getAttribute("kategoria"); %>

<h3>
    Dla kategorii <c:out value="${kategoria.opis}"/>
</h3>

<h2>Lista ksiazek</h2>
<table class="data-table">
    <thead>
        <tr>
            <td>Tytul</td>
            <td>Wydawnictwo</td>
            <td>Kategoria</td>
        </tr>
    </thead>
    <tbody id="tableBody">
        <% List listaKsiazek = (List)session.getAttribute("listaKsiazek");%>
        <c:forEach items="${listaKsiazek}" var="ksiazka">
            <tr>
                <td><c:out value="${ksiazka.tytul}"/></td>
                <td><c:out value="${ksiazka.wydawnictwo.nazwa}"/></td>
                <td><c:out value="${ksiazka.kategoria.opis}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
