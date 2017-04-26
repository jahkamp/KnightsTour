/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3.KnightsTour;

/**
 *
 * @author Jared Kamp
 CSCI 232
 Lab 3 - KnightsTour's Tour
 */
public class KnightsTour {
    private boolean backOneSpace;
    private boolean backTwoSpaces;
    private boolean forwardOneSpace;
    private boolean forwardTwoSpaces;
    private boolean rightOneSpace;
    private boolean rightTwoSpaces;
    private boolean leftOneSpace;
    private boolean leftTwoSpaces;
    private int lastStart;
    private int start;
    private int end;
    private ChessPiece knight;
    private chessBoard myChessBoard;
    public ChessPiece[] tour;
    public int number;
    public boolean success;
    public KnightsTour(int startLocation, chessBoard board){
        //intialize
        myChessBoard = board;
        number = 0; //first knight
        success = false;
        start = startLocation;
        end = start;
        tour = new ChessPiece[myChessBoard.knightArray.length]; //tour keeps track of the order of moves
    }
    private void addMove(){
        myChessBoard.incrementMoves(); 
        if(!success)
            number++;
        knight = new ChessPiece(end, number);
        tour[number] = knight;
        if(end > 0)
            myChessBoard.knightArray[end-1] = knight;
    }
    private void backOut(){
        if(tour[number] != null){
            tour[number] = null;
        }
        number--;
        myChessBoard.removeKnight(start);
        if(number != 0){
            knight = tour[number];
            start = knight.getStart();
            System.out.println("Backing out to " + start);
            //myChessBoard.removeKnight(lastStart);
            possibleMoves(start);
        }
    }
    public int nextMove(int startPosition){
        //test base case
        if(number == tour.length-1){//base case
            success = true;
            addMove();
            return startPosition;
        }
        else{
            lastStart = start;
            start = startPosition;
            //determine which moves are possible based on starting position and board size and sets appropriate booleans
            possibleMoves(start);

            //try each valid move,
            if(rightOneSpace && backTwoSpaces && !success){
                //one right, two backward
                end = start + 1 - 2*myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved R1 B2 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if (rightTwoSpaces && backOneSpace && !success){
                //two right, one backward
                end = start + 2 - myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved R2 B1 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if (rightTwoSpaces && forwardOneSpace && !success){//two right, one forward
                end = start + 2 + myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved R2 F1 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if (rightOneSpace && forwardTwoSpaces && !success){
                //one right, two forward
                end = start + 1 + 2*myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved R1 F2 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if (leftOneSpace && forwardTwoSpaces && !success){
                //one left, two forward
                end = start - 1 + 2*myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved L1 F2 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            //two left, one forward 
            if(leftTwoSpaces && forwardOneSpace && !success){
                end = start - 2 + myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved L2 F1 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if (leftTwoSpaces && backOneSpace && !success){
                //two left, one backward
                end = start - 2 - myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved L2 B1 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if (leftOneSpace && backTwoSpaces && !success){
                //one left, two backward
                end = start - 1 - 2*myChessBoard.numColsAndRows;
                //position occupied?
                if(myChessBoard.addKnight(end)){
                   addMove();
                   System.out.println("Moved L1 B2 to " + end);
                   knight.setEnd(nextMove(end));
                }
            }
            if(!success)
                backOut();
            return start; //no luck
        }
    }
    public String orderOfMoves(){
        String order = "";
        for(int i = 0; i < tour.length; i++){
            if(tour[i] != null){
                order += " (";
                order += tour[i].getStart();
                order += "),";
            }
        }
        return order;
    }
    private void possibleMoves(int startPos){
        //initialize
        backOneSpace = true;
        backTwoSpaces = true;
        forwardOneSpace = true;
        forwardTwoSpaces = true;
        rightOneSpace = true;
        rightTwoSpaces = true;
        leftOneSpace = true;
        leftTwoSpaces = true;
            
        //check top and bottom edges
        if(startPos <= myChessBoard.numColsAndRows) //in the 1st row, so if we go backwards, we fall off.
        {   
            backOneSpace = false;
            backTwoSpaces = false;
        }
        if(startPos <= 2*myChessBoard.numColsAndRows)
            backTwoSpaces = false;
        if(startPos > myChessBoard.numColsAndRows*(myChessBoard.numColsAndRows-1)) //in the back row, so if we go forwards, we fall off
        {
            forwardOneSpace = false;
            forwardTwoSpaces = false;
        }
        if(startPos > myChessBoard.numColsAndRows*(myChessBoard.numColsAndRows-2))
            forwardTwoSpaces = false;

        //now check left and right edges.
        if(startPos == myChessBoard.numColsAndRows){ //on the right most edge of 1st row.
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 2){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 3){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 4){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 5){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 6){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 7){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 8){
            rightOneSpace = false;
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows - 1){ //on the right most edge of 1st row.
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 2 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 3 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 4 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 5 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 6 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 7 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == myChessBoard.numColsAndRows * 8 - 1){
            rightTwoSpaces = false;
        }
        if(startPos == 1){ //on the left most edge of 1st row
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 2){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 3){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 4){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 5){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 6){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 7){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 1 + myChessBoard.numColsAndRows * 8){
            leftOneSpace = false;
            leftTwoSpaces = false;
        }
        if(startPos == 2){ //on the second left most edge of 1st row
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 2){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 3){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 4){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 5){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 6){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 7){
            leftTwoSpaces = false;
        }
        if(startPos == 2 + myChessBoard.numColsAndRows * 8){
            leftTwoSpaces = false;
        }
    }
}