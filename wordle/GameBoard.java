package org.cis120.wordle;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

import static org.cis120.wordle.Wordle.possibleWords;
import static org.cis120.wordle.Wordle.wordGrid;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Wordle wordleModel; // model for the game


    // Game constants
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 600;
    public static int currXPos = 0;
    public static int currYPos = 0;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) throws IOException {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        wordleModel = new Wordle(); // initializes model for the game


        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        addKeyListener(new KeyAdapter() {
            //@Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (currYPos == 0) {
                    }
                    else {
                        currXPos--;
                        wordGrid[currXPos][currYPos] = null;
                    }
                }
                else if(Character.isLetter(e.getKeyChar())) {
                    wordGrid[currXPos][currYPos] = new WordleCharacter(Character.toLowerCase(e.getKeyChar()));
                    if (currXPos < 4) {
                        currXPos++;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (currYPos == 4 ) {
                        String word = "";
                        for (int j = 0; j < 5; j++) {
                            word += wordGrid[currXPos][j].getCharacter();
                        }
                        if (possibleWords.contains(word)) {
                            currYPos++;
                            currXPos = 0;
                            // need to change color now
                        }
                        else {
                            //invalid word
                        }
                    }
                }

                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        wordleModel.reset();
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }



    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        g.drawLine(100, 0, 100, 600);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(500, 0, 500, 600);
        g.drawLine(0, 100, 500, 100);
        g.drawLine(0, 200, 500, 200);
        g.drawLine(0, 300, 500, 300);
        g.drawLine(0, 400, 500, 400);
        g.drawLine(0, 500, 500, 500);
        g.drawLine(0, 600, 500, 600);

        //Draw letters
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 5; j++) {
//                g.drawString(possibleWords.get(i).substring(j,j+1), j*100 + 50, i * 100 + 50);
//            }
//        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
