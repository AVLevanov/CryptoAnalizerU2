package com.javarush.levanov.actions;

public class Decrypt extends AbstractAction {
    public Decrypt(String inPath, String outPath, int key) {
        this.code(inPath, outPath, -key);
    }
}
