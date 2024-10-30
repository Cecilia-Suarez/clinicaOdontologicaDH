window.addEventListener('load', function () {
    const barraBuscar = document.getElementById('barra_busqueda');
    const botonBuscarNav = document.getElementById('boton_buscar');
    const formulario = document.getElementById('formulario');
    const tableDiv = document.getElementById("div_paciente_table");
    const response = document.getElementById('response');
    const formularioModificar = document.getElementById('div_paciente_updating');

    botonBuscarNav.addEventListener('click', function () {
        barraBuscar.style.display = 'block';
        formulario.style.display = 'none';
        response.style.display= 'none';
        formularioModificar.style.display= 'none';
    });

    barraBuscar.addEventListener('submit', function (event) {
        event.preventDefault();
        const id = document.getElementById('idPaciente').value;
        const url = 'http://localhost:8080/pacientes/' + id;
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                var tableDiv = document.getElementById("div_paciente_table");
                var table = document.getElementById("pacienteTableBody");
                table.innerHTML = '';
                var pacienteRow = table.insertRow();
                let tr_id = 'tr_' + data.id;
                pacienteRow.id = tr_id;

                //para cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                //dicho boton invocara a la funcion de java script eliminarPaciente que se encargará
                //de llamar a la API para eliminar un paciente
                let deleteButton = '<button' +
                    ' id=' + '\"' + 'btn_delete_' + data.id + '\"' +
                    ' type="button" onclick="eliminarPaciente(' + data.id + ')" class="btn btn-danger btn_delete"> Eliminar </button>';

                //para cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar al paciente que queremos
                //modificar y mostrar los datos de la misma en un formulario.
                let updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + data.id + '\"' +
                    ' type="button" onclick="findBy(' + data.id + ')" class="btn btn-info btn_id"> Modificar </button>';

                //armamos cada columna de la fila
                pacienteRow.innerHTML =
                    '<td class=\"td_id\">' + data.id + '</td>' +
                    '<td class=\"td_nombre\">' + data.nombre + '</td>' +
                    '<td class=\"td_apellido\">' + data.apellido + '</td>' +
                    '<td class=\"td_dni\">' + data.dni + '</td>' +
                    '<td class=\"td_domicilio\">' + data.domicilio.calle + '</td>' +
                    '<td class=\"td_domicilio\">' + data.domicilio.numero + '</td>' +
                    '<td class=\"td_domicilio\">' + data.domicilio.localidad + '</td>' +
                    '<td class=\"td_domicilio\">' + data.domicilio.provincia + '</td>' +
                    '<td class=\"td_fechaDeAlta\">' + data.fechaIngreso + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';

                tableDiv.style.display = 'block';
                tableDiv.style.width = '100%';
            });
    });
});






