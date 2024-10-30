window.addEventListener('load', function () {
    const botonListar = document.getElementById('listar_todosTurnos');
    const response = document.getElementById('response');
    const barraBuscar = document.getElementById('barra_busquedaTurno');
    const tableDiv = document.getElementById("div_turno_table");
    const formularioModificar = document.getElementById('div_turno_updating');
    const formulario = document.getElementById('formularioTurno');

    botonListar.addEventListener('click', function () {
        formulario.style.display = 'none';
        response.style.display = 'none';
        tableDiv.style.display = 'none';
        barraBuscar.style.display = 'none';
        formularioModificar.style.display= 'none';

        //con fetch invocamos a la API de clinicaOdontologica con el método GET
        //nos devolverá un JSON con una colección de turnos
        const url = 'http://localhost:8080/turnos/listarTodos';
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                const tableDiv = document.getElementById("div_turno_table");
                const table = document.getElementById("turnoTableBody");
                table.innerHTML = '';
                //recorremos la colección de turnos del JSON
                for (turno of data) {
                    //por cada turno armaremos una fila de la tabla
                    var turnoRow = table.insertRow();
                    let tr_id = 'tr_' + turno.id;
                    turnoRow.id = tr_id;

                    //por cada turno creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                    //dicho boton invocara a la funcion de java script eliminarturno que se encargará
                    //de llamar a la API para eliminar un turno
                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                        ' type="button" onclick="eliminarTurno(' + turno.id + ')" class="btn btn-danger btn_delete"> Eliminar </button>';

                    //por cada turno creamos un boton que muestra el id y que al hacerle clic invocará
                    //a la función de java script findBy que se encargará de buscar al turno que queremos
                    //modificar y mostrar los datos de la misma en un formulario.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                        ' type="button" onclick="findBy(' + turno.id + ')" class="btn btn-info btn_id"> Modificar </button>';

                    //armamos cada columna de la fila
                    turnoRow.innerHTML =
                        '<td class=\"td_id\">' + turno.id + '</td>' +
                        '<td class=\"td_nombre\">' + turno.paciente.apellido + '</td>' +
                        '<td class=\"td_apellido\">' + turno.odontologo.apellido + '</td>' +
                        '<td class=\"td_fecha\">' + turno.date + '</td>' +
                        '<td>' + updateButton + '</td>' +
                        '<td>' + deleteButton + '</td>';

                };
                tableDiv.style.display = 'block';
                tableDiv.style.width = '100%';

            });
    });

});