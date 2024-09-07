function deleteAddress(id) {
    if (confirm("Are you sure to delete ship address with id: " + id)) {
        window.location = "deleteShippingAddress?id=" + id;
    }
}

$(document).ready(function () {
    $(document).on("click", "#shipping_address_btn", function () {
        $("#shipping_address_form").validate({
            rules: {
                addressName: {
                    required: true
                }
            },
            messages: {
                addressName: {
                    required: "Enter your shipping address"
                }
            }
        });
        $("#shipping_address_form").valid();
    });
});