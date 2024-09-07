$(document).ready(function () {
    $(document).on("click", "#product_btn", function () {
        $("#product_form").validate({
            rules: {
                fruitName: {
                    required: true
                },
                fruitcode: {
                    required: true
                },
                sale: {
                    required: true
                },
                description: {
                    required: true
                },
                image: {
                    required: true
                }
            },
            messages: {
                fruitName: {
                    required: "Enter fruitName"
                },
                fruitcode: {
                    required: "Enter fruitcode"
                },
                sale: {
                    required: "Enter sale"
                },
                description: {
                    required: "Enter description"
                },
                image: {
                    required: "Enter image"
                }
            }
        });
        $("#product_form").valid();
    });
});