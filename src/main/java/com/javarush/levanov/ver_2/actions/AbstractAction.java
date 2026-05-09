package com.javarush.levanov.ver_2.actions;

import com.javarush.levanov.ver_2.constant.Coder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractAction {

    public static void code(String inPath, String outPath, int key) {
        Coder.setKey(key);
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(inPath));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(outPath))
        ) {
            while (bufferedReader.ready()) {
                char symbol = (char) bufferedReader.read();
                char encryptedSymbol;
                if (Coder.getCipher().get(symbol) != null) {
                    encryptedSymbol = Coder.getCipher().get(symbol);
                } else {
                    encryptedSymbol = symbol;
                }
                bufferedWriter.write(encryptedSymbol);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
