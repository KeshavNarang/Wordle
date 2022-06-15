package org.cis120.wordle;

/**
 * CIS 120 HW09 -
 *
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 *
 */
public class Wordle {

    public static ArrayList<String> possibleWords;
    private static String chosenWord;
    //private static String userGuess;

    public static WordleCharacter [][] wordGrid = new WordleCharacter[5][6];

    /**
     * Constructor sets up game state.
     */
    public Wordle() {
        possibleWords = getPossibleWords();
        //userGuess = guess;
        chosenWord  = getRandomTarget();

    }

//    public static char askLetter ()  {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter your letter here: ");
//        char userInput = scan.next().charAt(0);
//        while (Character.isLetter(userInput) == false) {
//            userInput = scan.next().charAt(0);
//        }
//        return Character.toLowerCase(userInput);
//    }

    public ArrayList<String> getPossibleWords() {
        ArrayList<String> possibleTargets = new ArrayList<String>();
        Scanner readWords = null;
        try {
            readWords = new Scanner(new File("src/main/java/org/cis120/wordle/words.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(readWords.hasNext()) {
            possibleTargets.add(readWords.next());
        }
        return possibleTargets;
    }

    public String getRandomTarget() {
        String randomWord = possibleWords.get((int)(Math.random() * possibleWords.size()));
        return randomWord;
    }

    public static int determineStatus (char c, int index) {
        for (int i = 0; i < 5; i++) {
            if (i == index) {
                if (c == (chosenWord.charAt(i))) {
                    return 0;
                }
            } else if (i != index) {
                if (c == (chosenWord.charAt(i))) {
                    return 1;
                }
            }
        }
        return 2;
    }

    public static WordleCharacter[][] createArray(String guess, int guessNumber) {

        for (int i = 0; i < 5; i++) {
            wordGrid[guessNumber][i] = new WordleCharacter(guess.charAt(i), determineStatus(guess.charAt(i), 0));
        }
        return wordGrid;
    }

    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
        int numGuess = 0;
        while (numGuess < 6) {

//            if (guess.length() != 5 || ! possibleWords.contains(guess)) {
//
//            }
//            else {
//                createArray(guess, numGuess);
//                if(checkWord(guess)) {
//                    //game ends and user wins
//                }
//                numGuess ++;
//            }
//        }

            if (numGuess > 6) {
                //game ends and user loses
            }

        }
    }

//    public static boolean checkWord(String guess) {
//        if(guess.equals(chosenWord)) {
//            return true;
//        }
//        return false;
//    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {

    }
}
