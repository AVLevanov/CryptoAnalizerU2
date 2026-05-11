package com.javarush.levanov.ver_2.console;

import com.javarush.levanov.ver_2.controller.Action;
import com.javarush.levanov.ver_2.controller.ControllerApp;
import com.javarush.levanov.ver_2.controller.result.Result;

import java.util.Scanner;

public class ConsoleApp {

    //Запускаем консоль и получаем данные от пользователя
    public void Start(ControllerApp controller) {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = new String[3];
        int mode;
        Action action = Action.EXIT;
        controller.processRequest(Action.START, parameters);
        while (true) {
            System.out.println(Messages.INITIAL_MESSAGE);
            try {
                mode = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                controller.processRequest(Action.EXIT, parameters);
                break;
            }
            if (mode != 1 && mode != 2 && mode != 3) {
                controller.processRequest(Action.EXIT, parameters);
                break;
            } else {
                for (int i = 0; i < Messages.QUESTIONS[mode - 1].length; i++) {
                    System.out.println(Messages.QUESTIONS[mode - 1][i]);
                    parameters[i] = scanner.nextLine();
                }
                switch (mode) {
                    case 1 -> action = Action.ENCRYPT;
                    case 2 -> action = Action.DECRYPT;
                    case 3 -> action = Action.BRUTE_FORCE;
                }
                controller.processRequest(action, parameters);
            }
        }
    }

    public void showResult(Result result) {
        if (result.message != null) {
            System.out.printf(Messages.SHOW_RESULT, result.action, result.status, result.message);
        }
    }
}
