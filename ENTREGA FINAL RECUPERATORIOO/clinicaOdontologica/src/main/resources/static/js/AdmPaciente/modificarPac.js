window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del paciente
    const formularioModificar = document.getElementById('update_paciente_form');

    formularioModificar.addEventListener('submit', function (event) {
        let pacienteId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos del paciente
        //a diferencia de un paciente nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },
            fechaIngreso: document.querySelector('#fechaDeAlta').value,
        };

        //invocamos utilizando la función fetch la API clinica odontologica con el método PUT que modificará
        //el paciente que enviaremos en formato JSON
        const url = 'http://localhost:8080/pacientes/update';
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
                alert("Modificado con exito")
            })

    });
});

function findBy(id) {
    const url = 'http://localhost:8080/pacientes' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#paciente_id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#dni').value = paciente.dni;
            document.querySelector('#calle').value = paciente.domicilio.calle;
            document.querySelector('#numero').value = paciente.domicilio.numero;
            document.querySelector('#localidad').value = paciente.domicilio.localidad;
            document.querySelector('#provincia').value = paciente.domicilio.provincia;
            document.querySelector('#fechaDeAlta').value = paciente.fechaIngreso;
            //el formulario por default esta oculto y al editar se habilita
            document.getElementById('div_paciente_updating').style.display = "block";
            document.getElementById('div_paciente_table').style.display = "none";

        }).catch(error => {
            //Si hay algun error se muestra un mensaje diciendo que el paciente
            //no se pudo modificar y se intente nuevamente
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Error intente nuevamente</strong> </div>'

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        });
};