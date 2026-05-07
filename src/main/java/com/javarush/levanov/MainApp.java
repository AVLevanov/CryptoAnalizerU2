package com.javarush.levanov;

import com.javarush.levanov.actions.Decrypt;
import com.javarush.levanov.actions.Encrypt;

public class MainApp {
    static void main() {

        final String TEXT_PATH = "C:\\!_Не работа\\test.txt";
        final String ENCRYPTED_FILE_PATH = "C:\\!_Не работа\\encrypted.txt";
        final String DECRYPTED_FILE_PATH = "C:\\!_Не работа\\decrypted.txt";
//        final String BRUTE_FORCE_DECRYPTED_FILE_PATH = "C:\\!_Не работа\\bruteForceDecrypted.txt";

        Encrypt encrypt = new Encrypt(TEXT_PATH, ENCRYPTED_FILE_PATH, 1000);
        Decrypt decrypt = new Decrypt(ENCRYPTED_FILE_PATH, DECRYPTED_FILE_PATH, 1000);
/*
//        ввод ключа с консоли
        Scanner console = new Scanner(System.in);
        Validator validator = new Validator();
        System.out.print("Введите ключ шифрования: ");
        String inputKey;
        do {
            inputKey = console.nextLine();
        } while (!validator.isKeyCorrect(inputKey));

//        чтение файлa
        FileManager fileManager = new FileManager();
        String text = fileManager.readFile(TEXT_PATH);

//        шифрование
        Cipher cipher = new Cipher();
        String encryptedText = cipher.encrypt(text, validator.getKey()); // отрефакторить? Валидатор не должен хранить ключ
        fileManager.writeFile(encryptedText, ENCRYPTED_FILE_PATH);

//        расшифровка
        String encryptedTextFromFile = fileManager.readFile(ENCRYPTED_FILE_PATH);
        String decryptedText = cipher.decrypt(encryptedTextFromFile, 3);
        fileManager.writeFile(decryptedText, DECRYPTED_FILE_PATH);

//        расшифровка BruteForce
//        System.out.println(LocalTime.now());
        BruteForce bruteForce = new BruteForce();
        String bruteForceDecrypted = bruteForce.decryptByBruteForce(encryptedText, Cipher.ALPHABET_LENGTH, cipher);
        fileManager.writeFile(bruteForceDecrypted, BRUTE_FORCE_DECRYPTED_FILE_PATH);
//        System.out.println(LocalTime.now());


 */
    }
}
