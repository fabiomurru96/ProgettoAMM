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
        
        <c:set property="pagina" value="profilo"/>
        <jsp:include page="header.jsp"/>
        
        <div id="divBody">
            
            <jsp:include page="sidebar.jsp"/>
            
            <div id="profileContent" class="content">
                <div id="formdati">
                    <img src="${sessionScope.loggedUser.urlFoto}" alt="Foto profilo di ${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}" title="${sessionScope.loggedUser.nome} ${sessionScope.loggedUser.cognome}"/>
                    <form action="#" method="post">
                        <div class="campoFormProfilo" id="formNome">
                            <label for="nome" class="firstLabel">Nome</label>
                            <input id="nome" name="nome" type="text" placeholder="${sessionScope.loggedUser.nome}"/>
                        </div>
                        <div class="campoFormProfilo" id="formCognome">
                            <label for="cognome">Cognome</label>
                            <input id="cognome" name="cognome" type="text" placeholder="${sessionScope.loggedUser.cognome}"/>
                        </div>
                        <div class="campoFormProfilo" id="formPropic">
                            <label for="propic">Immagine profilo</label>
                            <input id="propic" name="propic" type="url"/>
                        </div>
                        <div class="campoFormProfilo" id="formFrase">
                            <label for="status">Frase di presentazione</label>
                            <textarea id="status" name="status" rows="2"></textarea>
                        </div>
                        <div class="campoFormProfilo" id="formData">
                            <label for="datanascita">Data di nascita</label>
                            <input id="datanascita" name="datanascita" type="date"/>
                        </div>
                        <div class="campoFormProfilo" id="formPassword">
                            <label for="psswd">Password</label>
                            <input id="psswd" name="psswd" type="password"/>
                        </div>
                        <div class="campoFormProfilo" id="formConfermaP">
                            <label for="confirmpss">Conferma password</label>
                            <input id="confirmpss" name="confirmpss" type="password"/>
                        </div>
                        <button type="submit">Aggiorna</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
