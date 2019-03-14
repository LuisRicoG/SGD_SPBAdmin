$(document).ready(function () {
    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",

        inserting: true,
        editing: true,
        sorting: true,
        paging: true,

        data: clients,
        //controller: controllers,

        fields: [
            {name: "Name", type: "text", width: 150, validate: "required"},
            {name: "Age", type: "number", width: 50},
            {name: "Address", type: "text", width: 200},
            {name: "Country", type: "select", items: countries, valueField: "Id", textField: "Name"},
            {name: "Married", type: "checkbox", title: "Is Married", sorting: false},
            controls
        ]
    });

});

var controls = {
    type: "control",
    editButton: true, // show edit button
    deleteButton: false, // show delete button
    clearFilterButton: true, // show clear filter button
    modeSwitchButton: true, // show switching filtering/inserting button

    align: "center", // center content alignment
    width: 50, // default column width is 50px
    filtering: false, // disable filtering for column
    inserting: false, // disable inserting for column
    editing: false, // disable editing for column
    sorting: false, // disable sorting for column

    searchModeButtonTooltip: "Switch to searching", // tooltip of switching filtering/inserting button in inserting mode
    insertModeButtonTooltip: "Switch to inserting", // tooltip of switching filtering/inserting button in filtering mode
    editButtonTooltip: "Edit", // tooltip of edit item button
    deleteButtonTooltip: "Delete", // tooltip of delete item button
    searchButtonTooltip: "Search", // tooltip of search button
    clearFilterButtonTooltip: "Clear filter", // tooltip of clear filter button
    insertButtonTooltip: "Insert", // tooltip of insert button
    updateButtonTooltip: "Update", // tooltip of update item button
    cancelEditButtonTooltip: "Cancel edit" // tooltip of cancel editing button
};
var url = "/SGDADMIN/";
var controllers =
        {             
            loadData: function (filter) {
                var data;
                
                $.ajax({
                    type: "GET",
                    url: url + "jsonlist",
                    data: data,
                    success: success,
                    dataType: dataType
                });
                
                return $.ajax({
                    type: "GET",
                    url: "/items",
                    data: filter
                });
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
                    url: "/items",
                    data: item
                });
            },

            deleteItem: function (item) {
                return $.ajax({
                    type: "DELETE",
                    url: "/items",
                    data: item
                });
            }
        };

var clients = [
    {"Name": "Otto Clay", "Age": 25, "Country": 1, "Address": "Ap #897-1459 Quam Avenue", "Married": false},
    {"Name": "Connor Johnston", "Age": 45, "Country": 2, "Address": "Ap #370-4647 Dis Av.", "Married": true},
    {"Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
    {"Name": "Timothy Henson", "Age": 56, "Country": 1, "Address": "911-5143 Luctus Ave", "Married": true},
    {"Name": "Ramona Benton", "Age": 32, "Country": 3, "Address": "Ap #614-689 Vehicula Street", "Married": false}
];

var countries = [
    {Name: "", Id: 0},
    {Name: "United States", Id: 1},
    {Name: "Canada", Id: 2},
    {Name: "United Kingdom", Id: 3}
];
 