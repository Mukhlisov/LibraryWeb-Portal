const source = 'http://localhost:8087/get/';

window.onload = function() {
    let images = document.getElementsByClassName('dynamic-image');
    for (var i = 0; i < images.length; i++) {
        let bookCover = images[i].getAttribute('data-cover');
        if (bookCover === null || bookCover === ''){
            images[i].src = source;
        } else{
            images[i].src = source + bookCover;
        }
    }
};