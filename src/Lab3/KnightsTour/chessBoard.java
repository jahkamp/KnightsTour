/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3.KnightsTour;

/**
 * *
 * @author Jared Kamp
 * CSCI 232
 * Lab 3 - Knight's Tour
 */

// This class stores our knights. We're done when the chessBoard array is full.
public class chessBoard{
    private int numberOfMoves;
    public ChessPiece[] knightArray;
    public int numColsAndRows;
    public chessBoard(int size)
    {
        numberOfMoves = 0;
        knightArray = new ChessPiece[size*size]; //knightArray keeps track of open spots
        numColsAndRows = size;
    }    
    public void addFirstKnight(ChessPiece knight){ //add first entry
        knightArray[knight.getStart()-1] = knight;
    }
    public boolean addKnight(int position){ //is it an empty position?
        if(knightArray[position-1] == null)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public void removeKnight(int i){
        if(i > 0 && i < knightArray.length)
            if(knightArray[i - 1] != null)
                knightArray[i - 1] = null;
    }
    public void incrementMoves(){
        numberOfMoves++;
    }
    public int moves(){
        return numberOfMoves;
    }
}   