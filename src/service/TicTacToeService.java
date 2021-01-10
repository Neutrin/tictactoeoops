package service;

import model.Board;
import model.EnumMark;
import model.Move;
import model.User;
import org.omg.CORBA.UNKNOWN;

import java.util.List;
import java.util.Scanner;

public class TicTacToeService{
    Board board;
    MoveService moveService;
    private List<User> player;
    private int []rowCount;
    private int []colCount;
    int diagnalCount;
    int antiDiagnalCount;


    public void intializeBoard(int size, List<User> users) throws Exception {
        board = new Board(size);
        player = users;
        rowCount = new int[size+1];
        colCount = new int[size+1];
        diagnalCount = 0;
        antiDiagnalCount = 0;
        moveService = new MoveService();
    }

    public void runGame() throws Exception {
        if(board == null){
            throw new Exception("Board not intialised");
        }
        User userWon = null;
        int playerCount = 0;
        int maxPlayer = player.size();
        Move move = null;
        User curPlayer = null;
        int movesPlayed = 0;
        while(true){
            curPlayer = player.get(playerCount);
            board.displayBoard();
            move = moveService.getMove();
            move.setMark(EnumMark.MARK_O);
            if(playerCount == 1){
                move.setMark(EnumMark.MARK_X);
            }
            board.addMove(move);
            updateCount(move, playerCount);
            movesPlayed++;
            if(isWinningMove(move)){
                board.displayBoard();
                System.out.println("User won" + curPlayer.getName());
                try{
                    if(!undo(playerCount, maxPlayer)){
                        userWon = curPlayer;
                        //to do save details
                        break;
                    }
                    movesPlayed = movesPlayed-2;
                    //Unset the user who won
                    userWon = null;
                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                    continue;
                }
            }
            if(movesPlayed == (board.getSize()* board.getSize())){
                System.out.println("Refreshing your board");
                refreshBoard();
            }
            playerCount = (playerCount + 1)%maxPlayer;

        }
    }

    private void updateCount(Move move, int playerCount){
        if(playerCount == 1){
            rowCount[move.getRowNo()] -= 1;
            colCount[move.getColNo()] -= 1;
            if(move.isDiagnal()){
                diagnalCount -= 1;
            }
            if(move.isAntiDiagnal(board.getSize())){
                antiDiagnalCount -= 1;
            }
        }else{
            rowCount[move.getRowNo()] += 1;
            colCount[move.getColNo()] += 1;
            if(move.isDiagnal()){
                diagnalCount += 1;
            }
            if(move.isAntiDiagnal(board.getSize())){
                antiDiagnalCount += 1;
            }
        }

    }

    private boolean isWinningMove(Move move) {
        if((Math.abs(rowCount[move.getRowNo()]) == board.getSize())
            ||(Math.abs(colCount[move.getColNo()]) == board.getSize())
                || (Math.abs(diagnalCount) == board.getSize())
                || (Math.abs(antiDiagnalCount) == board.getSize())){
            return true;
        }
        return false;
    }

    private boolean undo(int playerCount, int playerSize) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Want to undo your last two moves press 1 else 0");
        int option = 0;
        option = sc.nextInt();
        if(option >= 1){
            List<Move> undoMoves = board.undo();
            for(Move curMove : undoMoves){
                if(playerCount == 1){
                    rowCount[curMove.getRowNo()] += 1;
                    colCount[curMove.getColNo()] += 1;
                    if(curMove.isDiagnal()){
                        diagnalCount += 1;
                    }
                    if(curMove.isAntiDiagnal(board.getSize())){
                        antiDiagnalCount += 1;
                    }
                }else{
                    rowCount[curMove.getRowNo()] -= 1;
                    colCount[curMove.getColNo()] -= 1;
                    if(curMove.isDiagnal()){
                        diagnalCount -= 1;
                    }
                    if(curMove.isAntiDiagnal(board.getSize())){
                        antiDiagnalCount -= 1;
                    }
                }
                playerCount = (playerCount + 1)%playerSize;

            }
            //TO DO ALSO UNSET THE USER DETAILS
        }
        if(option == 1){
            return true;
        }
        return false;
    }
    public void refreshBoard() throws Exception {
        rowCount = new int[board.getSize()+1];
        colCount = new int[board.getSize()+1];
        diagnalCount = 0;
        antiDiagnalCount = 0;
        board.refreshBoard();
    }
}
