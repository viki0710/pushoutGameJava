/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitolas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;            
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.border.LineBorder;

/**
 *
 * @author Balla Viktória
 */
public class BoardGUI {
    
    private Board board;
    private JButton[][] buttons;
    private JPanel boardPanel;
    private JLabel turnLabel;
    private ArrayList<Point> points;
    private int turn;
    boolean waitForSignal = false;
    String dir = "";

    private Random random = new Random();
    private int clickNum = 0;

    private final int NUM_COLORED_FIELDS = 4;

    public BoardGUI(int boardSize) {
        turn = 1;
        board = new Board(boardSize);
        board.generatePebbles(1);
        board.generatePebbles(2);
        System.out.println("number of black pebbles: " + board.countPebbles(1));
        System.out.println("number of white pebbles: " + board.countPebbles(2));
        board.printBoard();
        
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.addKeyListener(new CustomKeyListener());
                button.setPreferredSize(new Dimension(80, 80));
                button.setBorder(new LineBorder(Color.BLACK));
                buttons[i][j] = button;
                if (board.getField(i, j).getPebble() == 0 || board.getField(i, j).getPebble() == 2) {
                    button.setEnabled(false);                    
                }
                
                if (board.getField(i, j).getPebble() != 0) {
                    button.setText("" + board.getField(i, j).getPebble());                 
                }

                boardPanel.add(button);
                
                turnLabel = new JLabel("Current turn: 1");
                turnLabel.setHorizontalAlignment(JLabel.RIGHT);
            }
        }
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public void getNearby(int x, int y) {
        buttons[x][y].setBorder(new LineBorder(Color.RED));
        if (x > 0) buttons[x-1][y].setBorder(new LineBorder(Color.RED));
        if (y > 0) buttons[x][y-1].setBorder(new LineBorder(Color.RED));
        if (x < buttons.length-1) buttons[x+1][y].setBorder(new LineBorder(Color.RED));
        if (y < buttons.length-1) buttons[x][y+1].setBorder(new LineBorder(Color.RED));
        waitForSignal = true;
        
        getDirection(x, y);
    }
    
    public void getDirection(int x, int y) {
        if (waitForSignal == false) {
            board.shiftPebbles(x, y, dir);
        }
    }
    
    
    public void resetBorders() {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = buttons[i][j];
                button.setBorder(new LineBorder(Color.BLACK));
                boardPanel.add(button);               
            }
        }
    }
    
    public void refresh(int playerNum) {
        
        if (turn == 1) {
            turn = 2;
        } else turn = 1;
        
        System.out.println("current turn: " + turn);
        turnLabel.setText("Current turn: " + turn);
        
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = buttons[i][j];
                //button.setBorder(new LineBorder(Color.BLACK));
                
                
                /*if (board.getPos(i, j).getPebble() != turn) {
                    button.setEnabled(false);                    
                } else button.setEnabled(true);*/
                                
                boardPanel.add(button);               
            }
        }
    }
    
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    
    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {            
            System.out.println("coordinates: " + x + ";" + y);
            resetBorders();
            getNearby(x, y);
        }
    }
    
    class CustomKeyListener implements KeyListener {
        public void keyPressed(KeyEvent e){                                          
            int key = e.getKeyCode();                                                
            switch (key) {
                case KeyEvent.VK_RIGHT -> {
                    if (waitForSignal == true) {
                        System.out.println("The right arrow key is pressed");
                        dir = "RIGHT";
                        waitForSignal = false;
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (waitForSignal == true) {
                        System.out.println("The left arrow key is pressed");
                        dir = "LEFT";
                        waitForSignal = false;
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (waitForSignal == true) {
                        System.out.println("The up arrow key is pressed");
                        dir = "UP";
                        waitForSignal = false;
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (waitForSignal == true) {
                        System.out.println("The down arrow key is pressed");
                        dir = "DOWN";
                        waitForSignal = false;
                    }
                }
                default -> {
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
            int key = e.getKeyCode();                                                
            switch (key) {
                case KeyEvent.VK_RIGHT -> {
                    if (waitForSignal == true) {
                        System.out.println("The right arrow key is pressed");
                        dir = "RIGHT";
                        waitForSignal = false;
                    }
                }
                case KeyEvent.VK_LEFT -> {
                    if (waitForSignal == true) {
                        System.out.println("The left arrow key is pressed");
                        dir = "LEFT";
                        waitForSignal = false;
                    }
                }
                case KeyEvent.VK_UP -> {
                    if (waitForSignal == true) {
                        System.out.println("The up arrow key is pressed");
                        dir = "UP";
                        waitForSignal = false;
                    }
                }
                case KeyEvent.VK_DOWN -> {
                    if (waitForSignal == true) {
                        System.out.println("The down arrow key is pressed");
                        dir = "DOWN";
                        waitForSignal = false;
                    }
                }
                default -> {
                }
            }
        }
    }
    
}
    

