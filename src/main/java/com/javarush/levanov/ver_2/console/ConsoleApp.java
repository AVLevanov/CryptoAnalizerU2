package com.javarush.levanov.ver_2.console;

import com.javarush.levanov.ver_2.controller.ControllerApp;
import com.javarush.levanov.ver_2.controller.result.Result;

import java.util.Scanner;

public class ConsoleApp {

    //Запускаем консоль и получаем данные от пользователя
    public void Start(ControllerApp controller) {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = new String[3];
        int mode;
        while (true) {
            System.out.println(Messages.INITIAL_MESSAGE);
            try {
                mode = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                break;
            }
            if (mode != 1 && mode != 2 && mode != 3) {
                break;
            } else {
                for (int i = 0; i < Messages.QUESTIONS[mode - 1].length; i++) {
                    System.out.println(Messages.QUESTIONS[mode - 1][i]);
                    parameters[i] = scanner.nextLine();
                }
                controller.processRequest(mode, parameters);
            }
        }
    }

    public void showResult(Result result) {
        System.out.printf(Messages.SHOW_RESULT, result.status, result.message);
    }
}
