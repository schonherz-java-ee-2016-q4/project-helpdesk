function onSubmissionComplete() {
    clearText();
    scrollLog();
}

function clearText() {
    $('textarea').val("");
}

function scrollLog() {
    var mydiv = $("#messageDiv");
    mydiv.scrollTop(mydiv.prop("scrollHeight"));
}

function saveScrollPos() {

    var scrollPos = jQuery('#messageDiv').prop('scrollTop');
    document.getElementById('messageForm:scrollPos').value = scrollPos;
}

function autoScroll() {
    var scrollPos = document.getElementById('messageForm:scrollPos').value;
    jQuery('#messageDiv').animate({scrollTop: scrollPos}, 0);
}