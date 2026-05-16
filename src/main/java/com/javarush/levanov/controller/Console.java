package com.javarush.levanov.controller;

import com.javarush.levanov.constant.Constants;
import com.javarush.levanov.result.Result;
import com.javarush.levanov.result.Status;

import java.util.Scanner;

public class Console {
    public Console(Controller controller) {
        Scanner scanner = new Scanner(System.in);
        ValidationRequest validationRequest = new ValidationRequest();
        this.start(controller, scanner, validationRequest);
    }

    private void start(Controller controller, Scanner scanner, ValidationRequest validationRequest) {
        controller.processRequest(validationRequest, this);
        String[] parameters = new String[Constants.MAX_QUESTION_NUMBERS - 1];
        int mode;
        while (true) {
            System.out.println(Constants.INITIAL_MESSAGE);
            try {
                mode = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                validationRequest.action = Action.EXIT;
                controller.processRequest(validationRequest, this);
                break;
            }
            if (mode < 1 || mode > 4) {
                validationRequest.action = Action.EXIT;
                controller.processRequest(validationRequest, this);
                break;
            } else {
                for (int i = 0; i < Constants.QUESTIONS[mode - 1].length; i++) {
                    System.out.println(Constants.QUESTIONS[mode - 1][i]);
                    parameters[i] = scanner.nextLine();
                }
                switch (mode) {
                    case 1 -> {
                        validationRequest.action = Action.ENCRYPT;
                        validationRequest.inStringPath = parameters[0];
                        validationRequest.outStringPath = parameters[1];
                        validationRequest.stringKey = parameters[2];
                    }
                    case 2 -> {
                        validationRequest.action = Action.DECRYPT;
                        validationRequest.inStringPath = parameters[0];
                        validationRequest.outStringPath = parameters[1];
                        validationRequest.stringKey = parameters[2];
                    }
                    case 3 -> {
                        validationRequest.action = Action.BRUTE_FORCE;
                        validationRequest.inStringPath = parameters[0];
                        validationRequest.outStringPath = parameters[1];
                    }
                    case 4 -> {
                        validationRequest.action = Action.ANALYZE;
                        validationRequest.inStringPath = parameters[0];
                        validationRequest.outStringPath = parameters[1];
                        validationRequest.dictionaryStringPath = parameters[2];
                        validationRequest.stringPrecision = parameters[3];
                    }
                }
            }
            controller.processRequest(validationRequest, this);
        }
    }

    // Выводим результат
    public void showResult(Result result) {
        if (result.message != null) {
            if (result.status == Status.SUCCESS) {
                System.out.printf(Constants.SUCCESS_RESULT, result.action, result.status, result.message);
            } else {
                System.out.printf(Constants.FAILED_RESULT, result.action, result.status, result.message);
            }
        }
    }
}