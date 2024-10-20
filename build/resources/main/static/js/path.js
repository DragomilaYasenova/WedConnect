let isLocal = window.location.href.indexOf("localhost") > -1 || window.location.href.indexOf("file://") > -1;
let basePath = isLocal ? "../static/css/" : "/css/";
document.write('<link rel="stylesheet" type="text/css" href="' + basePath + 'styles.css">');
document.write('<link rel="stylesheet" type="text/css" href="' + basePath + 'base.css">');
