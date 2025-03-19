document.addEventListener("DOMContentLoaded", function() {
    let links = document.querySelectorAll('link[rel="stylesheet"]');
    let objects = document.querySelectorAll('object');
    let images = document.querySelectorAll('img');


    let isLocalhost = window.location.href.startsWith('http://localhost:8080/');

    links.forEach(function(link) {
        let href = link.getAttribute('href');

        if (isLocalhost && href) {
            if (href.startsWith('../static')) {
                link.setAttribute('href', href.replace('../static', ''));
            } else if (href.startsWith('../../static')) {
                link.setAttribute('href', href.replace('../../static', ''));
            }
        }
    });

    objects.forEach(function(obj) {
        let data = obj.getAttribute('data');

        if (isLocalhost && data) {
            if (data.startsWith('../static')) {
                obj.setAttribute('data', data.replace('../static', ''));
            } else if (data.startsWith('../../static')) {
                obj.setAttribute('data', data.replace('../../static', ''));
            }
        }
    });

    images.forEach(function(img) {
        let src = img.getAttribute('src');

        if (isLocalhost && src) {
            if (src.startsWith('../static')) {
                img.setAttribute('src', src.replace('../static', ''));
            } else if (src.startsWith('../../static')) {
                img.setAttribute('src', src.replace('../../static', ''));
            }
        }
    });

});
