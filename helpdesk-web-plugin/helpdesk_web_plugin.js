var $div = $('<div />').appendTo('body');
var $text_$div = $('<div />').appendTo($div);
var $btn = $('<a/>').appendTo($div);

$(document).ready(function () {
    $btn.append("Yes");
    $text_$div.attr('class', 'helpdesk_plugin_text')
    $div.attr('class', 'helpdesk_plugin');
    $btn.attr('class', 'helpdesk_plugin_link');
    $btn.attr('href', 'http://javatraining.neuron.hu/helpdesk/secured/chat.xhtml');
    $text_$div.append("Can we help you?");
    $div.append($btn);
});
