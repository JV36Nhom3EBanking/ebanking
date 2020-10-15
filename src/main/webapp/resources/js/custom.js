$(document).ready(function () {
//    function format_currency() {
//        $('.currency').click(function () {
//            var amount = $(this).text();
//            alert(amout);
////            var formatAmount = amount.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
////            
////            $(this).html(formatAmount + "");
//
//        });
//    }


    $('.currency').each(function () {
        var amount = $(this).text();
        var formatAmount = amount.replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1.");

        $(this).html(formatAmount);
    })
}); 