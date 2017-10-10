function changeLang(language) {
    var pathname = window.location.href;

    pathname = pathname.replace(/(\?|&)lang=[^?&]+/g, "");


    var langParam = "lang=" + language;
    if (pathname.indexOf("?") != -1) {
        langParam = "&" + langParam;
    } else {
        langParam = "?" + langParam
    }


    window.location.assign(pathname + langParam);
}