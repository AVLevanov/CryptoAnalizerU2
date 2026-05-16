package com.javarush.levanov.actions;

import com.javarush.levanov.controller.ExecuteRequest;

public class Encrypt extends AbstractAction {
    public static void execute(ExecuteRequest executeRequest) {
        codeWithKey(executeRequest.inPath, executeRequest.outPath, executeRequest.key);
    }
}
