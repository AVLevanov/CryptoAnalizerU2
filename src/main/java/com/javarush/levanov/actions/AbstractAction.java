package com.javarush.levanov.actions;

import com.javarush.levanov.constant.Coder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractAction {

    public static void codeWithKey(Path inPath, Path outPath, int key) {
        Coder.setKey(key);
        code(inPath, outPath);
    }

    public static void code(Path inPath, Path outPath) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(inPath);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(outPath)
        ) {
            while (bufferedReader.ready()) {
                char symbol = Character.toLowerCase((char) bufferedReader.read());
                char encryptedSymbol;
                if (Coder.getCoder().get(symbol) != null) {
                    encryptedSymbol = Coder.getCoder().get(symbol);
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
