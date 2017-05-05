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
                <a href="bacheca.html?user=${utente.id}">
                    <div>
                        <div class="proPic">
                            <img src="${utente.urlFoto}" alt="Foto profilo di ${utente.nome} ${utente.cognome}" title="${utente.nome} ${utente.cognome}"/>
                        </div>
                        <p>${utente.nome} ${utente.cognome}</p>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
    <div id="gruppiSide">
        <h2 class="sideTitle">Gruppi</h2>
        <div>
            <c:forEach var="gruppo" items="${gruppi}">
                <a href="bacheca.html?group=${gruppo.id}">
                    <div class="group">
                        <div class="proPic">
                            <img src="${gruppo.urlFoto}" alt="Foto gruppo dei ${gruppo.nome}">
                        </div>
                        <p>${gruppo.nome}</p>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div> 
