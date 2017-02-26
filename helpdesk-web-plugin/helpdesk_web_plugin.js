var $pluginLayout = $('<div />').appendTo('body');
var $pluginContentParent = $('<div />').appendTo($pluginLayout);
var $pluginMainText = $('<div />').appendTo($pluginContentParent);
var $pluginLink = $('<a/>').appendTo($pluginContentParent);
var $slideDownParent = $('<div />').appendTo($pluginLayout);
var $slideDownArrow = $('<apan />').appendTo($slideDownParent);
var $modalParent = $('<div />').appendTo('body');
var $modalBody = $('<div />').appendTo($modalParent);
var $modalClose = $('<a />').appendTo($modalBody);
var $modalTitle = $('<h2 />').appendTo($modalBody);
var $modalText = $('<p />').appendTo($modalBody);
var $modalEmailLabel = $('<label />').appendTo($modalBody);
var $modalEmailInput = $('<input />').appendTo($modalBody);
var $modalButton = $('<button />').appendTo($modalBody);
var $errorMessage = $('<div />').appendTo($modalBody);

var uuid;
var slided = 0;

$(document).ready(function () {
    $pluginLayout.attr('class', 'helpdesk_plugin_parent');
    $pluginContentParent.attr('class', 'helpdesk_plugin_content_parent');
    $pluginMainText.attr('class', 'helpdesk_plugin_text');
    $pluginLink.attr('class', 'helpdesk_plugin_link');
    $errorMessage.attr('class', 'helpdesk_error_text');
    $slideDownParent.attr('class', 'slide_right_parent');
    $slideDownArrow.attr('class', 'slide_right_arrow');
    $modalParent.attr('class', 'modalDialog');
    $modalClose.attr('href', '#close');
    $modalClose.attr('class', 'closeModal');
    $modalClose.attr('title', 'Close');
    $modalEmailLabel.attr('for', "email_input");
    $modalEmailLabel.attr('id', "email_input");
    $modalEmailLabel.attr('type', "text");
    $modalEmailLabel.attr('name', "email_input");
    $modalEmailLabel.attr('placeholder', 'E-mail address');
    $modalEmailInput.attr('class', 'modal_email_input');
    $modalButton.attr('class', 'modal_submit_button');

    $pluginMainText.append('If you have any problem with this site');
    $pluginLink.append('Click here');
    $modalClose.append('X');
    $modalTitle.append('Welcome to the Helpdesk Platform');
    $modalText.append('Please enter your e-mail address to connect to your helpdesk agent!');
    $modalEmailLabel.append('E-mail:');
    $modalButton.append('OK');

    generateUUID();

    $pluginLink.on('click', function () {
        $modalParent.fadeTo(500, 1);
        $(".modalDialog").css({"pointer-events": "auto"});
    });

    $modalButton.on('click', function () {
        var email = $modalEmailInput.val();
        if (validateEmailAddress(email)) {
            getAvailableAgent(email);
        }
        else {
            displayError('The e-mail address is invalid');
        }
    });

    $slideDownParent.on('click', function () {
        slidePlugin();
    });

    $modalClose.on('click', function () {
        $(".modalDialog").fadeTo(500, 0);
        $(".modalDialog").css({"pointer-events": "none"});
    })

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

function slidePlugin() {
    if (slided == 0) {
        $pluginContentParent.slideUp('fast');
        setTimeout(function () {
            $pluginLayout.animate({width: '30px'}, 350)
        }, 350);

        slided = 1;
    }
    else {

        $pluginLayout.animate({
            width: '370px',
            right: 0
        }, 350);
        slided = 0;
        setTimeout(function () {
            $pluginContentParent.slideDown('fast')
        }, 350);
    }
    $slideDownArrow.css({'transform': 'rotate(' + slided * 180 + 'deg)'});
}

function generateUUID() {
    uuid = window.sessionStorage.getItem('helpesk_platform_uuid');
    if (uuid == null) {
        uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
            return v.toString(16);
        });
        window.sessionStorage.setItem('helpesk_platform_uuid', uuid);
    }
    console.log(uuid);
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
    var target;

    if ($(element).attr('id') != undefined) {
        target = $(element).attr('id');
    }
    else if ($(element).attr('name') != undefined) {
        target = $(element).attr('name');
    }
    target = target + "= " + (element.value);
    postActivity(uuid, "NAVIGATION", target);
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
    $errorMessage.slideDown("fast").delay(1000);
    $errorMessage.slideUp("slow");
}

function validateEmailAddress(email) {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(email);
}

function postActivity(uuid, type, target) {

    var form = {};
    form["uuid"] = uuid;
    form["type"] = type;
    form["target"] = target;

    console.log(form);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://javatraining.neuron.hu/helpdesk/api/activites/",
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 100000,
    });
}

function getAvailableAgent(email) {
    $modalButton.fadeOut('fast');
    var form = {};
    form["source"] = window.location.host;
    form["clientId"] = uuid;
    form["clientEmail"] = email;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://javatraining.neuron.hu/helpdesk/api/agents/available",
        data: JSON.stringify(form),
        dataType: 'json',
        timeout: 100000,
        success: function (response) {
            console.log(response);
            if (response.conversationId == null) {
                displayError("There is not any available agent!");
            }
            else {
                var chatPage = window.open('http://javatraining.neuron.hu/helpdesk/secured/chat.xhtml?' + response.conversationId);
                chatPage.focus();
            }
        },
        error: function (e) {
            displayError("We couldn't connect to the helpdesk site");
        },
        complete: function () {
            $modalButton.fadeIn("fast");
        }
    });
}