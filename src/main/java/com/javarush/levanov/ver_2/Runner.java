package com.javarush.levanov.ver_2;

import com.javarush.levanov.ver_2.console.ConsoleApp;
import com.javarush.levanov.ver_2.controller.ControllerApp;

public class Runner {
    static void main() {
        ControllerApp controller = new ControllerApp();
        ConsoleApp console = new ConsoleApp();
        console.Start(controller);
    }
}
