package com.javarush.levanov.ver_2.controller;

import com.javarush.levanov.ver_2.actions.BruteForce;
import com.javarush.levanov.ver_2.actions.Decrypt;
import com.javarush.levanov.ver_2.actions.Encrypt;
import com.javarush.levanov.ver_2.console.ConsoleApp;
import com.javarush.levanov.ver_2.controller.result.*;
import com.javarush.levanov.ver_2.utils.validator.ValidationResult;
import com.javarush.levanov.ver_2.utils.validator.Validator;

public class ControllerApp {

    // обработка поступившего запроса
    public void processRequest(int mode, String[] parameters, ConsoleApp console) {
        Validator validator = new Validator();
        ValidationResult validationResult = validator.validateParameters(mode, parameters);
        if (validationResult.status == Status.SUCCESS) {
            ActionResult actionResult = executeRequest(validationResult.request);
            console.showResult(actionResult);
        } else {
            console.showResult(validationResult);
        }
    }

    //отправляем запрос на исполние
    private ActionResult executeRequest(Request request) {
        ActionResult actionResult = new ActionResult();
        switch (request.mode) {
            case 1 -> {
                Encrypt.execute(request.inPath, request.outPath, request.key);
                actionResult.message = "File " + request.inPath + " has been successfully encrypted to file " + request.outPath + " with key=" + request.key;
            }
            case 2 -> {
                Decrypt.execute(request.inPath, request.outPath, request.key);
                actionResult.message = "File " + request.inPath + " has been successfully decrypted to file " + request.outPath + " with key=" + request.key;
            }
            case 3 -> {
                BruteForce.execute(request.inPath, request.outPath);
                actionResult.message = "File " + request.inPath + " has been successfully decrypted fy brute force method to file " + request.outPath;
            }
        }
        actionResult.status = Status.SUCCESS;
        return actionResult;
    }
}
