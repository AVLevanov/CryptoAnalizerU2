package com.javarush.levanov;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public String readFile(String filePath) {
        String text = null;
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {
            text = br.readAllAsString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    public void writeFile(String content, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
