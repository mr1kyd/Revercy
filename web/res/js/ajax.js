/**
* Created by EAA on 15.04.2017.
*/
$(document).ready(function(){
    $("#button").click(function(){
        var data = {"x":$('#i').val(), "y":$('#j').val()}
        $.ajax({
            url: "reversi",
            type: 'get',
            dataType: 'html',
            data: data,
            success: function(result){
                $('#field').html(result);
        }});
    });
});
$(document).ready(function () {
    $.ajax({
        url: "reversi",
        type: 'get',
        dataType: 'html',
        success: function(result){
            $('#field').html(result);
        }});
})
