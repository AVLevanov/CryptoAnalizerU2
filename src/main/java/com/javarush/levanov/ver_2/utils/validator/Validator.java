package com.javarush.levanov.ver_2.utils.validator;

import com.javarush.levanov.ver_2.controller.Request;
import com.javarush.levanov.ver_2.controller.result.Status;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    // Валидируем параметры запроса. Возвращаем результат валидации: статус и (ошибки или сконструированный запрос)
    public ValidationResult validateParameters(int mode, String[] parameters) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.status = Status.SUCCESS;
        switch (mode) {
            case 1, 2 -> {
                validateFile(parameters, validationResult);
                int key = validateKey(parameters, validationResult);
                if (validationResult.status == Status.SUCCESS) {
                    validationResult.request = new Request(mode, parameters[0], parameters[1], key);
                }
            }
            case 3 -> {
                validateFile(parameters, validationResult);
                if (validationResult.status == Status.SUCCESS) {
                    validationResult.request = new Request(mode, parameters[0], parameters[1]);
                }
            }
        }
        return validationResult;
    }

    // проверяем и возвращаем значение ключа
    private static int validateKey(String[] parameters, ValidationResult validationResult) {
        int key = 0;
        try {
            key = Integer.parseInt(parameters[2]);
        } catch (NumberFormatException e) {
            validationResult.status = Status.FAIL;
            validationResult.message = ValidationErrorMessages.INCORRECT_KEY;
        }
        return key;
    }

    // проверяем что файл существует
    private static void validateFile(String[] parameters, ValidationResult validationResult) {
        if (!Files.exists(Path.of(parameters[0]))) {
            validationResult.status = Status.FAIL;
            validationResult.message = ValidationErrorMessages.INCORRECT_FILE;
        }
    }
}
