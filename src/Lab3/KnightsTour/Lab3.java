/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab3.KnightsTour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * *
 * @author Jared Kamp
 * CSCI 232
 * Lab 3 - Knight's Tour
 */

/**
 * *
 * @author Jared Kamp
 CSCI 232
 Lab 3 - KnightsTour's Tour
 */
public class Lab3 {
////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws IOException
	{
            KnightsTour knightsTour = null;
            chessBoard chessboard = null;
            ChessPiece knight = null; 
            int choice;
            int value;
            int boardSize = 0;
            while(true)
            {
                if(chessboard == null) //we must enter a chess board size first.
                {
                    System.out.println("Welcome to Lab 3 - The Knight's Tour.");
                    choice = 's';
                }
                else
                {
                    System.out.print("Enter first letter of ");
                    System.out.print("size, position, or finish: ");
                    choice = getChar();
                }
                switch(choice)
                {
                    case 's':
                        System.out.println("Enter the size of the chess board:");
                        boardSize = getSize();
                        chessboard = new chessBoard(boardSize);
                        System.out.println("Enter the Knight's starting position on the chess board (1 to " + chessboard.knightArray.length + "): ");
                        value = getSize();
                        knightsTour = new KnightsTour(value, chessboard);
                        System.out.println("Starting at " + value + ".");
                        //lock in the starting position
                        knight = new ChessPiece(value, 0);
                        knight.setEnd(value);
                        chessboard.addFirstKnight(knight);
                        knightsTour.tour[0] = knight;
                        knight.setEnd(knightsTour.nextMove(value));
                        if(knightsTour.success)
                            System.out.println("SUCCESS: ");                        
                        else
                            System.out.println("FAILURE: ");
                        System.out.println("Total number of moves = " + chessboard.moves());
                        System.out.println("Moving Sequence:" + knightsTour.orderOfMoves());
                        break;
                    case 'p':
                        chessboard = new chessBoard(boardSize);
                        System.out.println("Enter the Knight's starting position on the chess board (1 to " + chessboard.knightArray.length + "): ");
                        value = getSize();
                        knightsTour = new KnightsTour(value, chessboard);
                        knightsTour.tour[0] = knight;
                        knight.setEnd(knightsTour.nextMove(value));
                        if(knightsTour.success)
                            System.out.println("SUCCESS: ");                        
                        else
                            System.out.println("FAILURE: ");
                        System.out.println("Total number of moves = " + chessboard.moves());
                        System.out.println("Moving Sequence:" + knightsTour.orderOfMoves());
                        break;
                    case 'f':
                        System.out.println("Thank you for playing!");
                        System.exit(0);
                        break;
                    default:
                        System.out.print("Invalid Entry.\n");
                }  // end switch
            }  // end while
	}  // end main()
	// -------------------------------------------------------------
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	// -------------------------------------------------------------
	public static int getSize() throws IOException
	{
		int size = 0;
		size = getInt();
                return size;
        }
	// -------------------------------------------------------------
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	//-------------------------------------------------------------
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
	// -------------------------------------------------------------
} 
