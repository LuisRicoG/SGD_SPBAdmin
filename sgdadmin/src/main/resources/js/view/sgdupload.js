/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('input[type=file]').change(function () {

        $(this).simpleUpload("/SGDADMIN/upload", {

            start: function (file) {
                //upload started
                $('#filename').html(file.name);
                $('#progress').html("");
                $('#progressBar').width(0);
            },

            progress: function (progress) {
                //received progress
                $('#progress').html("Progreso: " + Math.round(progress) + "%");
                $('#progressBar').width(progress + "%");
            },

            success: function (data) {
                //upload successful
                $('#progress').html("El archivo se subio correctamente!<br>Data: " + JSON.stringify(data));
            },

            error: function (error) {
                //upload failed
                $('#progress').html("Ocurrio un fallo!<br>" + error.name + ": " + error.message);
            }
        });
    });
});