package com.javarush.levanov.ver_2.actions;

public class Decrypt extends AbstractAction {
    public static void execute(String inPath, String outPath, int key) {
        code(inPath, outPath, -key);
    }
}
