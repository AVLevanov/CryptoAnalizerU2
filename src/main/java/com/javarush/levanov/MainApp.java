package com.javarush.levanov;

public class MainApp {
    static void main() {
        final String TEXT_PATH = "C:\\!_Не работа\\text.txt";
        final String ENCRYPTED_FILE_PATH = "C:\\!_Не работа\\encrypted.txt";
        final String DECRYPTED_FILE_PATH = "C:\\!_Не работа\\decrypted.txt";

        FileManager fileManager = new FileManager();
        String text = fileManager.readFile(TEXT_PATH);

        Cipher cipher = new Cipher();
        String encryptedText = cipher.encrypt(text, 3);
        fileManager.writeFile(encryptedText,ENCRYPTED_FILE_PATH);

        String encryptedTextFromFile = fileManager.readFile(ENCRYPTED_FILE_PATH);
        String decryptedText = cipher.decrypt(encryptedTextFromFile,3);
        fileManager.writeFile(decryptedText, DECRYPTED_FILE_PATH);
    }
}
