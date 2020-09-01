package com.xnea.memorid.model;

public class Card {

    private String word, description;
    private int aciertos, fallos;

    public Card(){

    }

    public Card(String word, String description, int aciertos, int fallos) {
        this.word = word;
        this.description = description;
        this.aciertos = aciertos;
        this.fallos = fallos;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getFallos() {
        return fallos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }
}
