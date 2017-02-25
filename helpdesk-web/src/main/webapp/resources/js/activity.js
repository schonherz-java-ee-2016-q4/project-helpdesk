$(document).ready(function () {

    $(function () {

        $('#dateTimePickerFrom').datetimepicker({
            sideBySide: true,
            allowInputToggle: true
        });
        $('#dateTimePickerTo').datetimepicker({
            useCurrent: false, //Important! See issue #1075
            sideBySide: true,
            allowInputToggle: true
        });
        $("#dateTimePickerFrom").on("dp.change", function (e) {
            $('#dateTimePickerTo').data("DateTimePicker").minDate(e.date);
            $('#activityDateFilterFrom').val(e.date);
        });
        $("#dateTimePickerTo").on("dp.change", function (e) {
            $('#dateTimePickerFrom').data("DateTimePicker").maxDate(e.date);
            $('#activityDateFilterTo').val(e.date);
        });

        $('#dateDropDown .dropdown-menu').on({
            "click":function(e){
                e.stopPropagation();
            }
        });

    });
});