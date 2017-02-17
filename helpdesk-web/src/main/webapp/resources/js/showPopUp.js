var state = '';

$(document).ready(function () {
})

function showGrowl() {
    var newState = $('#conversationState').val().split(" ")[0];

    if (newState != state) {
        state = newState;
        if (state === 'NEW') {
            $.growl.notice({
                message: "There is a new conversation for you!",
                duration: 0,
                title: "New Conversation",
                size: "large"
            });
        }
        else if (state == 'IN_PROGRESS') {
            $.growl.notice({
                message: "You already have an open conversation!",
                duration: 0,
                title: "Open Conversation",
                size: "large"
            });
        }
    }
}
function redirectToChat() {
    var convID = $('#conversationState').val().split(" ")[1];
    window.location.href = "/helpdesk/chat?id=" + convID;
}