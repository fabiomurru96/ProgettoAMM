<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login - Nerdbook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabio Murru">
        <meta name="keywords" content="amici cerca social network nerd geek computer login accesso">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        
        <c:set var="pagina" value="login" scope="request"/>
        <jsp:include page="header.jsp"/>
        
        <div id="divBody">
            <div id="login">
                <h1>Nerdbook</h1>
                <form method="post" action="login.html">
                    <label for="user" class="firstLabel">Username</label>
                    <input name="user" id="user" type="text">
                    <label for="password">Password</label>
                    <input name="password" id="password" type="password">
                    <c:if test="${invalidData == true}">
                        <p>Inseriti dati scorretti.</p>
                    </c:if>
                    <button type="submit">Invia</button>
                </form>
            </div>
        </div>
    </body>
</html>
