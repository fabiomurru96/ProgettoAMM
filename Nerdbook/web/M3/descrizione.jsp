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
        <title>Descrizione - Nerdbook</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Fabio Murru">
        <meta name="keywords" content="amici cerca social network nerd geek computer descrizione">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        
        <c:set property="pagina" value="descrizione"/>
        <jsp:include page="header.jsp"/>
        
        <div id="divBody">
            <article>
                <h1>F.A.Q.</h1>
                <div id="sum">
                    <h2>Sommario</h2>
                    <ol>
                        <li>
                            <a href="#generiche">Domande generiche</a>
                            <ol>
                                <li><a href="#aChi">Per chi</a></li>
                                <li><a href="#comeIscriversi">Iscrizione</a></li>
                                <li><a href="#costo">Costo</a></li>
                            </ol>
                        </li>
                    </ol>
                </div>
                <section id="generiche">
                    <h2>Domande generiche</h2>
                    <section id="aChi">
                        <h3>A chi Ã¨ rivolto?</h3>
                        <p>Nerdbook Ã¨ rivolto a chiunque abbia la nostra passione, e voglia conoscere nuovi amici.</p>
                    </section>
                    <section id="comeIscriversi">
                        <h3>Come iscriversi?</h3>
                        <p>Vai su login.</p>
                    </section>
                    <section id="costo">
                        <h3>Quanto costa?</h3>
                        <p>Nerdbook Ã¨ completamente gratuito a chiunque si iscriva.</p>
                    </section>
                </section>
            </article>
        </div>
    </body>
</html>
