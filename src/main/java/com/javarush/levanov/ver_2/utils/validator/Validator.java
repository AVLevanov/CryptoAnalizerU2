package com.javarush.levanov.ver_2.utils.validator;

import com.javarush.levanov.ver_2.controller.Action;
import com.javarush.levanov.ver_2.controller.Request;
import com.javarush.levanov.ver_2.controller.result.Status;
import com.javarush.levanov.ver_2.controller.result.ValidationResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


// Проверяет данные введенные пользователем
public class Validator {

    // Валидируем параметры запроса. Возвращаем результат валидации: статус и (ошибки или сконструированный запрос)
    public ValidationResult validateParameters(Action action, String[] parameters) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.action = action;
        validationResult.status = Status.SUCCESS;
        switch (action) {
            case START, EXIT -> addRequest(validationResult, action, parameters);
            case ENCRYPT, DECRYPT -> {
                validateFile(parameters, validationResult);
                int key = validateKey(parameters, validationResult);
                addRequest(validationResult, action, parameters, key);
            }
            case BRUTE_FORCE -> {
                validateFile(parameters, validationResult);
                addRequest(validationResult, action, parameters);
            }
        }
        return validationResult;
    }

    // проверяем, что файл существует, не является директорией и не пуст
    private static void validateFile(String[] parameters, ValidationResult validationResult) {
        try {
            if (Files.size(Path.of(parameters[0])) == 0) {
                validationResult.status = Status.FAIL;
                validationResult.message = ValidationErrorMessages.EMPTY_FILE;
            }
        } catch (IOException e) {
            validationResult.status = Status.FAIL;
            validationResult.message = ValidationErrorMessages.FILE_MUST_EXIST;
        }
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

    // конструируем запрос, там где ключ не нужен
    private static void addRequest(ValidationResult validationResult, Action action, String[] parameters) {
        if (validationResult.status == Status.SUCCESS) {
            validationResult.request = new Request(action, parameters[0], parameters[1]);
        }
    }

    // конструируем запрос, там где ключ нужен
    private static void addRequest(ValidationResult validationResult, Action action, String[] parameters, int key) {
        if (validationResult.status == Status.SUCCESS) {
            validationResult.request = new Request(action, parameters[0], parameters[1], key);
        }
    }
}
