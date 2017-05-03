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
        <title>Bacheca - Nerdbook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabio Murru">
        <meta name="keywords" content="amici cerca social network nerd geek computer bacheca">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        
    </head>
    <body>
        
        <c:set property="pagina" value="bacheca"/>
        <jsp:include page="header.jsp"/>
        
        <div id="divBody">
             
            <jsp:include page="sidebar.jsp"/>
            
            <div id="posts" class="content">
                
                <c:forEach var="post" items="${posts}">
                    <div class="postType">            
                        <div class="postUserInfo">
                            <div class="proPic">
                                <img src="${post.utente.urlFoto}" alt="Foto profilo di ${post.utente.nome} ${post.utente.cognome}" title="${post.utente.nome} ${post.utente.cognome}"/>
                            </div>
                            <p class="username">${post.utente.nome} ${post.utente.cognome}</p>
                        </div>
                        <div class="postContent">
                            <p>${post.testo}</p>
                            <c:if test="${post.tipoAllegato == 'IMG'}">
                                <img class="postPic" src="${post.allegato}" alt="Foto allegato"/>
                            </c:if>
                            <c:if test="${post.tipoAllegato == 'LINK'}">
                                <a href="${post.allegato}" target"_blank">${post.allegato}</a>
                            </c:if>  
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
