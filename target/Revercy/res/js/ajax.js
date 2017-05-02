/**
* Created by EAA on 15.04.2017.
*/
function sendMove(i, j) {
    if($('#cell_' + (i + 1) + (j + 1)).attr('class').indexOf('yellowCell') !== -1) {
        var data = {"x": i, "y": j};
        $('#cell_' + (i + 1) + (j + 1)).removeClass('yellowCell');
        $('#cell_' + (i + 1) + (j + 1)).html("<div class='playerCell'>");
        $.ajax({
            url: "reversi",
            type: 'get',
            data: data,
            dataType: 'json',
            data: data,
            success: function (result) {
                var computerMove = result.computerMove;
                var playerMove = result.playerMove;
                renderBoard(playerMove, 'player');
                var millisecondsToWait = 500;
                setTimeout(function () {
                    renderBoard(computerMove, 'computer');
                }, millisecondsToWait);
            }
        });
    }
}

$(document).ready(function () {
    $.ajax({
        url: "reversi",
        type: 'get',
        dataType: 'json',
        success: function (result) {
            renderBoard(result.computerMove, 'computer');
        }
    });
    $('#newGame').click(function () {
        $('div.playerCell').remove();
        $('div.computerCell').remove();
        var data = {"newGame": true};
        $.ajax({
            url: "reversi",
            data: data,
            type: 'get',
            dataType: 'json',
            success: function (result) {
                alert("Новая игра начинается!");
                renderBoard(result.computerMove, 'computer');
            }
        });
    })
});

function renderBoard(json, who) {
    var board = json.board;
    var validMove = 0;
    for(var i = 0; i < board.length; i++){
        for(var j = 0; j < board[0].length; j++){
            if(board[i][j] === 0){
                $('#cell_'+(i+1)+(j+1)).removeClass('yellowCell');
            }
            if(board[i][j] === 1){
                $('#cell_'+(i+1)+(j+1)).addClass('yellowCell');
                validMove++;
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
    var playerScore = json.playerScore;
    var computerScore = json.computerScore;
    $('#player').html(playerScore);
    $('#computer').html(computerScore);
    if(validMove == 0 && who == 'computer'){
        var message;
        if(playerScore > computerScore){
            message = "Вы выйграли";
        }
        else if(computerScore == playerScore){
            message = "Ничья";
        }
        else{
            message = "Вы проиграли";
        }
        alert(message);
    }
}
