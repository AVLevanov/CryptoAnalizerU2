package com.javarush.levanov.ver_1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public String readFile(String filePath) {
        Path path = Path.of(filePath);
        String text;
        try {
            text = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    public void writeFile(String content, String filePath) {
        Path path = Path.of(filePath);
        try {
            Files.writeString(path,content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
