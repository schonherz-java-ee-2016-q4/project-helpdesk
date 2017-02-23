$(document).ready(function () {

    $(function () {
        $('#datetimepicker6').datetimepicker({
            sideBySide: true
        });
        $('#datetimepicker7').datetimepicker({
            useCurrent: false, //Important! See issue #1075
            sideBySide: true
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });

        $('#dateDropDown .dropdown-menu').on({
            "click":function(e){
                e.stopPropagation();
            }
        });

    });
});