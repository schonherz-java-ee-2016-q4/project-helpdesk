$(document).ready(function () {
    $("textarea").keyup(function (event) {
        if (event.keyCode == 13) {
            $("form").submit();
        }
    });
    $(function () {
        $('#textBox').slimscroll({
            height: '250px'
        });
    });
});


function clearText() {
    $('textarea').val("");
}
