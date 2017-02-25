$(document).ready(function () {

    $(function () {
        var formatString = "YYYY-MM-DD HH:mm";
        var dateAsString = "";

        $('#dateTimePickerFrom').datetimepicker({
            format: "YYYY-MM-DD HH:mm",
            allowInputToggle: true
        });
        $('#dateTimePickerTo').datetimepicker({
            useCurrent: false, //Important! See issue #1075
            format: "YYYY-MM-DD HH:mm",
            allowInputToggle: true
        });
        $("#dateTimePickerFrom").on("dp.change", function (e) {
            $('#dateTimePickerTo').data("DateTimePicker").minDate(e.date);
            dateAsString = e.date.format(formatString);
            $('#dateFilter\\:activityDateFilterFrom').val(dateAsString);
        });
        $("#dateTimePickerTo").on("dp.change", function (e) {
            $('#dateTimePickerFrom').data("DateTimePicker").maxDate(e.date);
            dateAsString = e.date.format(formatString);
            $('#dateFilter\\:activityDateFilterTo').val(dateAsString);
        });

        $('#dateDropDown .dropdown-menu').on({
            "click":function(e){
                e.stopPropagation();
            }
        });

    });
});