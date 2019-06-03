/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",
        inserting: false,
        //filtering: true,
        editing: false,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 5,
        pageButtonCount: 5,
        pagerFormat: "Pag: {first} {prev} {pages} {next} {last}    {pageIndex} de {pageCount}",
        pagePrevText: "Anterior",
        pageNextText: "Siguiente",
        pageFirstText: "Primero",
        pageLastText: "Último",
        pageNavigatorNextText: "...",
        pageNavigatorPrevText: "...",
        loadMessage: "Por favor espere ...",
        rowClick: function (args) {
            showDetailsDialog("Editar", args.item);
            $("#deletebtn").show("slow");
        },
        clients,
        controller: controllers,

        fields: [            
            {name: "nombre", type: "text", validate: "required", title: "Nombre"},
            {name: "cargo", type: "text", title: "Cargo"},
            {name: "vista", type: "text", title: "Lugar donde se muestra"},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                deleteButton: false,
                width: 80,
                headerTemplate: function () {
                    return $("<button>").attr("type", "button").text("Agregar")
                            .on("click", function () {
                                showDetailsDialog("Agregar", {});
                                $("#deletebtn").hide(1500);
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
            nombre:{
                required:true,
                maxlength:150
            },
            cargo: {
                required:true,
                maxlength:100
            },
            vista: "required"        
        },
        messages: {
            nombre: {
                required:"requerido",
                maxlength:jQuery.validator.format("Longitud maxima es de: {0} caracteres")
            },
            cargo: {
                required:"requerido",
                maxlength:jQuery.validator.format("Longitud maxima es de: {0} caracteres")
            },
            vista: "requerido"        
        },
        submitHandler: function () {
            formSubmitHandler();
        }
    });

    var formSubmitHandler = $.noop;

    var showDetailsDialog = function (dialogType, client) {
        $("#nombreid").val(client.nombreid);
        $("#nombre").val(client.nombre);
        $("#cargo").val(client.cargo);
        $("#vista").val(client.vista === 'Consejo de Administración' ? 'CA' : client.vista === 'Comité de Engorda' ? 'CE' : client.vista === 'Comité de Inversiones' ? 'CI' : client.vista === 'Comité Comercial' ? 'CC' : '');

        $("#deletebtn").on("click", function() {
            $("#jsGrid").jsGrid("deleteItem", client);
            $("#detailsDialog").dialog("close");
        });

        formSubmitHandler = function () {            
            saveClient(client, dialogType === "Agregar");
            //location.reload(true);
        };

        $("#detailsDialog").dialog("option", "title", dialogType + " Nombre y Cargo")
            .dialog("open");
    };

    var saveClient = function (client, isNew) {

        var data = {
            nombreid: $("#nombreid").val(),
            nombre: $("#nombre").val(),
            cargo: $("#cargo").val(),
            vista: $("#vista").val()
        };

        $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", data);

        $("#detailsDialog").dialog("close");
    };
});

var url = "/SGDADMIN/editarnombres/";
var clients;
var dataGrid;
var controllers = {
    loadData: function (filter) {
        if (dataGrid) {
            return $.grep(dataGrid, function (client) {
                return (!filter.nombre || client.nombre.indexOf(filter.nombre) > -1)
                    && (!filter.cargo || client.cargo.indexOf(filter.cargo) > -1)
                    && (!filter.vista || client.vista.indexOf(filter.vista) > -1)
            });
        }
        console.log("filter:" + filter);
        var deferred = $.Deferred();

        $.ajax({
            url: url + "listanombres",
            success: function (data) {
                $.each(data, function (k, v) {
                    v.vista = v.vista === 'CA' ? 'Consejo de Administración' : v.vista === 'CE' ? 'Comité de Engorda' : v.vista === 'CI' ? 'Comité de Inversiones' : v.vista === 'CC' ? 'Comité Comercial' : '';
                });
                deferred.resolve(data);
            }
        });

        return deferred.promise();
    },

    insertItem: function (item) {
        return $.ajax({
            type: "POST",
            url: url + "registronombre",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(item),
            success: function (data) {
                $("#jsGrid").jsGrid("loadData");
                return data;
            }
        });        
    },

    deleteItem: function (item) {
        return $.ajax({
            type: "PUT",
            url: url + "deletenombre",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(item),
            success: function (data) {
                $("#jsGrid").jsGrid("loadData");
                return data;
            }
        });
    },
    
    updateItem: function (item) {
        return $.ajax({
            type: "PUT",
            url: url + "updatenombre",
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(item),
            success: function (data) {
                $("#jsGrid").jsGrid("loadData");
                return data;
            }
        });
    }
};