window.addEventListener('load', function () {
    const barraBuscar = document.getElementById('barra_busquedaTurno');
    const botonBuscarNav = document.getElementById('boton_buscarTurno');
    const formulario = document.getElementById('formularioTurno');
    const tableDiv = document.getElementById("div_turno_table");
    const response = document.getElementById('response');
    const formularioModificar = document.getElementById('div_turno_updating');

    botonBuscarNav.addEventListener('click', function () {
        barraBuscar.style.display = 'block';
        formulario.style.display = 'none';
        response.style.display= 'none';
        formularioModificar.style.display= 'none';
    });

    barraBuscar.addEventListener('submit', function (event) {
        event.preventDefault();
        const id = document.getElementById('idTurno').value;
        const url = 'http://localhost:8080/turnos/' + id;
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                var tableDiv = document.getElementById("div_turno_table");
                var table = document.getElementById("turnoTableBody");
                table.innerHTML = '';
                var turnoRow = table.insertRow();
                let tr_id = 'tr_' + data.id;
                turnoRow.id = tr_id;

                //para cada turno creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                //dicho boton invocara a la funcion de java script eliminarTurno que se encargará
                //de llamar a la API para eliminar un turno
                let deleteButton = '<button' +
                    ' id=' + '\"' + 'btn_delete_' + data.id + '\"' +
                    ' type="button" onclick="eliminarTurno(' + data.id + ')" class="btn btn-danger btn_delete"> Eliminar </button>';

                //para cada turno creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar al turno que queremos
                //modificar y mostrar los datos de la misma en un formulario.
                let updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + data.id + '\"' +
                    ' type="button" onclick="findBy(' + data.id + ')" class="btn btn-info btn_id"> Modificar </button>';

                //armamos cada columna de la fila
                turnoRow.innerHTML =
                    '<td class=\"td_id\">' + data.id + '</td>' +
                    '<td class=\"td_odontologo\">' + data.odontologo.apellido + '</td>' +
                    '<td class=\"td_paciente\">' + data.paciente.apellido + '</td>' +
                    '<td class=\"td_fechaTurno\">' + data.date + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';

                tableDiv.style.display = 'block';
                tableDiv.style.width = '100%';
            });
    });
});


