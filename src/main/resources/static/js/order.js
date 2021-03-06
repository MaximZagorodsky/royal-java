var loadingAddressCount = 0;
var zipFromCount = 0;
var loadingAddresses;
var loadingZip;
var unloadingAddressCount = 0;
var zipToCount = 0;
var unloadingAddresses;
var unloadingZip;


//Срабатывает, когда загрзился весь документ HTML
$(document).ready(
    function () {
        $("#submitAll").click(
            submitAllForms);
        //window.onbeforeunload = confirmExit;
        $("#moveDate").change(showModel);
        $("#storageDate").change(showModel);
        $("#priceForEachHour").change(changeTotalSumPerHours);
        $("#apply").click(refreshPaymentDetails);
        addressButtonListen();
        changePackingMaterials();
    }
);


//Срабатывает, когда щелкают мшью по кнопкам с id = addLoadAddress и submitOrder
var addressButtonListen = function () {
    $("#addLoadAddress").click(addLoadAddressField);
    $("#addUnLoadAddress").click(addUnLoadAddressField);
    $("#submitOrder").click(submitOrder);
}

// Добавляет поле с адресом и zip кодом

var addLoadAddressField = function () {
    var nextLoadAddressIndex = loadingAddressCount + 1;
    var nextZipFrom = zipFromCount + 1;

    loadingAddressCount += 1;
    zipFromCount += 1;
    var addressField = "<input type='text' class= 'form-control' placeHolder='Address from' id='loadAddressDiv" + nextLoadAddressIndex + "'" + "/>"
    var zipField = "<input  style='clear:both;width: 39.5%;' type='text' placeholder='Zip from' class='form-control' id='zipFrom" + nextZipFrom + "'" + " value=''/>"
    $(zipField).appendTo("#zipFromDiv");
    $(addressField).appendTo("#loadAddressDiv");
}
var addUnLoadAddressField = function () {
    var nextUnLoadAddressIndex = unloadingAddressCount + 1;
    var nextZipTo = zipToCount + 1;

    unloadingAddressCount += 1;
    zipToCount += 1;
    var addressField = "<input type='text' class= 'form-control' placeHolder='Address to' id='unloadAddressDiv" + nextUnLoadAddressIndex + "'" + "/>"
    var zipField = "<input  style='clear:both;width: 39.5%;' type='text' placeholder='Zip to' class='form-control' id='zipFrom" + nextZipTo + "'" + " value=''/>"
    $(zipField).appendTo("#zipToDiv");
    $(addressField).appendTo("#unloadAddressDiv");
}
//Отпраляет форму с id = OrderForm на сервер!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
var submitOrder = function () {
    collectAddressIdsNames();
    alert("Теперь строка выглядит так(сначала адрес, потом соответствующие им индексы):" + loadingAddresses + ";" + loadingZip);
    $("#loadingAddresses").val(loadingAddresses + ";" + loadingZip);
    $("#unloadingAddresses").val(unloadingAddresses + ";" + unloadingZip);
    $("#FormOrder").submit();
}

// В цикле переберает все поля с zip кодом
var cycleZip = function () {
    var zip = '';
    $(".zipCode").each(
        function () {
            if (zip != '') {
                zip = zip + "," + $(this).val();
            } else {
                zip = $(this).val();
            }
        }
    )
    loadingZip = zip;
}

// В цикле переберает все поля с адресом кодом
var cycle = function () {
    var addresses = '';
    $(".loadAddress").each(
        function () {
            if (addresses != '') {
                addresses = addresses + "," + $(this).val();
            } else {
                addresses = $(this).val();
            }
        }
    )
    loadingAddresses = addresses;
}
var cycleZip2 = function () {
    var zip = '';
    $(".zipCode").each(
        function () {
            if (zip != '') {
                zip = zip + "," + $(this).val();
            } else {
                zip = $(this).val();
            }
        }
    )
    unloadingZip = zip;
}
var cycle2 = function () {
    var addresses = '';
    $(".unloadAddress").each(
        function () {
            if (addresses != '') {
                addresses = addresses + "," + $(this).val();
            } else {
                addresses = $(this).val();
            }
        }
    )
    unloadingAddresses = addresses;
}


//Запускает циклы обработки полей с адресом и zip кодом
var collectAddressIdsNames = function () {
    $("loadAddressDiv").on("change", ".loadAddress", cycle());
    $("loadAddressDiv").on("change", ".zipCode", cycleZip());
    $("unloadAddressDiv").on("change", ".unloadAddress", cycle2());
    $("unloadAddressDiv").on("change", ".zipCode", cycleZip2());
}


//Ожидает изменения в элементе с выбором компании и автоматически меняет значение в PackagingMaterils
var changeCompanyListener = function () {
    $("#company").change(changePackingMaterials);
}

//Меняет значение в PackagingMaterials в зависимости от значения в company
var changePackingMaterials = function () {
    var currentCompany = $("#company").val();
    if (currentCompany == "ROYALMOVING") {
        $("#packingMaterials").val("FREE");
    }
    else {
        $("#packingMaterials").val("EXTRA");
    }
}

//Показывает модальное окно
var showModel = function (id) {
    $("#myModal").modal("show");
    $('.ui-state-default').click(function () {
        console.log(777);
    });
};

var confirmExit = function () {
    alert("confirm exit is being called");
    return false;
}


$(function () {
    // IMPORTANT: Fill in your client key
    var clientKey = "MsjAnQ5wxOADwVjaOmPLYi4ul9M5yFck8vVvm11TPCxlxQwldl7bEbF0ls5T8ieg";

    var cache = {};
    var container = $("#example2");
    var errorDiv = container.find("div.text-error");

    /** Handle successful response */
    function handleResp(data) {
        // Check for error
        if (data.error_msg)
            errorDiv.text(data.error_msg);
        else if ("distance" in data) {
            // Show div
            container.find("div.distance").show()
                // Set distance
                .find("span.distance").text(data.distance + " miles");
        }
    }

    // Set up event handlers
    container.find("input[name='zipcode1'],input[name='zipcode2']").on("keyup change", function () {
        // Get zip code
        var zipcode1 = $("input[name='zipcode1']").val().substring(0, 5);
        var zipcode2 = $("input[name='zipcode2']").val().substring(0, 5);
        if (zipcode1.length == 5 && /^[0-9]+$/.test(zipcode1) && zipcode2.length == 5 && /^[0-9]+$/.test(zipcode2)) {
            // Clear error
            errorDiv.empty();

            // Check cache
            var cacheKey = zipcode1 + '-' + zipcode2;
            if (cacheKey in cache) {
                handleResp(cache[cacheKey]);
            }
            else {
                // Build url
                var url = "https://www.zipcodeapi.com/rest/" + clientKey + "/distance.json/" + zipcode1 + "/" + zipcode2 + "/mile";

                // Make AJAX request
                $.ajax({
                    "url": url,
                    "dataType": "json"
                }).done(function (data) {
                    handleResp(data);

                    // Store in cache
                    cache[cacheKey] = data;
                }).fail(function (data) {
                    if (data.responseText && (json = $.parseJSON(data.responseText))) {
                        // Store in cache
                        cache[cacheKey] = json;

                        // Check for error
                        if (json.error_msg)
                            errorDiv.text(json.error_msg);
                    }
                    else
                        errorDiv.text('Request failed.');
                });
            }
        }
    }).trigger("change");
});

var submitForm = function () {
    $("#orderForm").submit;
    $("#paymentsDetailsForm").submit;
}

var submitAllForms = function () {
    var paymentForm = $("#paymentsDetailsForm").serialize();
    var orderForm = $("#orderForm").serialize();
    $.ajax({
        method: "post",
        url: "/order",
        dataType: 'json',
        data: {paymentsDetailsForm: JSON.stringify(paymentForm), orderForm: JSON.stringify(orderForm)},
        success: alert(JSON.stringify(orderForm))
    });
};

$('#sentButton').click(function () {console.log('sentButton pressed!')});