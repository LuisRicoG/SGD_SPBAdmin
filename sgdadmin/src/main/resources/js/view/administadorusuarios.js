$(document).ready(function () {
    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",

        inserting: true,
        editing: true,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 5,
        pageButtonCount: 5, 
        pagerFormat: "Pag: {first} {prev} {pages} {next} {last}    {pageIndex} of {pageCount}",
        pagePrevText: "Prev",
        pageNextText: "Siguiente",
        pageFirstText: "Primero",
        pageLastText: "Último",
        pageNavigatorNextText: "...",
        pageNavigatorPrevText: "...",
        rowClick: function (args) {
            showDetailsDialog("Editar", args.item);
        },
        clients,
        controller: controllers,

        fields: [
            {name: "nombre1", type: "text", validate: "required", title: "Nombre"},
            {name: "nombre2", type: "text", title: "Segundo Nombre"},
            {name: "apellido_paterno", type: "text", title: "Apellido Paterno"},
            {name: "apellido_materno", type: "text", title: "Apellido Materno"},
            {name: "correo_electronico", type: "text", width: "130", title: "Correo Electrónico"},
            {name: "telefono", type: "text", title: "Teléfono"},
            {name: "estatus", type: "select", title: "Estatus"},
            {name: "usuario", type: "text", title: "Usuario"},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                deleteButton: false,
                headerTemplate: function () {
                    return $("<button>").attr("type", "button").text("Add")
                            .on("click", function () {
                                showDetailsDialog("Agregar", {});
                            });
                }
            }
        ]
    });

    $("#detailsDialog").dialog({
        autoOpen: false,
        width: 400,
        close: function () {
            $("#detailsForm").validate().resetForm();
            $("#detailsForm").find(".error").removeClass("error");
        }
    });

    $("#detailsForm").validate({
        rules: {
            nombre1: "required",
            apellido_paterno: "required",
            apellido_materno: "required",
            estatus: "required",
            rol_id: "required",
            usuario: {
              required: true,
              minlength: 6
            },            
            pass: {
              required: true,
              minlength: 6
            },            
            correo_electronico: {
              required: true,
              email: true
            },            
            pass1: {
                equalTo: "#pass"
            }            
        },
        messages: {
            nombre1: "requerido",
            apellido_paterno: "requerido",
            apellido_materno: "requerido",
            estatus: "requerido",
            rol_id: "requerido",
            usuario: {
              required: "requerido",
              minlength: jQuery.validator.format("Al menos debe tener {0} caracteres")
            },
            pass: {
              required: "requerido",
              minlength: jQuery.validator.format("Al menos debe tener {0} caracteres")
            },
            pass1: {
                equalTo: "Las contraseñas deben coincidir"
            },            
            correo_electronico: {
              required: "Requerido",
              email: "Formato para email debe ser del tipo name@domain.com"
            }            
        },
        submitHandler: function () {
            formSubmitHandler();
        }
    });

    var formSubmitHandler = $.noop;

    var showDetailsDialog = function (dialogType, client) {
        $("#usuario_id").val(client.usuario_id);
        $("#nombre1").val(client.nombre1);
        $("#nombre2").val(client.nombre2);
        $("#apellido_paterno").val(client.apellido_paterno);
        $("#apellido_materno").val(client.apellido_materno);
        $("#correo_electronico").val(client.correo_electronico);
        $("#telefono").val(client.telefono);
        $("#estatus").val(client.estatus);
        $("#usuario").val(client.usuario);
        $("#pass").val(client.contrasena);
        $("#pass1").val(client.contrasena);        
        $("#rol_id").val(client.rol_id);        


        formSubmitHandler = function () {
            saveClient(client, dialogType === "Agregar");
        };

        $("#detailsDialog").dialog("option", "title", dialogType + " Usuario")
                .dialog("open");
    };

    var saveClient = function (client, isNew) {

        var data = {
            usuario_id: $("#usuario_id").val(),
            nombre1: $("#nombre1").val(),
            nombre2: $("#nombre2").val(),
            apellido_paterno: $("#apellido_paterno").val(),
            apellido_materno: $("#apellido_materno").val(),
            correo_electronico: $("#correo_electronico").val(),
            telefono: $("#telefono").val(),
            estatus: $("#estatus").val(),
            usuario: $("#usuario").val(),
            contrasena: $("#pass").val(),
            rol_id: $("#rol_id").val()
        };
        
        if (client.pass !== client.pass1) {
            alert("las contraseñas ingresadas deben ser iguales");
        }

        $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", data);

        $("#detailsDialog").dialog("close");
    };
});

var url = "/SGDADMIN/administradorusuarios/";
var clients;
var controllers = {
    loadData: function () {
        var deferred = $.Deferred();

        $.ajax({
            url: url + "userlist",
            success: function (data) {
                deferred.resolve(data);
            }
        });

        return deferred.promise();
    },

    insertItem: function (item) {
        return $.ajax({
            type: "POST",
            url: url + "registro",
            dataType: 'json',
            contentType: 'application/json',            
            data: JSON.stringify(item),
            success: function (data) {
                return data;
            }
        });
    },

    updateItem: function (item) {
        
        return $.ajax({
            type: "PUT",
            url: url + "updateUser",
            dataType: 'json',
            contentType: 'application/json',            
            data: JSON.stringify(item),
            success: function (data) {
                return data;
            }
        });
    }
};
