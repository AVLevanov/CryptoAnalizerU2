package com.javarush.levanov.actions;

import com.javarush.levanov.constant.Coder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractAction {

    public void code(String inPath, String outPath, int key) {
        Coder coder = new Coder(key);
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(inPath));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of(outPath))
        ) {
            while (bufferedReader.ready()) {
                char symbol = (char) bufferedReader.read();
                char encryptedSymbol;
                if (coder.cipher.get(symbol) != null) {
                    encryptedSymbol = coder.cipher.get(symbol);
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
