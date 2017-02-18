package com.rohananand.firedemo;

/**
 * Created by Rohan_Anand on 13/02/2017.
 */

public class Word {

    public String wordId;
    public String word;
    public String definition;

    public Word() {

    }

    public Word(String word, String wordDef) {
        this.word = word;
        this.definition = wordDef;
    }

    public Word(String wordId, String word, String wordDef) {
        this.wordId = wordId;
        this.word = word;
        this.definition = wordDef;
    }
}
