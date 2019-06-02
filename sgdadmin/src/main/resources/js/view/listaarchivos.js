/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var MyDateField = function (config) {
    jsGrid.Field.call(this, config);
};
var MyDateField2 = function (config) {
    jsGrid.Field.call(this, config);
};

MyDateField.prototype = new jsGrid.Field({

    css: "date-field", // redefine general property 'css'
    align: "center", // redefine general property 'align'

    sorter: function (date1, date2) {
        return new Date(date1) - new Date(date2);
    },
    itemTemplate: function (value) {
        return dateToString(value);
    },
    filterTemplate: function () {
        return this._insertPicker = $("<input onfocusout= onDatePickerClick(this.value,this)>").datepicker({defaultDate: new Date(), dateFormat: "dd/mm/yy", showButtonPanel: true, onSelect: onDatePickerClick, regional: "es"});
    }
});

function onDatePickerClick(dateText, inst) {
    console.log("dateText : " + dateText);
    console.log("inst : " + inst);
    if (inst && inst.input &&  inst.input[0].value === "")
    {
        dateText = "";
    }
    $("#jsGrid").jsGrid("loadData", {fecha: dateText}).done(function () {
        console.log("data loaded");
    });
}

MyDateField2.prototype = new jsGrid.Field({

    css: "date-field", // redefine general property 'css'
    align: "center", // redefine general property 'align'

    sorter: function (date1, date2) {
        return new Date(date1) - new Date(date2);
    },
    itemTemplate: function (value) {
        return dateToString(value);
    },
    filterTemplate: function () {
        return this._insertPicker = $("<input onfocusout= onDatePickerClick2(this.value,this)>").datepicker({defaultDate: new Date(), dateFormat: "dd/mm/yy", showButtonPanel: true, onSelect: onDatePickerClick2, regional: "es"});
    }
});

function onDatePickerClick2(dateText, inst) {
    console.log("dateText : " + dateText);
    console.log("inst : " + inst);
    if (inst && inst.input && inst.input[0].value === "")
    {
        dateText = "";
    }
    $("#jsGrid2").jsGrid("loadData", {fecha: dateText}).done(function () {
        console.log("data loaded");
    });
}

function dateToString(timestampDate) {
    var day = (new Date(timestampDate).getDate()) < 10 ? "0" + (new Date(timestampDate).getDate()) : (new Date(timestampDate).getDate())
    var month = (new Date(timestampDate).getMonth() + 1) < 10 ? "0" + (new Date(timestampDate).getMonth() + 1) : (new Date(timestampDate).getMonth() + 1)
    var fecha = day + "/" + month + "/" + new Date(timestampDate).getFullYear();
    return fecha;
}

jsGrid.fields.date = MyDateField;
jsGrid.fields.date2 = MyDateField2;


$(document).ready(function () {
    $("#jsGrid").jsGrid(configuracion("/SGDADMIN/alldocuments"));
    $("#jsGrid2").jsGrid(configuracion("/SGDADMIN/alldocumentsacumulados"));
});

function configuracion(url, dataGrid) {
    var config = {
        width: "100%",
        height: "400px",
        inserting: false,
        filtering: true,
        editing: false,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 10,
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
            if (args.event.target.type === "button")
            {
                descargar(args.item);
            }
        },
        clients,
        controller: controllers(url)
    };

    config.fields = [];
    config.fields.push(
            {name: "nombre", type: "text", title: "Nombre del Archivo", align: "center"});
    if (url.match(/acumulados/g))
    {
        config.fields.push({name: "descripcion", type: "text", title: "Descripción", align: "center"},
                {name: "fecha", type: "date2", title: "Fecha de Carga", width: 100, align: "center"});

    } else {
        config.fields.push({name: "fecha", type: "date", title: "Fecha de Carga", width: 100, align: "center"});
    }

    config.fields.push(
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
    );
    return config;
}

function descargar(item) {

    console.log(item);
    var url;
    if (item.ruta.match(/acumulados/g)) {
        url = '/SGDADMIN/getDoctoAcumulado?id=' + item.documentoId + '&pantalla=' + item.pantalla;
    } else if (item.ruta.match(/unicos/g)) {
        url = '/SGDADMIN/getDocto?id=' + item.documentoid;
    } else {
        alert("No es posible descargar el archivo")
    }

    console.log(url);
    getDocument(url, item.nombre);

}


var clients;
var dataGrid;
function controllers(url, dataGrid) {
    return {
        loadData: function (filter) {
            if (dataGrid) {
                return $.grep(dataGrid, function (client) {
                    return (!filter.descripcion || client.descripcion.indexOf(filter.descripcion) > -1)
                            && (!filter.nombre || client.nombre.indexOf(filter.nombre) > -1)
                            && (!filter.fecha || dateToString(client.fecha).indexOf(filter.fecha) > -1);
                });
            }
            console.log("filter:" + filter);
            var deferred = $.Deferred();

            $.ajax({
                url: url,
                success: function (data) {
                    dataGrid = data;
                    deferred.resolve(data);
                }
            });

            return deferred.promise();
        }
    };
}
;