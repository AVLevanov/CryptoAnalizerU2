package com.javarush.levanov.ver_2;

import com.javarush.levanov.ver_2.actions.BruteForce;
import com.javarush.levanov.ver_2.actions.Decrypt;
import com.javarush.levanov.ver_2.actions.Encrypt;

public class Runner {
    static void main() {

        final String TEXT_PATH = "C:\\!_Не работа\\test.txt";
        final String ENCRYPTED_FILE_PATH = "C:\\!_Не работа\\encrypted.txt";
        final String DECRYPTED_FILE_PATH = "C:\\!_Не работа\\decrypted.txt";
        final String BRUTE_FORCE_DECRYPTED_FILE_PATH = "C:\\!_Не работа\\bruteForceDecrypted.txt";

        Encrypt encrypt = new Encrypt(TEXT_PATH, ENCRYPTED_FILE_PATH, 3);
        Decrypt decrypt = new Decrypt(ENCRYPTED_FILE_PATH, DECRYPTED_FILE_PATH, 3);
        BruteForce bruteForce = new BruteForce(ENCRYPTED_FILE_PATH, BRUTE_FORCE_DECRYPTED_FILE_PATH);
    }
}
