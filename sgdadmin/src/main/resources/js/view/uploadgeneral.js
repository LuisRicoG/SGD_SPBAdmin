$(document).ready(function () {
    $('#submit').on("click", function () {
       $("#fileUploadForm").submit();
    });

    $("#fileUploadForm").validate({
        rules: {
            date: {
                required: true
            },
            file: {
                required: true
            },
            descripcion: {
                required: true,
                maxlength: 50
            }
        },
        messages: {
            date: {
                required: "requerido"
            },
            file: {
                required: "requerido"
            },
            descripcion: {
                required: "requerido",
                maxlength: jQuery.validator.format("Longitud maxima es de: {0} caracteres")
            }
        },
        submitHandler: function (form) {
            uploadDocument(form);
        }
    });
});


function uploadDocument(form) {
    var req = new XMLHttpRequest();
    var formData = new FormData(form);
    req.open("POST", "/SGDADMIN/upload", true);

    req.onload = function (event) {
        if (req.status !== 200) {
            alert("Ocurrio un error al cargar el archivo");
            return;
        } else {
            alert(req.responseText);
        }

    };
    req.send(formData);
}
