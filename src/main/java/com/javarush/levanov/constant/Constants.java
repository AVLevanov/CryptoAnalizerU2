package com.javarush.levanov.constant;

import java.nio.file.Path;

public interface Constants {
    Character[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    // Keywords and characters for BruteForce
    String[] KEY_WORDS = {"и", "в", "на", "не", "я", "он", "она", "человек", "время", "год", "был"};
    String[] PRECEDING_CHARACTERS = {" ", "\n"};
    String[] NEXT_CHARACTERS = {" ", ".", ",", ";", ":", "\"", "!", "?", "\n"};

    // Logger
    String LOD_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    String LINE_WITH_MESSAGE = "%s   action: %s   status: %s   message: %s\n";
    String LINE_WITHOUT_MESSAGE = "%s   action: %s\n";

    // Console menu messages
    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    int MAX_QUESTION_NUMBERS = 5;
    String INITIAL_MESSAGE = """
            
            ---------------
            CHOOSE ACTION:
            1. Encrypt
            2. Decrypt
            3. Brute Force
            4. Analyze
            For exit press any other key
            ---------------""";
    String[][] QUESTIONS = {
            {"Enter text file path: ", "Enter encrypted file path: ", "Enter key: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: ", "Enter key: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: ", "Enter dictionary file path: ", "Enter precision between 0.005 and 0.1: "}
    };
    String SUCCESS_RESULT = "---------------\nAction: %s\nStatus: " + GREEN + "%s\n" + RESET + "Message: %s";
    String FAILED_RESULT = "---------------\nAction: %s\nStatus: " + RED + "%s\n" + RESET + "Message: %s";
    String ENCRYPT_SUCCESS_MESSAGE = "%s has been successfully encrypted to file %s with key=%d";
    String DECRYPT_SUCCESS_MESSAGE = "%s has been successfully decrypted to file %s with key=%d";
    String BRUTE_FORCE_SUCCESS_MESSAGE = "File %s has been successfully decrypted by brute force method to file %s";
    String ANALYZE_SUCCESS_MESSAGE = "File %s has been successfully decrypted by analyze method to file %s";

    // Analyze
    String BEST_ALPHABET_WAS_FOUND = "\tFound best cipher with percision=%f, attempts count %d \n";
    int MATRIX_REVERT_COUNT_BY_ANALYZE = 7;

    // Validator error messages
    String INCORRECT_KEY = "Invalid encryption key. Please enter an integer between 0 and " + Integer.MAX_VALUE;
    String INCORRECT_PRECISION = "Invalid precision. Precision must be 0.005 < precision < 0.1";
    String FILE_MUST_EXIST = "Invalid file. File must exist";
    String EMPTY_FILE = "File is empty";

    // Environment
    String LOG_PATH = "/Users/alevanov/!_Не работа/log.txt";
}
