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
                addButton: false,
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
            //$("#detailsForm").validate().resetForm();
            $("#detailsForm").find(".error").removeClass("error");
        }
    });

//    $("#detailsForm").validate({
//        rules: {
//            name: "required",
//            age: {required: true, range: [18, 150]},
//            address: {required: true, minlength: 10},
//            country: "required"
//        },
//        messages: {
//            name: "Please enter name",
//            age: "Please enter valid age",
//            address: "Please enter address (more than 10 chars)",
//            country: "Please select country"
//        },
//        submitHandler: function () {
//            formSubmitHandler();
//        }
//    });

    var formSubmitHandler = $.noop;

    var showDetailsDialog = function (dialogType, client) {
        $("#usuarioid").val(client.usuarioid);
        $("#nombre1").val(client.nombre1);
        $("#nombre2").val(client.nombre2);
        $("#apellido_paterno").val(client.apellido_paterno);
        $("#apellido_materno").val(client.apellido_materno);
        $("#correo_electronico").val(client.correo_electronico);
        $("#telefono").val(client.telefono);
        $("#estatus").val(client.estatus);
        $("#usuario").val(client.usuario);

      
        formSubmitHandler = function () {
            saveClient(client, dialogType === "Agregar");
        };

        $("#detailsDialog").dialog("option", "title", dialogType + " Usuario")
                .dialog("open");
    };

    var saveClient = function (client, isNew) {
        $.extend(client, {
            usuario_id: $("#usuario_id").val(client.usuario_id),
            nombre1: $("#nombre1").val(client.nombre1),
            nombre2: $("#nombre2").val(client.nombre2),
            apellido_paterno: $("#apellido_paterno").val(client.apellido_paterno),
            apellido_materno: $("#apellido_materno").val(client.apellido_materno),
            correo_electronico: $("#correo_electronico").val(client.correo_electronico),
            telefono: $("#telefono").val(client.telefono),
            estatus: $("#estatus").val(client.estatus),
            usuario: $("#usuario").val(client.usuario),          
            pass: $("#pass").val(client.pass),          
            pass1: $("#pass1").val(client.pass1)          
        });
        
        if(client.pass !==client.pass1){
            alert("las contraseñas ingresadas deben ser iguales");
        }

        $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", client);

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
            url: "/items",
            data: item
        });
    },

    updateItem: function (item) {
        return $.ajax({
            type: "PUT",
            url: "/updateUser",
            data: item,
            success: function (data) {
                return data;
            }            
        });
    },

    deleteItem: function (item) {
        return $.ajax({
            type: "DELETE",
            url: "/items",
            data: item
        });
    },
};
