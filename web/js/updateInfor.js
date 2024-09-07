$(document).ready(function () {
    $(document).on("click", "#updateProfile_btn", function () {
        $("#updateProfile_form").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 4,
                    maxlength: 16
                },
                fullName: {
                    required: true,
                    minlength: 10,
                    maxlength: 40
                },
                address: {
                    required: true
                },
                phoneNumber: {
                   required: true,
                    minlength: 10,
                    maxlength: 11
                }
            },
            messages: {
                username: {
                    required: "Enter your user name",
                    minlength: "You must input 4 characters at least",
                    maxlength: "Your user name must less than 16 characters"
                },
                fullName: {
                    required: "Enter your full name",
                    minlength: "You must input 10 characters at least",
                    maxlength: "Your full name must less than 40 characters"
                },
                address: {
                    required: "Enter your address"
                },
                phoneNumber: {
                    required: "Enter your phoneNumber",
                    minlength: "You must input 10 characters at least",
                    maxlength: "Your must input 11 characters at least"
                }
            }
        });
        $("#updateProfile_form").valid();
    });
});