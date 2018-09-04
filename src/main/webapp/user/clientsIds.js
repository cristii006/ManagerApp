$('document').ready(function(){
    const select = document.getElementById("client_id_select");

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/Thesis/resources/clients/retrieveAll',
        accepts: 'application/json',
        success: function(data){
            data.forEach(element => {
                const option = document.createElement("option");
                option.innerHTML = element.id;
                select.appendChild(option);
            });
        }
    })
});