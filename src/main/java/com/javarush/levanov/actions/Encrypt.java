package com.javarush.levanov.actions;

public class Encrypt extends AbstractAction {
    public Encrypt(String inPath, String outPath, int key) {
        this.code(inPath, outPath, key);
    }
}
