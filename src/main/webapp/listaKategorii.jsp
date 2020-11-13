<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>lista kategorii</title>
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

<h2>Lista kategorii</h2>
<table class="data-table">
    <thead>
        <tr>
            <td>Id</td>
            <td>Opis</td>
        </tr>
    </thead>
    <tbody id="tableBody">
        <% List listaKategorii = (List)session.getAttribute("listaKategorii");%>
        <c:forEach items="${listaKategorii}" var="kategoria">
            <tr>
                <td><c:out value="${kategoria.idk}"/></td>
                <td><c:out value="${kategoria.opis}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="addKategoria.jsp">dodaj kategorie</a>

</body>
</html>
