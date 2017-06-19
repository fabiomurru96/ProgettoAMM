<%-- 
    Document   : cercaJson
    Created on : Jun 10, 2017, 12:09:53 AM
    Author     : fabio
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="user" items="${result}">
        <json:object>
            <json:property name="id" value="${user.id}"/>
            <json:property name="nome" value="${user.nome}"/>
            <json:property name="urlFoto" value="${user.urlFoto}"/>
            <json:property name="cognome" value="${user.cognome}"/>
        </json:object>
    </c:forEach>
</json:array>