<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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

        <c:set var="pagina" value="bacheca" scope="request"/>
        <jsp:include page="header.jsp"/>

        <div id="divBody">
            <c:if test="${error == '403'}">
                <p>Accesso Negato.</p>
            </c:if>
            <c:if test="${error != '403'}">

                <jsp:include page="sidebar.jsp"/>

                <div id="posts" class="content">
                    <c:if test="${userU != null}">
                        <div  id="status">
                            <div class="postUserInfo">
                                <div class="proPic">
                                    <img src="${userU.urlFoto}" alt="Foto profilo di ${userU.nome} ${userU.cognome}" title="${userU.nome} ${userU.cognome}"/>
                                </div>
                                <p class="username">${userU.nome} ${userU.cognome}</p>
                                <p>${userU.frase}</p>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${submit == null&&(userU!=null || groupG!=null)}">
                        <div id="formdati" class="postType">
                            <div class="proPic">
                                <img src="${sessionScope.loggedUser.urlFoto}" alt="Foto profilo di ${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}" title="${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}"/>
                            </div>
                                <form action="bacheca.html<c:if test="${user != null}">?user=${user}</c:if><c:if test="${group != null}">?group=${group}</c:if>" method="post" accept-charset="UTF-8">
                                    <div class="campoFormProfilo">
                                        <label for="testo" class="firstLabel">Testo</label>
                                        <input id="testo" name="testo" type="text"/>
                                    </div>
                                    <div class="campoFormProfilo">
                                        <label for="allegato">Allegato</label>
                                        <input id="allegato" name="allegato" type="url"/>
                                    </div>
                                    <div class="radio">
                                        <label for="none">Nessuno</label>
                                        <input id="none" name="tipo" type="radio" checked="checked" value="NONE"/>
                                        <label for="image">Immagine</label>
                                        <input id="image" name="tipo" type="radio" value="IMAGE"/>
                                        <label for="link">Link</label>
                                        <input id="link" name="tipo" type="radio" value="LINK"/>
                                    </div>
                                <c:if test="${err == '1'}"></br><p>Inserisci il Testo.</p></br></c:if>
                                <c:if test="${err == '2'}"></br><p>Inserisci Allegato.</p></br></c:if>
                                    <button type="submit" name="invia" value="2">Invia</button>
                                <c:if test="${scritto != null && user != null}"></br></br><p>Hai scritto sulla bacheca di ${userU.nome} ${userU.cognome}!</p></br></c:if>
                                <c:if test="${scritto != null && group != null}"></br></br><p>Hai scritto sulla bacheca del gruppo!</p></br></c:if>
                                </form>

                            </div>
                    </c:if>
                    <c:if test="${submit != null}">
                        <div class="postType">            
                            <div class="postUserInfo">
                                <div class="proPic">
                                    <img src="${postNew.autore.urlFoto}" alt="Foto profilo di ${postNew.autore.nome} ${postNew.autore.cognome}" title="${postNew.autore.nome} ${postNew.autore.cognome}"/>
                                </div>
                                <p class="username">${postNew.autore.nome} ${postNew.autore.cognome}</p>
                            </div>
                            <div class="postContent">
                                <p>${postNew.testo}</p>
                                <c:if test="${postNew.tipoAllegato == 'IMAGE'}">
                                    <img class="postPic" src="${postNew.allegato}" alt="Foto allegato"/>
                                </c:if>
                                <c:if test="${postNew.tipoAllegato == 'LINK'}">
                                    <a href="${postNew.allegato}" target"_blank">${postNew.allegato}</a>
                                </c:if>  
                            </div>
                            <form action="bacheca.html<c:if test="${user != null}">?user=${user}</c:if><c:if test="${group != null}">?group=${group}</c:if>" method="post">
                                <input type="hidden" name="postC" value="${postCode}"/>
                                <button type="submit" name="confirm" value="1">Conferma</button>
                            </form>
                        </div>
                    </c:if>
                    <c:forEach var="post" items="${posts}">
                        <div class="postType">            
                            <div class="postUserInfo">
                                <div class="proPic">
                                    <img src="${post.autore.urlFoto}" alt="Foto profilo di ${post.autore.nome} ${post.autore.cognome}" title="${post.autore.nome} ${post.autore.cognome}"/>
                                </div>
                                <p class="username">${post.autore.nome} ${post.autore.cognome}</p>
                            </div>
                            <div class="postContent">
                                <p>${post.testo}</p>
                                <c:if test="${post.tipoAllegato == 'IMAGE'}">
                                    <img class="postPic" src="${post.allegato}" alt="Foto allegato"/>
                                </c:if>
                                <c:if test="${post.tipoAllegato == 'LINK'}">
                                    <a href="${post.allegato}" target"_blank">${post.allegato}</a>
                                </c:if>  
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </body>
</html>
