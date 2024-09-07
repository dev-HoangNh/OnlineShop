$(document).ready(function() {
    $(document).on("click", "#register_btn", function() {
        $("#register_form").validate({
            rules: {
                email: {
                    required: true
                },
                username: {
                    required: true,
                    minlength: 4,
                    maxlength: 16
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 16
                },
                rePassword: {
                    required: true,
                    minlength: 8,
                    maxlength: 16,
                    equalTo: "#password"
                }
            },
            messages: {
                email: {
                    required: "Enter your email"
                },
                username: {
                    required: "Enter your user name",
                    minlength: "You must input 4 characters at least",
                    maxlength: "Your user name must less than 16 characters"
                },
                password: {
                    required: "Enter your password",
                    minlength: "You must input 8 characters at least",
                    maxlength: "Your password must less than 16 characters"
                },
                rePassword: {
                    required: "Enter your re-password",
                    minlength: "You must input 8 characters at least",
                    maxlength: "Your re-password must less than 16 characters",
                    equalTo: "Your re-password not match"
                }
            }
        });
        $("#register_form").valid();
    });
});