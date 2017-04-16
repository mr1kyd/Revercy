/**
* Created by EAA on 15.04.2017.
*/
function sendMove(i, j) {
    var data = {"x": i, "y": j};
    $.ajax({
        url: "reversi",
        type: 'get',
        data: data,
        dataType: 'json',
        data: data,
        success: function (result) {
            renderBoard(result);
        }
    });
}

$(document).ready(function () {
    $.ajax({
        url: "reversi",
        type: 'get',
        dataType: 'json',
        success: function(result){
            renderBoard(result);
        }});
});

function renderBoard(json) {
    var board = json.board;
    for(var i = 0; i < board.length; i++){
        for(var j = 0; j < board[0].length; j++){
            if(board[i][j] === 0){
                $('#cell_'+(i+1)+(j+1)).removeClass('yellowCell');
            }
            if(board[i][j] === 1){
                $('#cell_'+(i+1)+(j+1)).addClass('yellowCell');
            }
            if(board[i][j] === 2){
                $('#cell_'+(i+1)+(j+1)).removeClass('yellowCell');
                $('#cell_' + (i + 1) + (j + 1)).html("<div class='playerCell'>");
            }
            if(board[i][j] === 3){
                $('#cell_'+(i+1)+(j+1)).removeClass('yellowCell');
                $('#cell_'+(i+1)+(j+1)).html("<div class='computerCell'>");
            }
        }
    }
}
