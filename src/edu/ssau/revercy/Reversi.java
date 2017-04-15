package edu.ssau.revercy;

/**
 * Created by EAA on 15.04.2017.
 */
import java.util.Scanner;

class Reversi{
    public static void main(String[] args){
        int boardSize = 8;
        char humanColor = 'D';

        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-l")){
                humanColor = 'L';
            }
            if(args[i].equals("-n")){
                try{
                    boardSize = Integer.parseInt(args[i+1]);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                if(boardSize > 26 || boardSize < 4)
                    boardSize = 8;
                i++;
            }
        }

        playGame(boardSize, humanColor);
    }

    public static void playGame(int boardSize, char humanColor){
        char human = humanColor;
        char computer = (char)(144 - (int)humanColor);
        char currentPlayer = (human <  computer ? human : computer);
        String humanString = (human == 'D' ? "Dark" : "Light");
        String computerString = (computer == 'D' ? "Dark" : "Light");

        ReversiBoard board = new ReversiBoard(boardSize);
        //board.printBoard();
        System.out.println("Move played: --");
        while(board.isFull() == false && board.isOneColor() == false &&
                (board.hasMove(human) || board.hasMove(computer))){
            if(currentPlayer == human){
                if(board.hasMove(human) == false){
                    System.out.println(humanString + " player has no moves");
                }else{
                    System.out.println(humanString + " (human) plays now");
                    board.printScore();
                    String input = "";
                    do{
                        System.out.print("> ");
                        Scanner sc = new Scanner(System.in);
                        input = sc.nextLine();
                        //input = System.console().readLine(">");
                    }while(validateAndPlay(input, board, human) == false);
                    //board.printBoard();
                    System.out.println("Move played: " + input);
                }
            }
            else if(currentPlayer == computer){
                if(board.hasMove(computer) == false){
                    System.out.println(computerString + " player has no moves");
                }else{
                    System.out.println(computerString + " (COM) plays now");
                    board.printScore();
                    System.out.println(computerString + " player (COM) is calculating its next move... (this may take up to 30 seconds");

                    String move = ReversiPlayer.playMove(board, computer);
                    //board.printBoard();
                    System.out.println("Move played: " + move);
                }
            }
            currentPlayer = (char)(144 - (int)currentPlayer); //switch player
        }

        System.out.println("Game over");
        System.out.println("");

        char winner = board.getWinner();
        if(winner != 'T'){
            String winnerNature = (winner == human ? "human" : "computer");
            String winnerColor = (winner == 'L' ? "Light" : "Dark");
            System.out.println(winnerColor + " player (" + winnerNature + ") wins!");
            System.out.println("Score: " + board.getScore(winner) + " to " +  board.getScore((char)(144-(int)winner)));
        }else{
            System.out.println("The game is a tie");
        }

    }

    public static boolean validateAndPlay(String input, ReversiBoard board, char playerColor){
        boolean isValid = true;
        int iLoc = -1;
        int jLoc = -1;

        //validate input and set iLoc and jLoc
        try{
            if((int)input.charAt(0) < 97 || (int)input.charAt(0) > 96 + board.getBoardSize())
                isValid = false;
            else
                iLoc = (int)input.charAt(0) - 97;

            jLoc = Integer.parseInt(input.substring(1)) - 1;
            if(jLoc < 0 || jLoc >= board.getBoardSize())
                isValid = false;

            if(board.isValid(playerColor, iLoc, jLoc) == false){
                isValid = false;
            }
        }catch(Exception ex){
            isValid = false;
        }

        if(isValid == false){
            System.out.println("Error: invalid move, please try again");
            return false;
        }


        //play move if valid
        board.playMove(playerColor, iLoc, jLoc);
        return true;

    }


    //mostly testing function
    public static void playTurn(ReversiBoard board, char playerColor, int iLoc, int jLoc){
        board.playMove(playerColor, iLoc, jLoc);
        //board.printBoard();
        board.printScore();
    }

    //mostly testing function
    public static void printValidMoves(ReversiBoard board){
        for(int i = 0; i < board.getBoardSize(); i++){
            for(int j = 0; j < board.getBoardSize(); j++){
                if(board.isValid('D', i, j))
                    System.out.println("valid D " + i + " " + j);
                if(board.isValid('L', i, j))
                    System.out.println("valid L " + i + " " + j);
            }
        }
    }


}

