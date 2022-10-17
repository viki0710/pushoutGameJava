/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitolas;

/**
 *
 * @author Balla Viktória
 */
public class Player {
    
    private int colour;
    private int numOfPebbles;
    
    public Player(int c, int n) {
        this.colour = c;
        this.numOfPebbles = n;
    }
    
    int getColour() {
        return colour;
    }

    void setColour(int c) {
        this.colour = c;
    }
    
}
