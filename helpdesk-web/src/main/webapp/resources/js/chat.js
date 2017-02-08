var scrollbarid;
$(document).ready(function () {
    $("textarea").keyup(function (event) {
        ifEnterClear();
    });
    showScroll();
    scrollbarid = ".textBox";
});

function showScroll() {
    $('#scrollable').slimscroll({
        wheelStep: 10,
        height: '310px',
        start: 'bottom',
        alwaysVisible: true,
        disableFadeOut: true
    });
}

function clearText() {
    $('textarea').val("");
}

function onSubmissionComplete() {
    showScroll();
    clearText();
    scrollLog();
}

function scrollLog() {
    var mydiv = $(".textBox");
    mydiv.scrollTop(mydiv.prop("scrollHeight"));
}

function saveScrollPos() {

    var scrollPos = jQuery('#scrollable').prop('scrollTop');
    document.getElementById('messageForm:scrollPos').value = scrollPos;
}

function autoScroll() {

    var scrollPos = document.getElementById('messageForm:scrollPos').value;
    // jQuery('#messageForm\\:messagesList.ui-outputpanel.ui-widget.textBox').animate({scrollTop: scrollPos}, 0);
    showScroll();
    $('#scrollable').slimscroll({
        scrollTo: scrollPos + 'px'
    });
}