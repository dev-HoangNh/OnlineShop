$(document).ready(function () {
    $(document).on("click", "#checkout_btn", function () {
        $("#checkout_form").validate({
            rules: {
                fullName: {
                    required: true
                },
                email: {
                    required: true
                },
                phoneNumber: {
                    required: true
                }
            },
            messages: {
                fullName: {
                    required: "Enter full name"
                },
                email: {
                    required: "Enter email"
                },
                phoneNumber: {
                    required: "Enter phone number"
                }
            }
        });
        $("#checkout_form").valid();
    });
});