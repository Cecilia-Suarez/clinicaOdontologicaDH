window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del turno
    const formularioModificar = document.getElementById('update_turno_form');

    formularioModificar.addEventListener('submit', function (event) {
        let turnoId = document.querySelector('#turno_id').value;

        //creamos un JSON que tendrá los datos de los turnos
        //a diferencia de un turno nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#turno_id').value,
            paciente: {
                            id: document.querySelector('#paciente').value,
                        },
            odontologo: {
                            id: document.querySelector('#odontologo').value,
                        },
            date: document.querySelector('#fechaDeTurno').value
        };

        //invocamos utilizando la función fetch la API clinica odontologica con el método PUT que modificará
        //el turno que enviaremos en formato JSON
        const url = 'http://localhost:8080/turnos/update';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

            })

    });
});

function findBy(id) {
    const url = 'http://localhost:8080/turnos' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#odontologo').value = turno.odontologo.id;
            document.querySelector('#paciente').value = turno.paciente.id;
            document.querySelector('#fechaDeTurno').value = turno.date;

            //el formulario por default esta oculto y al editar se habilita
            document.getElementById('div_turno_updating').style.display = "block";
            document.getElementById('div_turno_table').style.display = "none";

        }).catch(error => {
            //Si hay algun error se muestra un mensaje diciendo que el turno
            //no se pudo modificar y se intente nuevamente
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Error intente nuevamente</strong> </div>'

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        });
};