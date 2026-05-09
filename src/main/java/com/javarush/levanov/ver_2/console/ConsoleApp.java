package com.javarush.levanov.ver_2.console;

import com.javarush.levanov.ver_2.controller.ControllerApp;
import com.javarush.levanov.ver_2.controller.result.Result;
import com.javarush.levanov.ver_2.controller.result.ValidationErrorMessages;

import java.util.Scanner;

public class ConsoleApp {

    //Запускаем консоль и получаем данные от пользователя
    public void Start(ControllerApp controller) {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = new String[3];
        System.out.println(Messages.INITIAL_MESSAGE);
        String firstLine = scanner.nextLine();
        if (firstLine != null) {
            int mode = Integer.parseInt(firstLine);
            for (int i = 0; i < Messages.QUESTIONS[mode - 1].length; i++) {
                System.out.println(Messages.QUESTIONS[mode - 1][i]);
                parameters[i] = scanner.nextLine();
            }
            controller.processRequest(mode, parameters, this);
        } else {
            System.out.println(ValidationErrorMessages.INCORRECT_MODE);
        }
    }

    public void showResult(Result result) {
        System.out.println("Status: " + result.status + "\nMessage: " + result.message);
    }
}
