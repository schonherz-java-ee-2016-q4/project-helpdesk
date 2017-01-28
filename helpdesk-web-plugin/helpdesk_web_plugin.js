var $div = $('<div />').appendTo('body');
var $text_$div = $('<div />').appendTo($div);
var $btn = $('<button/>').appendTo($div);

$(document).ready(function () {
    $btn.append("Yes");
    $text_$div.attr('class', 'helpdesk_plugin_text')
    $div.attr('class', 'helpdesk_plugin');
    $btn.attr('class', 'helpdesk_plugin_button');
    $btn.attr('src', 'https://javatraining.neuron.hu/helpdesk/chat.xhtml');
    $text_$div.append("Can we help you? ");
    $div.append($btn);

});
