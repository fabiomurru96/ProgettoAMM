<%-- 
    Document   : newjsp
    Created on : May 2, 2017, 8:18:27 PM
    Author     : fabio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sideBar">
    <div id="personeSide">
        <h2 class="sideTitle">Persone</h2>
        <div>
            <c:forEach var="utente" items="${utenti}">
                <div>
                    <div class="proPic">
                        <img src="${utente.urlFoto}" alt="Foto profilo di ${utente.nome} ${utente.cognome}" title="${utente.nome} ${utente.cognome}"/>
                    </div>
                    <p>${utente.nome} ${utente.cognome}</p>
                </div>
            </c:forEach>
        </div>
    </div>
    <div id="gruppiSide">
        <h2 class="sideTitle">Gruppi</h2>
        <div>
            <c:forEach var="gruppo" items="$gruppi">
                <div class="group">
                    <img src="${gruppo.urlFoto}"
                    <p>${gruppo.nome}</p>
                </div>  
            </c:forEach>
        </div>
    </div>
</div> 
