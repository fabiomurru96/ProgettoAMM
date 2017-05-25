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
        <title>Profilo - Nerdbook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabio Murru">
        <meta name="keywords" content="amici cerca social network nerd geek computer profilo">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        
        <c:set var="pagina" value="profilo" scope="request"/>
        <jsp:include page="header.jsp"/>
        
        <div id="divBody">
            <c:if test="${error == '403'}">
                            <p>Accesso Negato.</p>
            </c:if>
            <c:if test="${error != '403'}">
                    
                <jsp:include page="sidebar.jsp"/>

                <div id="profileContent" class="content">
                    
                    <c:if test="${confermaDelete != null}">
                        <div id="delForm" class="postType">
                            <p>Sei sicuro di voler cancellare il tuo profilo?</p>
                            <form action="profilo.html" method="post">
                                <button type="submit" name="delete" value="true">Si.</button>
                                <button type="submit" name="delete" value="false">Annulla.</button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${confermaDelete == null}">
                    <div id="formdati" class="profile">
                        <div class="proPic">
                            <img src="${sessionScope.loggedUser.urlFoto}" alt="Foto profilo di ${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}" title="${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}"/>
                        </div>
                        
                        <form action="profilo.html" method="post">
                            <c:if test="${done == '1'}"><p>Campi aggiornati</p></c:if>
                            <div class="campoFormProfilo" id="formNome">
                                <label for="nome" class="firstLabel">Nome</label>
                                <input id="nome" name="nome" type="text" <c:if test="${done == '1'}">placeholder="${nome}"</c:if>/>
                            </div>
                            <div class="campoFormProfilo" id="formCognome">
                                <label for="cognome">Cognome</label>
                                <input id="cognome" name="cognome" type="text" <c:if test="${done == '1'}">placeholder="${cognome}"</c:if>/>
                            </div>
                            <div class="campoFormProfilo" id="formPropic">
                                <label for="propic">Immagine profilo</label>
                                <input id="propic" name="propic" type="url" <c:if test="${done == '1'}">placeholder="${propic}"</c:if>/>
                            </div>
                            <div class="campoFormProfilo" id="formFrase">
                                <label for="status">Frase di presentazione</label>
                                <textarea id="status" name="status" rows="2" <c:if test="${done == '1'}">placeholder="${status}"</c:if>></textarea>
                            </div>
                            <div class="campoFormProfilo" id="formData">
                                <label for="datanascita">Data di nascita</label>
                                <input id="datanascita" name="datanascita" type="date" <c:if test="${done == '1'}">placeholder="${datanascita}"</c:if>/>
                            </div>
                            <div class="campoFormProfilo" id="formPassword">
                                <label for="psswd">Password</label>
                                <input id="psswd" name="psswd" type="password"/>
                            </div>
                            <div class="campoFormProfilo" id="formConfermaP">
                                <label for="confirmpss">Conferma password</label>
                                <input id="confirmpss" name="confirmpss" type="password"/>
                            </div>
                            <c:if test="${errpass == '1'}"></br><p>Conferma la password.</p></br></c:if>
                            <c:if test="${errpass == '2'}"></br><p>Le password devono corrispondere.</p></br></c:if>
                            <button type="submit" name="confirm" value="1">Aggiorna</button> 
                            <button id="deleteProfile" type="submit" name="confermaDelete" value="1" title="Cancella il profilo"></button>
                        </form>
                    </div>
                    </c:if>
                </div>
            </c:if>
        </div>
    </body>
</html>
