<%-- 
    Document   : header
    Created on : May 2, 2017, 12:19:56 PM
    Author     : fabio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header <c:if test="${pagina == 'login' || (pagina == 'descrizione' && sessionScope.loggedIn == 'true')}">id="logHeader"</c:if>>
    <div id="title">
        Nerdbook
    </div>
    <nav <c:if test="${pagina == 'login' || (pagina == 'descrizione' && sessionScope.loggedIn == 'true')}">id="logNav"</c:if>>
        <ul>
            <li <c:if test="${pagina == 'descrizione'}">class="active"</c:if>>
                <a href="descrizione.html">Descrizione</a>
            </li>
            <c:if test="${pagina != 'login'}">
                <li <c:if test="${pagina == 'profilo'}">class="active"</c:if>>
                    <a href="profilo.html">Profilo</a>
                </li>
                <li <c:if test="${pagina == 'bacheca'}">class="active"</c:if>>
                    <a href="bacheca.html">Bacheca</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <c:if test="${pagina != 'login'}">
        <c:if test="${pagina != 'descrizione' || sessionScope.loggedIn == 'true'}">
            <div id="loggedUser">
                <div class="proPic">
                    <img src="${sessionScope.loggedUser.urlFoto}" alt="Foto profilo di ${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}" title="${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}"/>
                </div>
                <p>${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}</p>
                <a href="#">Logout</a>
            </div>
        </c:if>
    </c:if>
</header>