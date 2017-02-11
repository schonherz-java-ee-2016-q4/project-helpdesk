var scrollbarid;
$(document).ready(function () {
    scrollbarid = ".textBox";
});

function showScroll() {
    $('#textBox').slimscroll({
        railVisible: true,
        alwaysVisible: false,
        wheelStep: 10,
        height: '250px'
    });
}

function clearText() {
    $('textarea').val("");
}

function onSubmissionComplete() {
    clearText();
    scrollLog();
}

function scrollLog() {
    var mydiv = $(".textBox");
    mydiv.scrollTop(mydiv.prop("scrollHeight"));
}

function saveScrollPos() {

    var scrollPos = jQuery('#messageForm\\:messagesList.ui-outputpanel.ui-widget.textBox').prop('scrollTop');
    document.getElementById('messageForm:scrollPos').value = scrollPos;
}

function autoScroll() {
    var scrollPos = document.getElementById('messageForm:scrollPos').value;
    jQuery('#messageForm\\:messagesList.ui-outputpanel.ui-widget.textBox').animate({scrollTop: scrollPos}, 0);
}