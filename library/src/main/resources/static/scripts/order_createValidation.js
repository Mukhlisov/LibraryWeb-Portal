document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('orderCreateForm');
    const rent_start_date = document.getElementById('rent_start_date');
    const dateError = document.getElementById('dateError');
    const currDate = new Date();

    function checkDate() {
        let lastDate = new Date();
        let selectedDate = new Date(rent_start_date.value);
        lastDate.setDate(lastDate.getDate() + 7);
        if (selectedDate <= currDate || selectedDate > lastDate){
            dateError.style.display = 'block';
            return false;
        } else {
            dateError.style.display = 'none';
            return true;
        }
    }

    rent_start_date.addEventListener('input', checkDate);

    // Проверка перед отправкой формы
    form.addEventListener('submit', function(event) {
        if (!checkDate()) {
            event.preventDefault();
        }
    });
});