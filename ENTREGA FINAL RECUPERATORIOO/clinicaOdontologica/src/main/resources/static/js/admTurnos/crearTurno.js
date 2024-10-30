window.addEventListener('load', function () {
    const buttonAgregar = document.getElementById('agregar_turno');
    const formulario = document.getElementById('formularioTurno');
    const barraBuscar = document.getElementById('barra_busquedaTurno');
    const tableDiv = document.getElementById("div_turno_table");
    const formularioModificar = document.getElementById('div_turno_updating');

    buttonAgregar.addEventListener('click', function () {
        formulario.style.display = 'block';
        barraBuscar.style.display = 'none';
        tableDiv.style.display = 'none';
        formularioModificar.style.display= 'none';
    });

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //creamos un JSON que tendrá los datos del nuevo turno

        const formData = {
            paciente: {
                id: document.querySelector('#inputPaciente').value,
            },
            odontologo: {
                id: document.querySelector('#inputOdontologo').value,
            },
            date: document.querySelector('#inputFechaTurno').value,
        }
        //invocamos utilizando la función fetch la API clinicaOdontologica con el método POST que guardará
        //al turno que enviaremos en formato JSON
        const url = 'http://localhost:8080/turnos/new';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                //Si no hay ningun error se muestra un mensaje diciendo que el turno
                //se agrego bien
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Turno agregado </strong> </div>'

                console.log(successAlert);

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = 'block';
                resetUploadForm();

            })
            .catch(error => {
                //Si hay algun error se muestra un mensaje diciendo que el turno
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //se dejan todos los campos vacíos por si se quiere ingresar otro turno
                resetUploadForm();
            })
    });

    function resetUploadForm() {
        document.querySelector('#inputPaciente').value = "";
        document.querySelector('#inputOdontologo').value = "";
        document.querySelector('#inputFechaTurno').value = "";
    }
});