var $div = $('<div />').appendTo('body');
var $text_$div = $('<div />').appendTo($div);
var $btn = $('<a/>').appendTo($div);

var uuid;

$(document).ready(function () {

    $btn.append("Yes");
    $text_$div.attr('class', 'helpdesk_plugin_text')
    $div.attr('class', 'helpdesk_plugin');
    $btn.attr('class', 'helpdesk_plugin_link');
    $text_$div.append("Can we help you?");
    $div.append($btn);

    generateUUID();

    $btn.on('click', function () {
        getAvailableAgent();
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

});

function generateUUID() {
    uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

function onAnchorClicked(element) {
    var target;
    if ($(element).attr('href') != undefined) {
        target = $(element).attr('href');
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
    if ($(element).attr('id') != undefined) {
        target = $(element).attr('id');
    }
    else if ($(element).attr('name') != undefined) {
        target = $(element).attr('name');
    }
    else {
        target = ($(element)[0]).toString();
    }

    postActivity(uuid, "BUTTON_CLICK", target);
    if (element.attr('id') != undefined) {
        target = element.attr('id');
    }
    else {
        target = element.attr('id');
    }
    doPost(uuid, "BUTTON_CLICK", target);
}


function postActivity(uuid, type, target) {

    var form = {};
    form["uuid"] = uuid;
    form["type"] = type;
    form["target"] = target;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "javatraining.neuron.hu/helpdesk/api/activites/",
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 100000,
    });
}

function getAvailableAgent() {

    var form = {};
    form["source"] = window.location.href;
    console.log(window.location.href);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "javatraining.neuron.hu/helpdesk/api/isagentavailable",
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 100000,
        success: function (response) {
            if (response.agentId == null) {
                //TODO: Display an error message
            }
            else {
                var chatPage = window.open('http://javatraining.neuron.hu/helpdesk/secured/chat/' + agentId);
                chatPage.focus();
            }
        },
        error: function (e) {
            //TODO: Display an error message
        }
    });
}