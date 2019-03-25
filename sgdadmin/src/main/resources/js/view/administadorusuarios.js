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
            $("#detailsForm").validate().resetForm();
            $("#detailsForm").find(".error").removeClass("error");
        }
    });

    $("#detailsForm").validate({
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


        formSubmitHandler = function () {
            saveClient(client, dialogType === "Agregar");
        };

        $("#detailsDialog").dialog("option", "title", dialogType + " Usuario")
                .dialog("open");
    };

    var saveClient = function (client, isNew) {
        $.extend(client, {
            Usuario_id: $("#usuario_id").val(),
            Nombre1: $("#nombre1").val(),
            Nombre2: $("#nombre2").val(),
            Apellido_paterno: $("#apellido_paterno").val(),
            Apellido_materno: $("#apellido_materno").val(),
            Correo_electronico: $("#correo_electronico").val(),
            Telefono: $("#telefono").val(),
            Estatus: $("#estatus").val(),
            Usuario: $("#usuario").val(),
            Contrasena: $("#pass").val()
        });

        var test = {
            Usuario_id: $("#usuario_id").val(),
            Nombre1: $("#nombre1").val(),
            Nombre2: $("#nombre2").val(),
            Apellido_paterno: $("#apellido_paterno").val(),
            Apellido_materno: $("#apellido_materno").val(),
            Correo_electronico: $("#correo_electronico").val(),
            Telefono: $("#telefono").val(),
            Estatus: $("#estatus").val(),
            Usuario: $("#usuario").val(),
            Contrasena: $("#pass").val()
        };

        if (client.pass !== client.pass1) {
            alert("las contraseñas ingresadas deben ser iguales");
        }

        $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", test);

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
            url: "registro",
            data: item
        });
    },

    updateItem: function (item) {
        
        var test = '{"usuario_id":1,"nombre1":"Allan1","nombre2":"","apellido_paterno":"Flores","apellido_materno":"Rojas","correo_electronico":"correo2@mail.com","telefono":"5464654654654","estatus":"1","usuario":"allan","contrasena":"$2a$10$Xt3gTH/P0VzPxw/bwPfl2OtnwZMg55bA.1lUm.xTkrQwp44i001Qq","rol_id":1}';
        return $.ajax({
            type: "PUT",
            url: url + "updateUser/1",
            //data: test,,
            data: JSON.stringify(item),
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
