/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('document').ready(function(){
    const select = document.getElementById("shipment_id_select");

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Thesis/resources/shipments/retrieveAll',
        success: function(data){
            data.forEach(element => {
                const option = document.createElement("option");
                option.innerHTML = element.id+" "+element.departurePlace+" "+element.arrivalPlace;
                select.appendChild(option);
            });
        }
    })
});

