$(document).ready(function(){
    $("textarea").keyup(function(event){
        if(event.keyCode == 13){
            $("form").submit();
        }
    });
});


function clearText() {
    $('textarea').val("");
}
