window.onload = function() {
    document.querySelector('input[type=file]').addEventListener('change', function (e) {
        var maxFileSize = 5242880; // 5MB
        var allowedFileTypes = ['image/jpeg', 'image/png'];
        if (this.files[0].size > maxFileSize) {
            alert('Файл слишком большой! Максимальный размер файла - 5MB.');
            this.value = '';
        } else if (!allowedFileTypes.includes(this.files[0].type)) {
            alert('Недопустимый тип файла! Разрешены только файлы .jpg, .jpeg, .png.');
            this.value = '';
        }
    }, false);
}

document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('bookForm');
    const quantity = document.getElementById('quantity');
    const year = document.getElementById('year');
    const quantityError = document.getElementById('quantityValueError');
    const yearError = document.getElementById('yearValueError');
    const currYear = new Date().getFullYear();

    function yearValid() {
        if (year.value >= 1600 && year.value <= currYear){
            yearError.style.display = 'none';
            return true;
        } else {
            yearError.style.display = 'block';
            return false;
        }
    }

    function quantityValid(){
        if (quantity.value >= 0){
            quantityError.style.display = 'none';
            return true;
        } else {
            quantityError.style.display = 'block';
            return false;
        }
    }

    year.addEventListener('input', yearValid);
    quantity.addEventListener('input', quantityValid);

    form.addEventListener('submit', function(event) {
        if (!yearValid() || !quantityValid()) {
            event.preventDefault();
        }
    });
});