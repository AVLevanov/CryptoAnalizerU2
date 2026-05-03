package com.javarush.levanov;

import java.util.Scanner;

public class MainApp {
    static void main() {
        final String TEXT_PATH = "C:\\!_Не работа\\test.txt";
        final String ENCRYPTED_FILE_PATH = "C:\\!_Не работа\\encrypted.txt";
        final String DECRYPTED_FILE_PATH = "C:\\!_Не работа\\decrypted.txt";
        final String BRUTE_FORCE_DECRYPTED_FILE_PATH = "C:\\!_Не работа\\bruteForceDecrypted.txt";

//        ввод ключа с консоли
        Scanner console = new Scanner(System.in);
        Validator validator = new Validator();
        System.out.print("Введите ключ шифрования: ");
        String key;
        do {
            key = console.nextLine();
        } while (!validator.isKeyCorrect(key));

//        чтение файлa
        FileManager fileManager = new FileManager();
        String text = fileManager.readFile(TEXT_PATH);

//        шифрование
        Cipher cipher = new Cipher();
        String encryptedText = cipher.encrypt(text, validator.getShift());
        fileManager.writeFile(encryptedText, ENCRYPTED_FILE_PATH);

//        расшифровка
        String encryptedTextFromFile = fileManager.readFile(ENCRYPTED_FILE_PATH);
        String decryptedText = cipher.decrypt(encryptedTextFromFile, 3);
        fileManager.writeFile(decryptedText, DECRYPTED_FILE_PATH);

//        расшифровка BruteForce
        BruteForce bruteForce = new BruteForce();
        String bruteForceDecrypted = bruteForce.decryptByBruteForce(encryptedText, Cipher.ALPHABET_LENGTH);
        fileManager.writeFile(bruteForceDecrypted, BRUTE_FORCE_DECRYPTED_FILE_PATH);
    }
}
