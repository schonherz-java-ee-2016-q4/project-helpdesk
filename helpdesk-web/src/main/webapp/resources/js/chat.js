var scrollbarid;
$(document).ready(function () {
    $("textarea").keyup(function (event) {
        ifEnterClear();
    });
    //  showScroll();
    scrollbarid = ".textBox";
});

function ifEnterClear() {
    if (event.keyCode == 13) {
        $("form").submit();
    }
}

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
    //  showScroll();
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
    jQuery('#messageForm\\:messagesList.ui-outputpanel.ui-widget.textBox').animate({scrollTop:scrollPos}, 0);
}