function validateRussianInput(input) {
    input.value = input.value.replace(/[^А-Яа-я]/g, '');
}

function validatePhoneNumber(input){
    input.value = input.value.replace(/[^0-9]/g, '')
}

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('regForm');
    const passwordInput = document.getElementById('passwordReg');
    const passwordRepInput = document.getElementById('passwordRegConfirm');
    const phoneNumberInput = document.getElementById('phoneNumber');
    const passwordMatchError = document.getElementById('passwordMatchError');
    const phoneLengthError = document.getElementById('phoneLengthError')

    function checkPasswordMatch() {
        if (passwordInput.value !== passwordRepInput.value) {
            passwordMatchError.style.display = 'block';
            return false;
        } else {
            passwordMatchError.style.display = 'none';
            return true;
        }
    }

    function checkPhoneLength(){

        if (phoneNumberInput.value.length === 11){
            phoneLengthError.style.display = 'none';
            return true;
        } else {
            phoneLengthError.style.display = 'block';
            return false;
        }
    }

    passwordInput.addEventListener('input', checkPasswordMatch);
    passwordRepInput.addEventListener('input', checkPasswordMatch);
    phoneNumberInput.addEventListener('input', checkPhoneLength);

    // Проверка перед отправкой формы
    form.addEventListener('submit', function(event) {
        if (!checkPasswordMatch() || !checkPhoneLength()) {
            event.preventDefault();
        }
    });
});