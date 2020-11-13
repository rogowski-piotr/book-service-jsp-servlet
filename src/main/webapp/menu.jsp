<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="menu">
    <% List lista = (List)session.getAttribute("lista");%>
    <ul>
        <li><a href="index.jsp">Strona domowa</a></li>
        <li><a href="listaKsiazek">Wszystkie książki</a></li>
        <li class="podmenu"><a href="kategorie">Kategorie</a>
            <ul>
                <c:forEach items="${lista}" var="kategoria">
                    <li>
                        <a href="kategoriaKsiazki?id=<c:out value='${kategoria.idk}'/>">
                            <c:out value="${kategoria.opis}"/></a>
                    </li>
                </c:forEach>
            </ul>
        </li>
        <li><a href="kontakt">Kontakt</a></li>
    </ul>
</div>


</body>
</html>
