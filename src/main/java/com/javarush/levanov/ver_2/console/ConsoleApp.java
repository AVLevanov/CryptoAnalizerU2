package com.javarush.levanov.ver_2.console;

import com.javarush.levanov.ver_2.controller.Action;
import com.javarush.levanov.ver_2.controller.ControllerApp;
import com.javarush.levanov.ver_2.controller.Request;

import java.util.Scanner;

public class ConsoleApp {

    //Запускаем консоль и получаем данные от пользователя
    public void Start(ControllerApp controller) {
        Scanner console = new Scanner(System.in);
        System.out.println(Messages.INITIAL_MESSAGE);
        String[] parameters = new String[3];
        int mode = Integer.parseInt(console.nextLine());// провалидировать ввод
        for (int i = 0; i < Messages.QUESTIONS[mode - 1].length; i++) {
            System.out.println(Messages.QUESTIONS[mode - 1][i]);
            parameters[i] = console.nextLine();
        }

        String inPath = parameters[0];
        String outPath = parameters[1];
        int key = 0;
        if (parameters[2] != null) {
            key = Integer.parseInt(parameters[2]);
        }
        controller.executeRequest(createRequest(mode, inPath, outPath, key));
    }

    // конструируем запросы
    public Request createRequest(int mode, String inPath, String outPath, int key) {
        switch (mode) {
            case 1 -> {
                return new Request(Action.ENCRYPT, inPath, outPath, key);
            }
            case 2 -> {
                return new Request(Action.DECRYPT, inPath, outPath, key);
            }
            case 3 -> {
                return new Request(Action.BRUTE_FORCE, inPath, outPath);
            }
            default -> {
                return null; // !обработать случай
            }
        }
    }
}
