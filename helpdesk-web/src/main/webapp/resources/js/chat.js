$(document).ready(function () {
    $("textarea").keyup(function (event) {
        ifEnterClear();
    });
    showScroll();
});

function ifEnterClear() {
    if (event.keyCode == 13) {
        $("form").submit();
    }
}

function showScroll() {
    $('#textBox').slimscroll({
        height: '250px'
    });
}

function clearText() {
    $('textarea').val("");
}

function onSubmissionComplete() {
    showScroll();
    clearText();
}