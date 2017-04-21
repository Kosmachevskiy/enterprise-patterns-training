$(function () {
    console.log("init...")
    loadAllProducts();
    initCategoryFilter();

    $("#search").keyup(search);
    $("#filter-button").click(search);
    $("#price-from").keyup(search);
    $("#price-to").keyup(search);
});

function handleData(data) {
    var table = document.getElementById("products");

    // Reset index and table//
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }

    data.forEach(function (product, i, arr) {
        var row = table.insertRow();
        row.insertCell(0).innerText = product.category;
        row.insertCell(1).innerText = product.name;
        row.insertCell(2).innerText = product.price;
        row.insertCell(3).innerText = product.count;
    })
}
function loadAllProducts() {
    $.getJSON("/product", function (data) {
        handleData(data);
    });
}

function initCategoryFilter() {

    $.getJSON("/category", function (data) {
        $("#categories").empty();

        data.forEach(function (category) {
            var child =
                "<div class='checkbox enabled'> <label>" +
                "<input type='checkbox' checked='true' value='" + category.id + "'/>" + category.name +
                "</label> </div>"
            $("#categories").append(child);
        });
        $("#categories label input").change(search);
    });
}

function search() {
    var filter = {
        priceFrom: $("#price-from").val(),
        priceTo: $("#price-to").val(),
        categories: [],
        nameLike: $('#search').val()
    }

    var checkedCats = $("#categories label input:checked");

    for (var i = 0; i < checkedCats.length; i++) {
        filter.categories.push(checkedCats[i].value);
    }
    var data = JSON.stringify(filter);

    $.ajax({
        url: "/product/byFilter",
        type: "POST",
        data: data,
        contentType: "application/json",
        statusCode: {
            200: function (data) {
                handleData(data);
            }
        }
    });

}