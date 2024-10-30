window.addEventListener('load', function () {
    const botonListar = document.getElementById('listar_todos');
    const response = document.getElementById('response');
    const barraBuscar = document.getElementById('barra_busqueda');
    const tableDiv = document.getElementById("div_odontologo_table");
    const formularioModificar = document.getElementById('div_odontologo_updating');


    botonListar.addEventListener('click', function () {
        formularioOdontologo.style.display = 'none';
        response.style.display = 'none';
        tableDiv.style.display = 'none';
        barraBuscar.style.display = 'none';
        formularioModificar.style.display= 'none';

        //con fetch invocamos a la API de clinicaOdontologica con el método GET
        //nos devolverá un JSON con una colección de odontologo
        const url = 'http://localhost:8080/odontologos/listarTodos';
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                const tableDiv = document.getElementById("div_odontologo_table");
                const table = document.getElementById("odontologoTableBody");
                table.innerHTML = '';
                //recorremos la colección de odontologo del JSON
                for (odontologo of data) {
                    //por cada odontologo armaremos una fila de la tabla
                    var odontologoRow = table.insertRow();
                    let tr_id = 'tr_' + odontologo.id;
                    odontologoRow.id = tr_id;

                    //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                    //dicho boton invocara a la funcion de java script eliminarOdontologo que se encargará
                    //de llamar a la API para eliminar un odontologo
                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                        ' type="button" onclick="eliminarOdontologo(' + odontologo.id + ')" class="btn btn-danger btn_delete"> Eliminar </button>';

                    //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
                    //a la función de java script findBy que se encargará de buscar al odontologo que queremos
                    //modificar y mostrar los datos de la misma en un formulario.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                        ' type="button" onclick="findBy(' + odontologo.id + ')" class="btn btn-info btn_id"> Modificar </button>';

                    //armamos cada columna de la fila
                    odontologoRow.innerHTML =
                        '<td class=\"td_id\">' + odontologo.id + '</td>' +
                        '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                        '<td class=\"td_nombre\">' + odontologo.nombre + '</td>' +
                        '<td class=\"td_apellido\">' + odontologo.apellido + '</td>' +
                        '<td>' + updateButton + '</td>' +
                        '<td>' + deleteButton + '</td>';

                };
                tableDiv.style.display = 'block';
                tableDiv.style.width = '100%';

            });
    });

});