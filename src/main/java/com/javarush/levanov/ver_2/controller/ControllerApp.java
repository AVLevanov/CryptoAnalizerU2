package com.javarush.levanov.ver_2.controller;

import com.javarush.levanov.ver_2.actions.BruteForce;
import com.javarush.levanov.ver_2.actions.Decrypt;
import com.javarush.levanov.ver_2.actions.Encrypt;
import com.javarush.levanov.ver_2.console.ConsoleApp;
import com.javarush.levanov.ver_2.controller.result.*;
import com.javarush.levanov.ver_2.utils.Logger;
import com.javarush.levanov.ver_2.controller.result.ValidationResult;
import com.javarush.levanov.ver_2.utils.validator.Validator;

public class ControllerApp {
    private ConsoleApp console;
    private Logger logger;

    // Создаем console и logger
    public void Start() {
        console = new ConsoleApp();
        logger = new Logger();
        console.Start(this);
    }

    // обработка поступившего запроса
    public void processRequest(Action action, String[] parameters) {
        Validator validator = new Validator();
        ValidationResult validationResult = validator.validateParameters(action, parameters);
        if (validationResult.status == Status.SUCCESS) {
            ActionResult actionResult = executeRequest(validationResult.request);
            console.showResult(actionResult);
            logger.logEvent(actionResult);
        } else {
            console.showResult(validationResult);
            logger.logEvent(validationResult);
        }
    }

    //отправляем запрос на исполнение
    private ActionResult executeRequest(Request request) {
        ActionResult actionResult = new ActionResult();
        switch (request.action) {
            case START -> actionResult.action = Action.START;
            case EXIT -> actionResult.action = Action.EXIT;
            case ENCRYPT -> {
                actionResult.action = Action.ENCRYPT;
                Encrypt.execute(request.inPath, request.outPath, request.key);
                actionResult.message = "\\u001B[31m" + "File " + request.inPath + " has been successfully encrypted to file " + request.outPath + " with key=" + request.key;
            }
            case DECRYPT -> {
                actionResult.action = Action.DECRYPT;
                Decrypt.execute(request.inPath, request.outPath, request.key);
                actionResult.message = "File " + request.inPath + " has been successfully decrypted to file " + request.outPath + " with key=" + request.key;
            }
            case BRUTE_FORCE -> {
                actionResult.action = Action.BRUTE_FORCE;
                BruteForce.execute(request.inPath, request.outPath);
                actionResult.message = "File " + request.inPath + " has been successfully decrypted fy brute force method to file " + request.outPath;
            }
        }
        actionResult.status = Status.SUCCESS;
        return actionResult;
    }
}
