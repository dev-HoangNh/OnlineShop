function deleteCategory(id) {
    if (confirm("Are you sure to delete category with id: " + id)) {
        window.location = "deleteCategory?id=" + id;
    }
}

$(document).ready(function () {
    $(document).on("click", "#category_btn", function () {
        $("#category_form").validate({
            rules: {
                category: {
                    required: true
                }
            },
            messages: {
                category: {
                    required: "Enter origin"
                }
            }
        });
        $("#category_form").valid();
    });
});