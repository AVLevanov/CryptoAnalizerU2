package com.javarush.levanov.utils;

import com.javarush.levanov.constant.Constants;
import com.javarush.levanov.controller.ValidationRequest;
import com.javarush.levanov.result.Status;
import com.javarush.levanov.result.ValidationResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public ValidationResult validateRequest(ValidationRequest validationRequest) {
        ValidationResult validationResult = new ValidationResult(validationRequest.action);
        validationResult.inPath = validateFilePath(validationRequest.inStringPath, validationResult);
        validationResult.outPath = Path.of(validationRequest.outStringPath);
        switch (validationRequest.action) {
            case ENCRYPT, DECRYPT -> validateKey(validationRequest.stringKey, validationResult);
            case ANALYZE -> {
                validationResult.dictionaryPath = validateFilePath(validationRequest.dictionaryStringPath, validationResult);
                validatePrecision(validationRequest.stringPrecision, validationResult);
            }
        }
        return validationResult;
    }

    private Path validateFilePath(String stringPath, ValidationResult validationResult) {
        Path path = Path.of(stringPath);
        try {
            if (Files.size(path) == 0) {
                validationResult.status = Status.FAIL;
                validationResult.message = Constants.EMPTY_FILE;
            }
        } catch (IOException e) {
            validationResult.status = Status.FAIL;
            validationResult.message = Constants.FILE_MUST_EXIST;
        }
        return path;
    }

    private void validateKey(String key, ValidationResult validationResult) {
        int validatedKey;
        try {
            validatedKey = Integer.parseInt(key);
            validationResult.key = validatedKey;
        } catch (NumberFormatException e) {
            validationResult.status = Status.FAIL;
            validationResult.message = Constants.INCORRECT_KEY;
        }
    }

    private void validatePrecision(String precision, ValidationResult validationResult) {
        double validatedPrecision = 0;
        try {
            validatedPrecision = Double.parseDouble(precision);
        } catch (NumberFormatException e) {
            validationResult.status = Status.FAIL;
            validationResult.message = Constants.INCORRECT_PRECISION;
        }
        if (validatedPrecision < 0.005 || validatedPrecision > 0.1) {
            validationResult.status = Status.FAIL;
            validationResult.message = Constants.INCORRECT_PRECISION;
        } else {
            validationResult.precision = validatedPrecision;
        }
    }
}
