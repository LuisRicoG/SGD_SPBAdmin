/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var MyDateField = function(config) {
    jsGrid.Field.call(this, config);
};
 
MyDateField.prototype = new jsGrid.Field({
 
    css: "date-field",            // redefine general property 'css'
    align: "center",              // redefine general property 'align'
 
    myCustomProperty: "foo",      // custom property
 
    sorter: function(date1, date2) {
        return new Date(date1) - new Date(date2);
    },
 
    itemTemplate: function(value) {
        return new Date(value).toDateString();
    },
 
    insertTemplate: function(value) {
        return this._insertPicker = $("<input>").datepicker({ defaultDate: new Date() });
    },
 
    editTemplate: function(value) {
        return this._editPicker = $("<input>").datepicker().datepicker("setDate", new Date(value));
    },
 
    insertValue: function() {
        return this._insertPicker.datepicker("getDate").toISOString();
    },
 
    editValue: function() {
        return this._editPicker.datepicker("getDate").toISOString();
    }
});
 
jsGrid.fields.date = MyDateField;

$(document).ready(function () {
    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",
        inserting: false,
        filtering: true,
        editing: false,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 5,
        pageButtonCount: 5,
        pagerFormat: "Pag: {first} {prev} {pages} {next} {last}    {pageIndex} of {pageCount}",
        pagePrevText: "Anterior",
        pageNextText: "Siguiente",
        pageFirstText: "Primero",
        pageLastText: "Último",
        pageNavigatorNextText: "...",
        pageNavigatorPrevText: "...",
        loadMessage: "Por favor espere ...",
        rowClick: function (args) {
            if (args.event.target.type === "button")
            {
                descargar(args.item);
            }

        },
        clients,
        controller: controllers,

        fields: [            
            {name: "nombre", type: "text", title: "Nombre del Archivo", align: "center"},
            {name: "fecha", type: "date", title: "Fecha de Carga", width: 100, align: "center" },
            {
                type: "control",
                modeSwitchButton: true,
                clearFilterButton: false,
                name: "descarga",
                headerTemplate: "Descarga",
                filterTemplate: "",
                width: 80,
                itemTemplate: function () {
                    return $("<button>").attr("type", "button").text("Descargar");
                }
            }
        ]
    });
});

function descargar(item) {

    console.log(item);
    var url = '/SGD/getDocto?name=' + item.nombre + "&path=" + item.ruta;
    console.log(url);
    getDocument(url, item.nombre);

}
var url = "/SGD/alldocuments";
var clients;
var dataGrid;
var controllers = {
    loadData: function (filter) {
        if (dataGrid) {
            return $.grep(dataGrid, function (client) {
                return (!filter.nombre || client.nombre.indexOf(filter.nombre) > -1)
            });
        }
        console.log("filter:" + filter);
        var deferred = $.Deferred();

        $.ajax({
            url: url,
            success: function (data) {
                $.each(data, function (k, v) {
                    var month = (new Date(v.fecha).getMonth() + 1) < 10 ? "0" + (new Date(v.fecha).getMonth() + 1) : (new Date(v.fecha).getMonth() + 1)
                    var fecha = new Date(v.fecha).getDate() + "/" + month + "/" + new Date(v.fecha).getFullYear();
                    v.fecha = fecha;
                });
                dataGrid = data;
                deferred.resolve(data);
            }
        });

        return deferred.promise();
    }
};