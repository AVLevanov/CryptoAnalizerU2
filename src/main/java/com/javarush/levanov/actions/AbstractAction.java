package com.javarush.levanov.actions;

import com.javarush.levanov.utilApps.Coder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractAction {

    // replacing symbols (encrypting\decrypting) using existing cipher
    public void code(Coder coder, Path inPath, Path outPath) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(inPath);
             BufferedWriter bufferedWriter = Files.newBufferedWriter(outPath)
        ) {
            while (bufferedReader.ready()) {
                char symbol = Character.toLowerCase((char) bufferedReader.read());
                char encryptedSymbol;
                if (coder.getCipher().get(symbol) != null) {
                    encryptedSymbol = coder.getCipher().get(symbol);
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
