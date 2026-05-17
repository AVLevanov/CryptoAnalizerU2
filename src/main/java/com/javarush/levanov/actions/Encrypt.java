package com.javarush.levanov.actions;

import com.javarush.levanov.controller.request.ExecuteRequest;

public class Encrypt extends AbstractAction {
    public Encrypt(ExecuteRequest executeRequest) {
        execute(executeRequest);
    }

    private void execute(ExecuteRequest executeRequest) {
        executeRequest.coder.setCeaserCipher(executeRequest.coder, executeRequest.key);
        code(executeRequest.coder, executeRequest.inPath, executeRequest.outPath);
    }
}
