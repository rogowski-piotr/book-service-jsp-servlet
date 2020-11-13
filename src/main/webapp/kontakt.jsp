<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>kontakt</title>
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

<h2>Lista wydawnictw</h2>
<table class="data-table">
    <thead>
        <tr>
            <td>nazwa</td>
            <td>adres</td>
        </tr>
    </thead>
    <tbody id="tableBody">
        <% List listaWydawnictw = (List)session.getAttribute("listaWydawnictw");%>
        <c:forEach items="${listaWydawnictw}" var="wydawnictwo">
            <tr>
                <td><c:out value="${wydawnictwo.nazwa}"/></td>
                <td><c:out value="${wydawnictwo.adres}"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
