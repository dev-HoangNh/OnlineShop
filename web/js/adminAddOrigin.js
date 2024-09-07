function deleteOrigin(id) {
    if (confirm("Are you sure to delete origin with id: " + id)) {
        window.location = "deleteOrigin?id=" + id;
    }
}

$(document).ready(function () {
    $(document).on("click", "#origin_btn", function () {
        $("#origin_form").validate({
            rules: {
                origin: {
                    required: true
                }
            },
            messages: {
                origin: {
                    required: "Enter origin"
                }
            }
        });
        $("#origin_form").valid();
    });
});