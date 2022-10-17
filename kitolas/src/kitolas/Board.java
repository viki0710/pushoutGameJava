/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitolas;

import java.util.Random;

/**
 *
 * @author Balla Viktória
 */
public class Board {
    private Field[][] board;
    private final int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
            }
        }
    }

    public Field getField(int x, int y) {
        return board[x][y];
    }
    
    public void setField(int x, int y, Field f) {
        this.board[x][y] = f;
    }

    public int getBoardSize() {
        return boardSize;
    }
    
    /**
     * Sets a pebble in a given x y location.
     * @param x - x coordinate
     * @param y - y coordinate
     * @param colour - colour of pebble
     */
    public void setPebbleXY(int x, int y, int colour) {
        this.board[x][y].setPebble(colour);
    }
    
    /**
     * Generates the original set of pebbles.
     * Gives random x y coordinates, if empty, makes pebble.
     * Continues until pebble number equals board size.
     * @param colour - colour of pebble being generated
     */
    public void generatePebbles(int colour) {
        int i = 0;
        while (i < boardSize) {
            Random random = new Random();
            int x = random.nextInt(boardSize);   
            int y = random.nextInt(boardSize);   
            if (this.board[x][y].getPebble() == 0){
                setPebbleXY(x, y, colour);
                i++;
            }
        }
    }
    
    /**
     * It counts the number of a given pebble
     * @param colour - colour of pebble being counted
     * @return c - number of pebbles
     */
    public int countPebbles(int colour) {
        int c = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (this.board[i][j].getPebble() == colour) c++;
            }
        }
        return c;
    }
    
    /**
     * Shifts the pebbles to the given direction
     * @param x - x coordinate of chosen pebble
     * @param y - y coordinate of chosen pebble
     * @param dir - direction of shift
     */
    public void shiftPebbles(int x, int y, String dir) {
        int pebbleTemp[] = new int[boardSize];
        switch (dir) {
            case "UP": {
                for (int i = 0; i < boardSize; i++) {
                    pebbleTemp[i] = board[x][i].getPebble();
                }
                for (int i = 0; i <= y; i++) {
                    if (pebbleTemp[i] == y) {
                        pebbleTemp[i] = 0;
                    } else pebbleTemp[i] = pebbleTemp[i+1];
                }
                for (int i = 0; i < boardSize; i++) {
                    board[x][i].setPebble(pebbleTemp[i]);
                }
                break;
            }
            case "DOWN": {
                for (int i = 0; i < boardSize; i++) {
                    pebbleTemp[i] = board[x][i].getPebble();
                }
                for (int i = boardSize-1; i >= y; i--) {
                    if (pebbleTemp[i] == y) {
                        pebbleTemp[i] = 0;
                    } else pebbleTemp[i] = pebbleTemp[i-1];
                }
                for (int i = 0; i < boardSize; i++) {
                    board[x][i].setPebble(pebbleTemp[i]);
                }
                break;
            } 
            case "LEFT": {
                for (int i = 0; i < boardSize; i++) {
                    pebbleTemp[i] = board[i][y].getPebble();
                }
                for (int i = 0; i <= x; i++) {
                    if (pebbleTemp[i] == x) {
                        pebbleTemp[i] = 0;
                    } else pebbleTemp[i] = pebbleTemp[i+1];
                }
                for (int i = 0; i < boardSize; i++) {
                    board[i][y].setPebble(pebbleTemp[i]);
                }
                break;
            } 
            case "RIGHT": {
                for (int i = 0; i < boardSize; i++) {
                    pebbleTemp[i] = board[i][y].getPebble();
                }
                for (int i = boardSize-1; i >= x; i--) {
                    if (pebbleTemp[i] == x) {
                        pebbleTemp[i] = 0;
                    } else pebbleTemp[i] = pebbleTemp[i-1];
                }
                for (int i = 0; i < boardSize; i++) {
                    board[i][y].setPebble(pebbleTemp[i]);
                }                
                break;
            } 
            default: {
                break;
            }
        }
    }
        
    /**
     * prints out the board on console
     */
    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(getField(i, j).getPebble());
            }
            System.out.print("\n");
        }
    }

}