function deleteSize(id, productId) {
    if (confirm("Are you sure to delete size & price with id: " + id)) {
        window.location = "deleteSize?id=" + id + "&productId=" + productId;
    }
}

$(document).ready(function () {
    $(document).on("click", "#size_btn", function () {
        $("#size_form").validate({
            rules: {
                size: {
                    required: true
                },
                price: {
                    required: true
                }
            },
            messages: {
                size: {
                    required: "Enter size"
                },
                price: {
                    required: "Enter price"
                }
            }
        });
        $("#size_form").valid();
    });
});