function validateForm() {
    var dateField = document.getElementById("date-field");
    var formSpanName = "form-err-span";
    var errorMessage = 'Not all fields filled correctly!';
    var messageColorName = 'red';

    if (validateDateField(dateField) && checkPassword()) {
        return true;
    } else {
        setSpanText(formSpanName, errorMessage, messageColorName);
        return false;
    }
}

/* функция для проверки валидности заданного поля */
function checkField(field) {
    if (field.checkValidity() == false) {
        field.style.backgroundColor = "rgba(255,0,0,0.3)";
        return false;
    } else {
        field.style.backgroundColor = "inherit";
        return true;
    }
}

/* функция для задания текста и цвета для него определенному span */
function setSpanText(spanName, text, colorName) {
    document.getElementById(spanName).innerText = text;
    document.getElementById(spanName).style.color = colorName;
}

function checkLoginField(loginField) {
    var loginSpanName = 'login-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (!checkField(loginField)) {
        errorMessage = loginField.title;
        messageColor = 'red';
    }
    setSpanText(loginSpanName, errorMessage, messageColor);
}

function checkFirstNameField(firstNameField) {
    var firstNameSpanName = 'first-name-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (!checkField(firstNameField)) {
        errorMessage = firstNameField.title;
        messageColor = 'red';
    }
    setSpanText(firstNameSpanName, errorMessage, messageColor);
}

function checkLastNameField(lastNameField) {
    var lastNameSpanName = 'last-name-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (!checkField(lastNameField)) {
        errorMessage = lastNameField.title;
        messageColor = 'red';
    }
    setSpanText(lastNameSpanName, errorMessage, messageColor);
}

function checkEmailField(emailField) {
    var emailFieldSpanName = 'email-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (!checkField(emailField)) {
        errorMessage = emailField.placeholder;
        messageColor = 'red';
    }
    setSpanText(emailFieldSpanName, errorMessage, messageColor);
}

function checkField(field) {
    var defaultBackGroundColorName = "inherit";
    var errorBackgroundColorName = "rgba(255,0,0,0.3)";

    if (field.checkValidity() == false) {
        field.style.backgroundColor = errorBackgroundColorName;
        return false;
    } else {
        field.style.backgroundColor = defaultBackGroundColorName;
        return true;
    }
}

function checkPasswordField(passwordField) {
    var backgroundColorName = "inherit";
    var passwordSpanName = 'password-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (passwordField.checkValidity() == false) {
        errorMessage = passwordField.title;
        messageColor = 'red';
        backgroundColorName = "rgba(255,0,0,0.3)";
    }
    passwordField.style.backgroundColor = backgroundColorName;
    setSpanText(passwordSpanName, errorMessage, messageColor);
    resetRepPassword();
}

/* функция сбрасывает значения связанные с полем повторного ввода пароля */
function resetRepPassword() {
    var repPasswordField = document.getElementById("rep-password-field");
    repPasswordField.value = '';
    repPasswordField.style.backgroundColor = 'inherit';
    setSpanText("rep-password-span", '', 'inherit');
}

/* функция проверки обоих паролей */
function checkPassword() {
    var passwordField = document.getElementById("password-field");
    var repPasswordField = document.getElementById("rep-password-field");
    var repPasswordSpanName = 'rep-password-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (passwordField.value === repPasswordField.value) {
        passwordField.style.backgroundColor = 'inherit';
        repPasswordField.style.backgroundColor = 'inherit';
        setSpanText(repPasswordSpanName, errorMessage, messageColor);
        return true;
    } else {
        errorMessage = 'Passwords do not match';
        messageColor = 'red';
        passwordField.style.backgroundColor = 'rgba(255,0,0,0.3)';
        repPasswordField.style.backgroundColor = 'rgba(255,0,0,0.3)';
        setSpanText(repPasswordSpanName, errorMessage, messageColor);
        return false;
    }
}

function checkSecAnswerField(secAnswerField) {
    var secAnswerSpanName = 'secanswer-span';
    var errorMessage = '';
    var messageColor = 'inherit';

    if (!checkField(secAnswerField)) {
        errorMessage = secAnswerField.title;
        messageColor = 'red';
    }
    setSpanText(secAnswerSpanName, errorMessage, messageColor);
}

function showOptionalBlock(addButton) {
    document.getElementById("optional").style.display = 'block';
    document.getElementById("optional-email-field").required = true;
    addButton.style.visibility = 'hidden';
}

