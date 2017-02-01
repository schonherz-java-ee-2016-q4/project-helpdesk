var $pluginLayout = $('<div />').appendTo('body');
var $textParent = $('<div />').appendTo($pluginLayout);
var $pluginMainText = $('<div />').appendTo($textParent);
var $pluginLink = $('<a/>').appendTo($textParent);
var $errorMessage = $('<div />').appendTo($pluginLayout);

var uuid;

$(document).ready(function () {
    $pluginLayout.attr('class', 'helpdesk_plugin');
    $textParent.attr('class', 'helpdesk_plugin_text_parent')
    $pluginMainText.attr('class', 'helpdesk_plugin_text');
    $pluginLink.attr('class', 'helpdesk_plugin_link');
    $errorMessage.attr('class', 'helpdesk_error_text');

    $pluginMainText.append("If you have any problem with this site");
    $pluginLink.append("Click here");


    generateUUID();

    $pluginLink.on('click', function () {
        getAvailableAgent();
    });

    $("form").on('submit', function () {
        onFormSubmit(this);
    });

    $("a").on('click', function () {
        onAnchorClicked(this);
    });

    $("input").on('focusout', function () {
        onInputFocusloss(this);
    });

    $("button").on('click', function () {
        onButtonClicked(this);
    });

    $errorMessage.hide()

});

function generateUUID() {
    uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

function onFormSubmit(element) {
    var target;

    if ($(element).attr('action') != undefined) {
        target = $(element).attr('action');
    }
    else if ($(element).attr('id') != undefined) {
        target = $(element).attr('id');
    }
    else if ($(element).attr('name') != undefined) {
        target = $(element).attr('name');
    }
    else {
        target = ($(element)[0]).toString();
    }
    postActivity(uuid, "FORM_SUBMIT", target);
}

function onAnchorClicked(element) {
    var target;

    if ($(element).attr('href') != undefined) {
        target = $(element).attr('href');
    }
    else if ($(element).attr('id') != undefined) {
        target = $(element).attr('id');
    }
    else if ($(element).attr('name') != undefined) {
        target = $(element).attr('name');
    }
    else {
        target = ($(element)[0]).toString();
    }
    postActivity(uuid, "NAVIGATION", target);
}

function onInputFocusloss(element) {
    postActivity(uuid, "INPUT_FOCUSLOSS", element.value);
}

function onButtonClicked(element) {
    var target;
    if ($(element).text() != "") {
        target = $(element).text();
    }
    else if ($(element).attr('id') != undefined) {
        target = $(element).attr('id');
    }
    else if ($(element).attr('name') != undefined) {
        target = $(element).attr('name');
    }
    else {
        target = ($(element)[0]).toString();
    }

    postActivity(uuid, "BUTTON_CLICK", target);
}

function displayError(message) {

    $errorMessage.html(message);
    $errorMessage.slideDown("fast").delay(5000);
    $errorMessage.slideUp("slow");

}

function postActivity(uuid, type, target) {

    var form = {};
    form["uuid"] = uuid;
    form["type"] = type;
    form["target"] = target;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://javatraining.neuron.hu/helpdesk/api/activites/",
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 100000,
    });
}

function getAvailableAgent() {

    $pluginLink.fadeOut("fast");

    var form = {};
    form["source"] = window.location.host;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://javatraining.neuron.hu/helpdesk/api/agents/available",
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 100000,
        success: function (response) {
            console.log(response);
            if (response.agentId == null) {
                displayError("There isn't any available agent!");
            }
            else {
                var chatPage = window.open('http://javatraining.neuron.hu/helpdesk/secured/chat' + agentId);
                chatPage.focus();
            }
        },
        error: function (e) {
            displayError("We couldn't connect to the helpdesk site");
        },
        complete: function () {
            $pluginLink.fadeIn("fast");
        }
    });
}