function invalid(element, message) {
    element.get(0).setCustomValidity(message);

    element.keyup(function () {
        element.get(0).setCustomValidity('');
    });
}