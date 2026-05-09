package com.javarush.levanov.ver_2.console;

public interface Messages {
    String INITIAL_MESSAGE = """
        ---------------
        CHOOSE ACTION:
        1. Encrypt
        2. Decrypt
        3. Brute Force
        For exit press any other key
        ---------------\n""";

    String SHOW_RESULT = """
        ---------------
        Status: %s
        Message: %s
        ---------------\n""";

    String[][] QUESTIONS = {
            {"Enter text file path: ", "Enter encrypted file path: ", "Enter key: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: ", "Enter key: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: "}
    };
}
