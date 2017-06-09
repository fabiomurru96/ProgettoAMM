/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
    $("#cerca").keyup(function ()
    {
        console.log($("#cerca").val());
        $.ajax({
            url: "filter.json",
            data: {
                q: $("#cerca").val()
            },
            dataType: 'json',
            success: function (data, state) {
                updateSidebar(data);
            },
            error: function (data, state) {
            }
        });
    });
});

function updateSidebar(result)
{
    $("#gruppiSide").remove();
    $("#personeSide").empty();
    
    var div = document.createElement("div");
    
    for(var u in result)
    {
        var utente = document.createElement("a");
        var divVuoto = document.createElement("div");
        var divProPic = document.createElement("div");
        var proPic = document.createElement("img");
        var nome = document.createElement("p");
        
        $(proPic).attr("src",u.urlFoto);
        
        $(divProPic).addClass("proPic");
        $(divProPic).prepend(proPic);
        
        $(nome).text(u.nome + " " + u.cognome);
        
        $(utente).attr("href", "bacheca.html?user="+u.id);
        
        $(utente).prepend(divProPic);
        $(utente).append(nome);
        $(div).append(utente);
    }
    
    $("#personeSide").append(div);
    
    
}