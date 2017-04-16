package edu.ssau.revercy;

/**
 * Created by EAA on 15.04.2017.
 */
public class ReversiPlayer {

    public static String playMove(ReversiBoard board, char playerColor){
        if(board.hasMove(playerColor) == false)
            return "no move to play";
        int[] result = new int[3];
	/*if(board.getSpotsLeft() > 8 && board.getFillFactor() < .6){
	    result = minimaxWithAB(board, playerColor, 5, true, playerColor, -10000, 10000);
	}else{ //run a slightly different algorithm for endgame
	*/
        result = minimax(board, playerColor, 1, true, playerColor);
//	}

        int iVal = result[1];
        int jVal = result[2];
        board.playMove(playerColor, iVal, jVal);

        String playedMove = "";
        playedMove += (char)(iVal + 97);
        playedMove += (jVal + 1);
        return playedMove;

    }

    public static int[] minimaxWithAB(ReversiBoard board, char playerColor, int depth, boolean maximizingPlayer, char myColor, int alpha, int beta){
        if(depth == 0 || (board.hasMove(playerColor) == false && board.hasMove((char)(144 - (int)playerColor)) == false) || board.isFull() == true){
            int[] result = new int[3];
            if((board.hasMove(playerColor) == false && board.hasMove((char)(144 - (int)playerColor)) == false) || board.isFull() == true){
                if(board.getScore(myColor) == board.getScore((char)(144 - (int)myColor))){
                    result[0] = 0;
                }else{
                    result[0] = (board.getScore(myColor) > board.getScore((char)(144 - (int)myColor)) ? 10000 : -10000);
                }
                result[1] = -1;
                result[2] = -1;
                return result;
            }

            result[0] = board.calculateScore(myColor);
            result[1] = -1;
            result[2] = -1;
            return result;
        }

        if(maximizingPlayer == true){
            int bestVal = -999999999;
            int iVal = -1;
            int jVal = -1;

            boolean hasMoves = false;
            for(int i = 0; i < board.getBoardSize(); i++){
                for(int j = 0; j < board.getBoardSize(); j++){
                    if(board.isValid(playerColor, i, j)){
                        hasMoves = true;
                        System.out.println("maximizing with move: " + i + "   " + j);
                        ReversiBoard tempBoard = new ReversiBoard(board);
                        tempBoard.playMove(playerColor, i, j);

                        int result[] = minimaxWithAB(tempBoard,  (char)(144-(int)playerColor), depth - 1, false, myColor, alpha, beta);
                        if(result[0] > alpha){
                            alpha = result[0];

                            if(result[0] > bestVal){
                                iVal = i;
                                jVal = j;
                            }
                        }
                        if(alpha >= beta){
                            result[0] = alpha;
                            return result;
                        }
                    }
                }
            }

            if(hasMoves == false){
                return minimaxWithAB(board, (char)(144 - (int)playerColor), depth - 1, false, myColor, alpha, beta);
            }
            int returnArr[] = new int[3];
            returnArr[0] = alpha;
            returnArr[1] = iVal;
            returnArr[2] = jVal;
            return returnArr;
        }else{ /*minimizing player*/
            int bestVal = 9999999;
            int iVal = -1;
            int jVal = -1;

            boolean hasMoves = false;
            for(int i = 0; i < board.getBoardSize(); i++){
                for(int j = 0; j < board.getBoardSize(); j++){
                    if(board.isValid(playerColor, i, j)){
                        System.out.println("minimizing with move: " + i + "   " + j);
                        hasMoves = true;
                        ReversiBoard tempBoard = new ReversiBoard(board);
                        tempBoard.playMove(playerColor, i, j);

                        int result[] = minimaxWithAB(tempBoard, (char)(144-(int)playerColor), depth - 1, true, myColor, alpha, beta);
                        if(result[0] < beta){
                            beta = result[0];

                            if(result[0] < bestVal){
                                iVal = i;
                                jVal = j;
                            }
                        }
                        if (beta <= alpha){
                            result[0] = beta;
                            System.out.println("" + i  + "     " + j);
                            return result;
                        }
                    }
                }
            }


            if(hasMoves == false){
                System.out.println("heeerrrreeE");
                return minimaxWithAB(board, (char)(144 - (int)playerColor), depth - 1, true, myColor, alpha, beta);
            }
            int returnArr[] = new int[3];
            returnArr[0] = beta;
            returnArr[1] = iVal;
            returnArr[2] = jVal;
            System.out.println("LOOK: " + returnArr[1] + "   " + returnArr[2] );
            return returnArr;
        }
    }


    public static int[] minimax(ReversiBoard board, char playerColor, int depth, boolean maximizingPlayer, char myColor){
        if(depth == 0 || (board.hasMove(playerColor) == false && board.hasMove((char)(144 - (int)playerColor)) == false) || board.isFull() == true){
            int[] result = new int[3];
            if((board.hasMove(playerColor) == false && board.hasMove((char)(144 - (int)playerColor)) == false) || board.isFull() == true){
                if(board.getScore(myColor) == board.getScore((char)(144 - (int)myColor))){
                    result[0] = 0;
                }else{
                    result[0] = (board.getScore(myColor) > board.getScore((char)(144 - (int)myColor)) ? 10000 : -10000);
                }
                result[1] = -1;
                result[2] = -1;
                return result;
            }

            result[0] = board.calculateScore(myColor);
            result[1] = -1;
            result[2] = -1;
            return result;
        }

        if(maximizingPlayer == true){
            int bestVal = -999999999;
            int iVal = -1;
            int jVal = -1;

            boolean hasMoves = false;
            for(int i = 0; i < board.getBoardSize(); i++){
                for(int j = 0; j < board.getBoardSize(); j++){
                    if(board.isValid(playerColor, i, j)){
                        hasMoves = true;
                        ReversiBoard tempBoard = new ReversiBoard(board);
                        tempBoard.playMove(playerColor, i, j);


                        int result[] = minimax(tempBoard,  (char)(144-(int)playerColor), depth - 1, false, myColor);
                        if(result[0] > bestVal){
                            bestVal = result[0];
                            iVal = i;
                            jVal = j;
                        }
                    }

                }
            }
            if(hasMoves == false){

                return minimax(board, (char)(144 - (int)playerColor), depth - 1, false, myColor);
            }
            int returnArr[] = new int[3];
            returnArr[0] = bestVal;

            returnArr[1] = iVal;
            returnArr[2] = jVal;
            return returnArr;
        }else{ //minimizing player
            int bestVal = 9999999;
            int iVal = -1;
            int jVal = -1;

            boolean hasMoves = false;
            for(int i = 0; i < board.getBoardSize(); i++){
                for(int j = 0; j < board.getBoardSize(); j++){
                    if(board.isValid(playerColor, i, j)){
                        hasMoves = true;
                        ReversiBoard tempBoard = new ReversiBoard(board);
                        tempBoard.playMove(playerColor, i, j);

                        int result[] = minimax(tempBoard, (char)(144-(int)playerColor), depth - 1, true, myColor);
                        if(result[0] < bestVal){
                            bestVal = result[0];
                            iVal = i;
                            jVal = j;
                        }

                    }

                }
            }
            if(hasMoves == false){
                return minimax(board, (char)(144 - (int)playerColor), depth - 1, true, myColor);
            }
            int returnArr[] = new int[3];
            returnArr[0] = bestVal;
            returnArr[1] = iVal;
            returnArr[2] = jVal;
            return returnArr;
        }
    }

    public static String playRandomMove(ReversiBoard board, char playerColor){
        for(int i = 0; i < board.getBoardSize(); i++){
            for(int j = 0; j < board.getBoardSize(); j++){
                if(board.isValid(playerColor, i, j)){
                    board.playMove(playerColor, i, j);
                    String playedMove = "";
                    playedMove += (char)(i + 97);
                    playedMove += (j+1);
                    return playedMove;
                }
            }
        }
        return "cannot play a move";
    }
}
