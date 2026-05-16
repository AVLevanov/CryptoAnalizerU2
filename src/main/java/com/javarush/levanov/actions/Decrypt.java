package com.javarush.levanov.actions;

import com.javarush.levanov.controller.ExecuteRequest;

public class Decrypt extends AbstractAction {
    public Decrypt(ExecuteRequest executeRequest) {
        this.executeRequest = executeRequest;
        execute(executeRequest);
    }

    private void execute(ExecuteRequest executeRequest) {
        codeWithKey(executeRequest.inPath, executeRequest.outPath, -executeRequest.key);
    }

}
