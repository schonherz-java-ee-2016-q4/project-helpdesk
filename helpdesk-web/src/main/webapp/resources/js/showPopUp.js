var showed = 0;

$(document).ready(function () {
    $('#default').puigrowl();
})

function showGrowl() {
    if (showed == 0) {
        $('#default').puigrowl('show', [{
            severity: 'info',
            summary: 'New Conversation',
            detail: 'There is a new conversation for you',
            life: 99999
        }]);
        showed = 1;
    }
}