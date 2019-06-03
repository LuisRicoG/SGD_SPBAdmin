$(document).ready(function () {

    $.validator.methods.dateISO = function (value) {
        console.log("value :"+ value);
        return /[1-31]\/[1-12]\/[1000-9999]/.test(value);
    }
    $("#fileUploadForm").validate({
        rules: {
            date: {
                required: true,
                dateISO:true
            },  
            descripcion: {
                required: true,
                maxlength: 50
            }
        },
        messages: {
            date: {
                required: "requerido",
                dateISO: "ingrese una fecha valida"
            },
            descripcion: {
                required: "requerido",
                maxlength: jQuery.validator.format("Longitud maxima es de: {0} caracteres")
            }
        },
        submitHandler: function (form) {
            form.submit();
        }
    });
});