/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
    backupP = $("#personeSide").clone();
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
    $("#gruppiSide").hide();
    
    $("#personeSide").empty();

    
    if($("#cerca").val()!=="")
    {
        var div = document.createElement("div");

        for(var i = 0; i<result.length; i++)
        {
            var utente = document.createElement("a");
            var divVuoto = document.createElement("div");
            var divProPic = document.createElement("div");
            var proPic = document.createElement("img");
            var nome = document.createElement("p");

            $(proPic).attr("src",result[i].urlFoto);

            $(divProPic).addClass("proPic");
            $(divProPic).prepend(proPic);

            $(nome).text(result[i].nome + " " + result[i].cognome);

            $(utente).attr("href", "bacheca.html?user="+result[i].id);

            $(utente).prepend(divProPic);
            $(utente).append(nome);
            $(div).append(utente);
        }

        $("#personeSide").append(div);
    }
    else
    {
        $("#personeSide").replaceWith(backupP.clone());
        $("#gruppiSide").show();
    }
    
}