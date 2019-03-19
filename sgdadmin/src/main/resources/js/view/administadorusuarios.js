//$(document).ready(function () {
//    $("#jsGrid").jsGrid({
//        width: "100%",
//        height: "400px",
//
//        inserting: true,
//        editing: true,
//        sorting: true,
//        paging: true,
//        autoload: true,
//        pageSize: 15,
//        pageButtonCount: 5,        
//        rowClick: function (args) {
//            showDetailsDialog("Edit", args.item);
//        },
//        clients,
//        controller: controllers,
//
//        fields: [
//            {name: "nombre1", type: "text", validate: "required", title: "Nombre"},
//            {name: "nombre2", type: "text", title: "Segundo Nombre"},
//            {name: "apellido_paterno", type: "text", title: "Apellido Paterno"},
//            {name: "apellido_materno", type: "text", title: "Apellido Materno"},
//            {name: "correo_electronico", type: "text", width: "130", title: "Correo Electrónico"},
//            {name: "telefono", type: "text", title: "Teléfono"},
//            {name: "estatus", type: "select", title: "Estatus"},
//            {name: "usuario", type: "text", title: "Usuario"},
//            controls
//        ]
//    });
//});
//
//var controls = {
//    type: "control",
//    editButton: true, // show edit button
//    deleteButton: false, // show delete button
//    clearFilterButton: true, // show clear filter button
//    modeSwitchButton: true, // show switching filtering/inserting button
//
//    align: "center", // center content alignment
//    width: 50, // default column width is 50px
//    filtering: false, // disable filtering for column
//    inserting: false, // disable inserting for column
//    editing: false, // disable editing for column
//    sorting: false, // disable sorting for column
//
//    searchModeButtonTooltip: "Switch to searching", // tooltip of switching filtering/inserting button in inserting mode
//    insertModeButtonTooltip: "Switch to inserting", // tooltip of switching filtering/inserting button in filtering mode
//    editButtonTooltip: "Edit", // tooltip of edit item button
//    deleteButtonTooltip: "Delete", // tooltip of delete item button
//    searchButtonTooltip: "Search", // tooltip of search button
//    clearFilterButtonTooltip: "Clear filter", // tooltip of clear filter button
//    insertButtonTooltip: "Insert", // tooltip of insert button
//    updateButtonTooltip: "Update", // tooltip of update item button
//    cancelEditButtonTooltip: "Cancel edit" // tooltip of cancel editing button
//};
//var url = "/SGDADMIN/";
//var clients;
//var controllers = {
//    loadData: function () {
//        var deferred = $.Deferred();
//
//        $.ajax({
//            url: url + "jsonlist",
//            success: function (data) {
//                deferred.resolve(data);
//            }
//        });
//
//        return deferred.promise();
//    },
//
//    insertItem: function (item) {
//        return $.ajax({
//            type: "POST",
//            url: "/items",
//            data: item
//        });
//    },
//
//    updateItem: function (item) {
//        return $.ajax({
//            type: "PUT",
//            url: "/items",
//            data: item
//        });
//    },
//
//    deleteItem: function (item) {
//        return $.ajax({
//            type: "DELETE",
//            url: "/items",
//            data: item
//        });
//    },
//};
//
//var countries = [
//    {Name: "", Id: 0},
//    {Name: "United States", Id: 1},
//    {Name: "Canada", Id: 2},
//    {Name: "United Kingdom", Id: 3}
//];
//
//$("#detailsDialog").dialog({
//    autoOpen: false,
//    width: 400,
//    close: function () {
//        $("#detailsForm").validate().resetForm();
//        $("#detailsForm").find(".error").removeClass("error");
//    }
//});
//
//$("#detailsForm").validate({
//    rules: {
//        name: "required",
//        age: {required: true, range: [18, 150]},
//        address: {required: true, minlength: 10},
//        country: "required"
//    },
//    messages: {
//        name: "Please enter name",
//        age: "Please enter valid age",
//        address: "Please enter address (more than 10 chars)",
//        country: "Please select country"
//    },
//    submitHandler: function () {
//        formSubmitHandler();
//    }
//});
//
//var formSubmitHandler = $.noop;
//
//var showDetailsDialog = function (dialogType, client) {
//    $("#name").val(client.Name);
//    $("#age").val(client.Age);
//    $("#address").val(client.Address);
//    $("#country").val(client.Country);
//    $("#married").prop("checked", client.Married);
//
//    formSubmitHandler = function () {
//        saveClient(client, dialogType === "Add");
//    };
//
//    $("#detailsDialog").dialog("option", "title", dialogType + " Client")
//            .dialog("open");
//};
//
//var saveClient = function (client, isNew) {
//    $.extend(client, {
//        Name: $("#name").val(),
//        Age: parseInt($("#age").val(), 10),
//        Address: $("#address").val(),
//        Country: parseInt($("#country").val(), 10),
//        Married: $("#married").is(":checked")
//    });
//
//    $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", client);
//
//    $("#detailsDialog").dialog("close");
//};