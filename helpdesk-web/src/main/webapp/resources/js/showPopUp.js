var state = '';

$(document).ready(function () {
})

function showGrowl() {
    if ($('#conversationState').val() != state) {
        state = $('#conversationState').val();
        $.growl.notice({
            message: $('#conversationState').val() === 'NEW' ? "There is a new conversation for you!" : "Your already have an open conversation!",
            duration: 0,
            title: "New Conversation"
        });
    }


}