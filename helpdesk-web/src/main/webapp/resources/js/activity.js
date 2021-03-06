$(document).ready(function () {

    $(function () {
        var formatString = "YYYY-MM-DD HH:mm";
        var dateAsString = "";

        function buttonReady() {
            if (($('#dateFilter\\:activityDateFilterTo').val() == "") || ($('#dateFilter\\:activityDateFilterFrom').val() == "")) {
                $('#dateFilter\\:submitDateFilters').prop('disabled', 'true');
            } else {
                $('#dateFilter\\:submitDateFilters').prop('disabled', '');
            }
        }

        var dateTimePickerFrom = $('#dateTimePickerFrom');
        var dateTimePickerTo =  $('#dateTimePickerTo');

        dateTimePickerFrom.datetimepicker({
            format: formatString,
            showClose: true,
            showTodayButton: true,
            allowInputToggle: true
        });
        dateTimePickerTo.datetimepicker({
            useCurrent: false, //Important! See issue #1075
            format: formatString,
            showClose: true,
            showTodayButton: true,
            allowInputToggle: true
        });

        dateTimePickerFrom.on("dp.change", function (e) {
            $('#dateTimePickerTo').data("DateTimePicker").minDate(e.date);
            dateAsString = e.date.format(formatString);
            $('#dateFilter\\:activityDateFilterFrom').val(dateAsString);
            buttonReady();
        });
        dateTimePickerTo.on("dp.change", function (e) {
            $('#dateTimePickerFrom').data("DateTimePicker").maxDate(e.date);
            dateAsString = e.date.format(formatString);
            $('#dateFilter\\:activityDateFilterTo').val(dateAsString);
            buttonReady();
        });


        $('.dropdown-menu').find('form').click(function (e) {
            e.stopPropagation();
        });

        $('#dateDropDown .dropdown-menu').on({
            "click": function (e) {
                e.stopPropagation();
            }
        });


        $('#dateFilter\\:submitDateFilters').on({
            "click": function (e) {
                $('.dropdown.open .dropdown-toggle').dropdown('toggle');
                $('#dateDropDown .dropdown-toggle').addClass('filterInUse');
            }
        });

        $('#typeDropDown li a').on({
            "click": function (e) {
                $('#typeDropDown .dropdown-toggle').addClass('filterInUse');
            }
        });

        $('#deleteFiltersBtn').on({
            "click": function (e) {
                $('#dateDropDown .dropdown-toggle').removeClass('filterInUse');
                $('#typeDropDown .dropdown-toggle').removeClass('filterInUse');
            }
        })


        buttonReady();




    });
});