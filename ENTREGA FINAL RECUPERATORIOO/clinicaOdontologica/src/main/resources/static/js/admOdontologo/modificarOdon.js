window.addEventListener('load', function () {
    
    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del odontologo
    const formularioModificar = document.getElementById('update_odontologo_form');

    formularioModificar.addEventListener('submit', function (event) {
        let odontologoId = document.querySelector('#odontologo_id').value;

        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
        };

        //invocamos utilizando la función fetch la API clinica odontologica con el método PUT que modificará
        //el odontologo que enviaremos en formato JSON
        const url = 'http://localhost:8080/odontologos/update';
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
    const url = 'http://localhost:8080/odontologos' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            document.querySelector('#odontologo_id').value = odontologo.id;
            document.querySelector('#matricula').value = odontologo.matricula;
            document.querySelector('#nombre').value = odontologo.nombre;
            document.querySelector('#apellido').value = odontologo.apellido;
            //el formulario por default esta oculto y al editar se habilita
            document.getElementById('div_odontologo_updating').style.display = "block";
            document.getElementById('div_odontologo_table').style.display = "none";

        }).catch(error => {
            //Si hay algun error se muestra un mensaje diciendo que el odontologo
            //no se pudo modificar y se intente nuevamente
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Error intente nuevamente</strong> </div>'

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        });
};