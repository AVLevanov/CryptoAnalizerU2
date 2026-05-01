package com.javarush.levanov;

public class MainApp {
    static void main() {
//        final String TEXT_PATH = "C:\\Users\\alevanov\\IdeaProjects\\CryptoAnalyzerU2\\text";
//        final String DECRYPTED_FILE_PATH = "C:\\Users\\alevanov\\IdeaProjects\\CryptoAnalyzerU2\\decrypted.txt";
        final String testPath = "C:\\!_Не работа\\test.txt";
        final String destPath = "C:\\!_Не работа\\dest.txt";

        FileManager fileManager = new FileManager();
        String text = fileManager.readFile(testPath);
        System.out.println(text);
        System.out.println("\n***\n");

        Cipher cipher = new Cipher();
        String encryptedText = cipher.encrypt(text, 3);

        fileManager.writeFile(encryptedText,destPath);
        System.out.println(encryptedText);
    }
}
