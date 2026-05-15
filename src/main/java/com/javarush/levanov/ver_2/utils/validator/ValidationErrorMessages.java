package com.javarush.levanov.ver2.utils.validator;

public interface ValidationErrorMessages {
//    String INCORRECT_MODE = "No action selected. Please enter a number between 1 and 3";
    String INCORRECT_KEY = "Invalid encryption key. Please enter an integer between 0 and " + Integer.MAX_VALUE;
    String FILE_MUST_EXIST = "Invalid file. The file must exist";
    String EMPTY_FILE = "File is empty";
}
