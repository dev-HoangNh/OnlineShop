$(document).ready(function() {
    $(document).on("click", "#login_btn", function() {
        $("#login_form").validate({
            rules: {
                email: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 16
                }
            },
            messages: {
                email: {
                    required: "Enter your email"
                },
                password: {
                    required: "Enter your password",
                    minlength: "You must input 8 characters at least",
                    maxlength: "Your password must less than 16 characters"
                }
            }
        });
        $("#login_form").valid();
    });
});