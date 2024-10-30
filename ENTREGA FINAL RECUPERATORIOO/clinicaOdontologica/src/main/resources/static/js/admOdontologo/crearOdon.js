window.addEventListener('load', function () {
    const buttonAgregar = document.getElementById('agregar_odontologo');
    const formulario = document.getElementById('formularioOdontologo');
    const barraBuscar = document.getElementById('barra_busqueda');
    const tableDiv = document.getElementById("div_odontologo_table");

    buttonAgregar.addEventListener('click', function () {
        formulario.style.display = 'block';
        barraBuscar.style.display = 'none';
        tableDiv.style.display = 'none';
    });

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //creamos un JSON que tendrá los datos del nuevo odontologo
        const formData = {
            matricula: document.querySelector('#inputMatricula').value,
            nombre: document.querySelector('#inputNombre').value,
            apellido: document.querySelector('#inputApellido').value,

        };

        //invocamos utilizando la función fetch la API clinicaOdontologica con el método POST que guardará
        //al odontologo que enviaremos en formato JSON
        const url = 'http://localhost:8080/odontologos/new';
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
                //Si no hay ningun error se muestra un mensaje diciendo que el paciente
                //se agrego bien
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong>Odontólogo agregado</strong> </div>'

                console.log(successAlert);

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = 'block';
                resetUploadForm();

            })
            .catch(error => {
                //Si hay algun error se muestra un mensaje diciendo que el odonotologo
                //no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                //se dejan todos los campos vacíos por si se quiere ingresar otro odonotologo
                resetUploadForm();
            })
    });

    function resetUploadForm() {
        document.querySelector('#inputMatricula').value = "";
        document.querySelector('#inputNombre').value = "";
        document.querySelector('#inputApellido').value = "";
    }
});