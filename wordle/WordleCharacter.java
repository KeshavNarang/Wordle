package org.cis120.wordle;

public class WordleCharacter {
    private char inputChar;
    private int charStatus;
    public WordleCharacter(char c, int n) {
        inputChar = c;
        charStatus = n;
    }

    public WordleCharacter(char c) {
        inputChar = c;
        charStatus = -1;
    }

    public char getCharacter () {
        return inputChar;
    }

    public int getCharStatus () {
        return charStatus;
    }

    public void setCharStatus (int status) {
        charStatus = status;
    }
}
