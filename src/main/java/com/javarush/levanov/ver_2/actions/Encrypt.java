package com.javarush.levanov.ver_2.actions;

public class Encrypt extends AbstractAction {
    public Encrypt(String inPath, String outPath, int key) {
        this.code(inPath, outPath, key);
    }
}
