var $div = $('<div />').appendTo('body');
var $text_$div = $('<div />').appendTo($div);
var $btn = $('<a/>').appendTo($div);

var uuid = "";

$(document).ready(function () {

    $btn.append("Yes");
    $text_$div.attr('class', 'helpdesk_plugin_text')
    $div.attr('class', 'helpdesk_plugin');
    $btn.attr('class', 'helpdesk_plugin_link');
    $btn.attr('href', 'http://javatraining.neuron.hu/helpdesk/secured/chat.xhtml');
    $text_$div.append("Can we help you?");
    $div.append($btn);

    generateUUID();

    $("a").on('click', function () {
        on_anchor_clicked(this);
    });

    $("input").on('focusout', function () {
        on_input_focusout(this);
    });

    $("button").on('click', function () {
        on_button_clicked(this);
    });

});

function generateUUID() {
    uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

function on_anchor_clicked(element) {
    doPost(uuid, "NAVIGATION", $(element).attr("href"));
}

function on_input_focusout(element) {
    doPost(uuid, "INPUT_FOCUSLOSS", element.value);
}

function on_button_clicked(element) {
    var target;
    if (element.value != undefined) {
        target = element.value;
    }
    if (element.attr('id') != undefined) {
        target = element.attr('id');
    }
    else {
        target = element.attr('id');
    }
    doPost(uuid, "BUTTON_CLICK", target);
}


function doPost(uuid, type, target) {

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
    }


