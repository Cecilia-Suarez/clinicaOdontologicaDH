window.addEventListener('load', function () {
    const botonListar = document.getElementById('listar_todos');
    const response = document.getElementById('response');
    const barraBuscar = document.getElementById('barra_busqueda');
    const tableDiv = document.getElementById("div_paciente_table");
    const formularioModificar = document.getElementById('div_paciente_updating');

    botonListar.addEventListener('click', function () {
        formulario.style.display = 'none';
        response.style.display = 'none';
        tableDiv.style.display = 'none';
        barraBuscar.style.display = 'none';
        formularioModificar.style.display= 'none';

        //con fetch invocamos a la API de clinicaOdontologica con el método GET
        //nos devolverá un JSON con una colección de pacientes
        const url = 'http://localhost:8080/pacientes/listarTodos';
        const settings = {
            method: 'GET'
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                const tableDiv = document.getElementById("div_paciente_table");
                const table = document.getElementById("pacienteTableBody");
                table.innerHTML = '';
                //recorremos la colección de pacientes del JSON
                for (paciente of data) {
                    //por cada paciente armaremos una fila de la tabla
                    var pacienteRow = table.insertRow();
                    let tr_id = 'tr_' + paciente.id;
                    pacienteRow.id = tr_id;

                    //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                    //dicho boton invocara a la funcion de java script eliminarPaciente que se encargará
                    //de llamar a la API para eliminar un paciente
                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                        ' type="button" onclick="eliminarPaciente(' + paciente.id + ')" class="btn btn-danger btn_delete"> Eliminar </button>';

                    //por cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
                    //a la función de java script findBy que se encargará de buscar al paciente que queremos
                    //modificar y mostrar los datos de la misma en un formulario.
                    let updateButton = '<button' +
                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                        ' type="button" onclick="findBy(' + paciente.id + ')" class="btn btn-info btn_id"> Modificar </button>';

                    //armamos cada columna de la fila
                    pacienteRow.innerHTML =
                        '<td class=\"td_id\">' + paciente.id + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.nombre + '</td>' +
                        '<td class=\"td_apellido\">' + paciente.apellido + '</td>' +
                        '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                        '<td class=\"td_domicilio\">' + paciente.domicilio.calle + '</td>' +
                        '<td class=\"td_domicilio\">' + paciente.domicilio.numero + '</td>' +
                        '<td class=\"td_domicilio\">' + paciente.domicilio.localidad + '</td>' +
                        '<td class=\"td_domicilio\">' + paciente.domicilio.provincia + '</td>' +
                        '<td class=\"td_fechaDeAlta\">' + paciente.fechaIngreso + '</td>' +
                        '<td>' + updateButton + '</td>' +
                        '<td>' + deleteButton + '</td>';

                };
                tableDiv.style.display = 'block';
                tableDiv.style.width = '100%';

            });
    });

});