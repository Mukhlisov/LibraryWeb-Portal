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