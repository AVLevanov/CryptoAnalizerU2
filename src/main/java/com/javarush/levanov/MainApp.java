package com.javarush.levanov;

import java.util.Scanner;

public class MainApp {
    static void main() {
        char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
                'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
                'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
                'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
                'Ы', 'Э', 'Ю', 'Я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

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

//        чтение файл
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

        BruteForce bruteForce = new BruteForce();
        String bruteForceDecrypted = bruteForce.decryptByBruteForce(encryptedText, ALPHABET);
        fileManager.writeFile(bruteForceDecrypted, BRUTE_FORCE_DECRYPTED_FILE_PATH);
    }
}
