var state = '';

$(document).ready(function () {
})

function showGrowl() {
    if ($('#conversationState').val() != state) {
        state = $('#conversationState').val();
        $.growl.notice({
            message: $('#conversationState').val(),
            duration: 0
        });
    }
    

}