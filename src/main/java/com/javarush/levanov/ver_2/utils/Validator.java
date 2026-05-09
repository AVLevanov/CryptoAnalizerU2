package com.javarush.levanov.ver_2.utils;

import com.javarush.levanov.ver_2.controller.Request;
import com.javarush.levanov.ver_2.controller.result.Status;
import com.javarush.levanov.ver_2.controller.result.ValidationErrorMessages;
import com.javarush.levanov.ver_2.controller.result.ValidationResult;

public class Validator {
    // Валидируем параметры запроса. Возвращаем результат валидации: статус и либо ошибки, либо сконструированный запрос
    public ValidationResult validateParameters(int mode, String[] parameters) {
        ValidationResult validationResult = new ValidationResult();
        switch (mode) {
            case 1, 2 -> {
                // проверить, что входной файл существует

                // проверяем, что ключ это число
                int key = 0;
                try {
                    key = Integer.parseInt(parameters[2]);
                    validationResult.status = Status.SUCCESS;
                } catch (NumberFormatException e) {
                    validationResult.status = Status.FAIL;
                    validationResult.message = ValidationErrorMessages.INCORRECT_KEY;
                }
                if (validationResult.status == Status.SUCCESS) {
                    validationResult.request = new Request(mode, parameters[0], parameters[1], key);
                }
            }
            case 3 -> {
                // проверить, что входной файл существует
                validationResult.status = Status.SUCCESS;
                validationResult.request = new Request(mode, parameters[0], parameters[1]);
            }
        }
        return validationResult;
    }
}
