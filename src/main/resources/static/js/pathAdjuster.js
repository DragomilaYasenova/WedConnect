document.addEventListener("DOMContentLoaded", function() {
    let links = document.querySelectorAll('link[rel="stylesheet"]');
    let objects = document.querySelectorAll('object');

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

});
