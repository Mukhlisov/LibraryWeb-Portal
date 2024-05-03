const source = 'http://localhost:8081/covers/';

window.onload = function() {
    var images = document.getElementsByClassName('dynamic-image');
    for (var i = 0; i < images.length; i++) {
        var bookCover = images[i].getAttribute('data-cover');
        images[i].src = source + bookCover;
    }
};