package com.javarush.levanov.ver_2.controller;

import com.javarush.levanov.ver_2.actions.BruteForce;
import com.javarush.levanov.ver_2.actions.Decrypt;
import com.javarush.levanov.ver_2.actions.Encrypt;
import com.javarush.levanov.ver_2.console.ConsoleApp;
import com.javarush.levanov.ver_2.controller.result.*;
import com.javarush.levanov.ver_2.utils.Logger;
import com.javarush.levanov.ver_2.controller.result.Result;
import com.javarush.levanov.ver_2.utils.validator.Validator;

public class ControllerApp {
    private ConsoleApp console;
    private Logger logger;
    private Validator validator;

    // Создаем console, logger и validator
    public void Start() {
        console = new ConsoleApp();
        logger = new Logger();
        validator = new Validator();
        console.Start(this);
    }

    // обработка поступившего запроса
    public void processRequest(Action action, String[] parameters) {
        Request request;
        Result result = new Result();
        switch (action) {
            case Action.START, Action.EXIT -> {
                result.action = action;
                logger.logEvent(result);
            }
            case Action.ENCRYPT, Action.DECRYPT, Action.BRUTE_FORCE -> {
                ValidationResult validationResult = validator.validateParameters(action, parameters);
                if (validationResult.status == Status.FAIL) {
                    console.showResult(validationResult);
                    logger.logEvent(validationResult);
                } else {
                    if (action == Action.BRUTE_FORCE) {
                        request = new Request(action, parameters[0], parameters[1]);
                    } else {
                        request = new Request(action, parameters[0], parameters[1], validationResult.key);
                    }
                    result = executeRequest(request);
                    console.showResult(result);
                    logger.logEvent(result);
                }
            }
        }
    }

    //отправляем запрос на исполнение
    private Result executeRequest(Request request) {
        Result result = new Result();
        switch (request.action) {
            case ENCRYPT -> {
                Encrypt.execute(request.inPath, request.outPath, request.key);
                result.action = Action.ENCRYPT;
                result.message = "File " + request.inPath + " has been successfully encrypted to file " + request.outPath + " with key=" + request.key;
            }
            case DECRYPT -> {
                Decrypt.execute(request.inPath, request.outPath, request.key);
                result.action = Action.DECRYPT;
                result.message = "File " + request.inPath + " has been successfully decrypted to file " + request.outPath + " with key=" + request.key;
            }
            case BRUTE_FORCE -> {
                BruteForce.execute(request.inPath, request.outPath);
                result.action = Action.BRUTE_FORCE;
                result.message = "File " + request.inPath + " has been successfully decrypted fy brute force method to file " + request.outPath;
            }
        }
        result.status = Status.SUCCESS;
        return result;
    }
}
