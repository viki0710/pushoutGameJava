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

public class Field {

    /**
     * int that stores the type of pebble on a field.
     * 0 - none
     * 1 - black
     * 2 - white
     */
    private int pebble;

    public Field() {
        pebble = 0;
    }

    int getPebble() {
        return pebble;
    }

    void setPebble(int p) {
        this.pebble = p;
    }
}
