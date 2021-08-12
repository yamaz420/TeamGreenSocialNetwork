// Wait for the DOM to be ready
$(function () {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  // $("form[name='signup'], form[name='signin'], form[name='edit']").validate({
  $("form").validate({
    // errorElement: "span",
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      username: "required",
      email: {
        // required: true,
        // Specify that email should be validated
        // by the built-in "email" rule
        email: true
      },
      password: {
        required: true,
        minlength: 5
      },
      password_confirm: {
        // required: true,
        // minlength: 5,
        equalTo: "#password"
      }
    },
    // Specify validation error messages
    messages: {
      firstname: "Please enter your First Name",
      lastname: "Please enter your Last Name",
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 5 characters long"
      },
      password_confirm: {
        // required: "Please provide a password",
        // minlength: "Your password must be at least 5 characters long",
        equalTo: "Please enter the same password as above"
      },
      email: "Please enter a valid email address"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function (form) {
      form.submit();
    }
  });
});
