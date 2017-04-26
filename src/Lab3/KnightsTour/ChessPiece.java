/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3.KnightsTour;

/**
 *
 * @author Jared Kamp
 * CSCI 232
 * Lab 3 - Knight's Tour
 */
public class ChessPiece {
    private int number;
    private int start;
    private int end;
    public ChessPiece(int startLocation, int num){
        number = num;
        start = startLocation;
    }
    public void setEnd(int endLocation){
        end = endLocation;
    }
    public int getNumber(){
        return number;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
}