package com.javarush.levanov.controller;

import com.javarush.levanov.actions.Analyze;
import com.javarush.levanov.actions.BruteForce;
import com.javarush.levanov.actions.Decrypt;
import com.javarush.levanov.actions.Encrypt;
import com.javarush.levanov.result.Result;
import com.javarush.levanov.result.Status;
import com.javarush.levanov.result.ValidationResult;
import com.javarush.levanov.utils.Logger;
import com.javarush.levanov.utils.Validator;

import java.util.Scanner;

import static com.javarush.levanov.constant.Constants.*;

public class Controller {
    private final Console console;
    private final Logger logger;
    private final Validator validator;

    // Создаем console, logger и validator
    public Controller() {
        Scanner scanner = new Scanner(System.in);
        ValidationRequest validationRequest = new ValidationRequest();
        console = new Console(this, scanner, validationRequest);
        validator = new Validator();
        logger = new Logger();
        console.start();
    }

    // обработка поступившего запроса
    public void processRequest(ValidationRequest validationRequest) {
        Result result;
        ExecuteRequest executeRequest;
        ValidationResult validationResult = validator.validateRequest(validationRequest);
        if (validationResult.status == Status.FAIL) {
            console.showResult(validationResult);
            logger.logEvent(validationResult);
        } else {
            executeRequest = new ExecuteRequest(validationResult.action,
                    validationResult.key,
                    validationResult.precision,
                    validationResult.inPath,
                    validationResult.outPath,
                    validationResult.dictionaryPath);
            result = executeRequest(executeRequest);
            console.showResult(result);
            logger.logEvent(result);
        }
    }

    //отправляем запрос на исполнение, возвращаем результат
    public Result executeRequest(ExecuteRequest executeRequest) {
        Result result = new Result();
        result.action = executeRequest.action;
        switch (executeRequest.action) {
            case ENCRYPT -> {
                Encrypt encrypt = new Encrypt(executeRequest);
                result.message = String.format(ENCRYPT_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath, executeRequest.key);
            }
            case DECRYPT -> {
                Decrypt decrypt = new Decrypt(executeRequest);
                result.message = String.format(DECRYPT_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath, executeRequest.key);
            }
            case BRUTE_FORCE -> {
                BruteForce bruteForce = new BruteForce(executeRequest);
                result.message = String.format(BRUTE_FORCE_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath);
            }
            case ANALYZE -> {
                Analyze analyze = new Analyze(ALPHABET, executeRequest);
                result.message = String.format(ANALYZE_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath);
            }
        }
        result.status = Status.SUCCESS;
        return result;
    }
}

