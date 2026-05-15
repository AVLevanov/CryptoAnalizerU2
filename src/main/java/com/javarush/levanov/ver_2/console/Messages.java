package com.javarush.levanov.ver_2.console;

public interface Messages {

    String RESET = "\u001B[0m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";

    String INITIAL_MESSAGE = """
            
            ---------------
            CHOOSE ACTION:
            1. Encrypt
            2. Decrypt
            3. Brute Force
            For exit press any other key
            ---------------""";

    String SUCCESS_RESULT = "---------------\nAction: %s\nStatus: " + GREEN + "%s\n" + RESET + "Message: %s";

    String FAILED_RESULT = "---------------\nAction: %s\nStatus: " + RED + "%s\n" + RESET + "Message: %s";

    String[][] QUESTIONS = {
            {"Enter text file path: ", "Enter encrypted file path: ", "Enter key: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: ", "Enter key: "},
            {"Enter encrypted file path: ", "Enter decrypted file path: "}
    };
}
