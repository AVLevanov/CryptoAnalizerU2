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

import static com.javarush.levanov.constant.Constants.*;

public class Controller {
    private Console console;
    private Logger logger;
    private Validator validator;

    // Создаем console, logger и validator
    public void start() {
        logger = new Logger();
        validator = new Validator();
        console = new Console(this);
    }

    // обработка поступившего запроса
    public void processRequest(ValidationRequest validationRequest, Console console) {
        Result result = new Result();
        ExecuteRequest executeRequest;
        switch (validationRequest.action) {
            case Action.START, Action.EXIT -> {
                result.action = validationRequest.action;
                logger.logEvent(result);
            }
            case Action.ENCRYPT, Action.DECRYPT, Action.BRUTE_FORCE, Action.ANALYZE -> {
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
        }
    }

    //отправляем запрос на исполнение, возвращаем результат
    public Result executeRequest(ExecuteRequest executeRequest) {
        Result result = new Result();
        result.action = executeRequest.action;
        switch (executeRequest.action) {
            case ENCRYPT -> {
                Encrypt.execute(executeRequest);
                result.message = String.format(ENCRYPT_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath, executeRequest.key);
            }
            case DECRYPT -> {
                Decrypt.execute(executeRequest);
                result.message = String.format(DECRYPT_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath, executeRequest.key);
            }
            case BRUTE_FORCE -> {
                BruteForce.execute(executeRequest);
                result.message = String.format(BRUTE_FORCE_SUCCESS_MESSAGE, executeRequest.inPath, executeRequest.outPath);
            }
            case ANALYZE -> {
                Analyze analyze = new Analyze(ALPHABET, executeRequest);
                result.message = String.format(ANALYZE_SUCCESS_MESSAGE,executeRequest.inPath ,executeRequest.outPath);
            }
        }
        result.status = Status.SUCCESS;
        return result;
    }
}

