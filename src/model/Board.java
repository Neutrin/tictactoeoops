package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Board{
    private int size;
    private Stack<Move> moves;
    private Peice [][]peices;

    public Board(int size) throws Exception {
        setSize(size);
        moves = new Stack<>();
        peices = new Peice[size][size];
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                peices[row][col] = new Peice(null);
            }
        }
    }

    public int getSize() {
        return size;
    }

    //This setter will set size for the board
    //if size is less than or equal to zero this method will return exception
    public void setSize(int size) throws Exception {
        if(size <= 0){
            throw new Exception("Invalid size for board");
        }
        this.size = size;
    }

    /*
        This method will add a move to the board and also store it
        in the list of moves played
        will throw exception if row no or col no is out
        of bound of size of the board
    */
    public void addMove(Move move) throws Exception{
        int rowNo = move.getRowNo();
        int colNo = move.getColNo();
        if((rowNo > size) || (colNo > size)){
            throw new Exception("this is an invalid move");
        }
        peices[rowNo-1][colNo-1] = new Peice(move.getMark());
        moves.push(move);
    }

    /*
        This method will undo last two moves will be used
        mostly when one of the user wins and other user want to undo
        his/her mover
        Throws exception if game has'nt started
    */

    public List<Move> undo() throws Exception {
        if(peices.length == 0){
            throw new Exception("No move to undo");
        }
        List<Move> undoMoves = new ArrayList<>();
        Move lastMove = moves.pop();
        undoMoves.add(lastMove);
        peices[lastMove.getRowNo()-1][lastMove.getColNo()-1] = new Peice(null);
        if(!moves.isEmpty()){
            lastMove = moves.pop();
            undoMoves.add(lastMove);
            peices[lastMove.getRowNo()-1][lastMove.getColNo()-1] = new Peice(null);
        }
        System.out.println("Last two moves undone");
        return undoMoves;
    }
    /*
        This function will be used to display board at any point
        of game
    */
    public void displayBoard() {
        for(int row = 0; row < size; row++){
            System.out.print("| ");
            for(int col = 0; col < size; col++){

                    System.out.print(peices[row][col].marking + " |");

            }
            System.out.println();
        }
    }

    public void refreshBoard() {
        peices = new Peice[size][size];
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                peices[row][col] = new Peice(null);
            }
        }
    }
}