package com.javarush.levanov.actions;

import com.javarush.levanov.controller.ExecuteRequest;

public class Decrypt extends AbstractAction {
    public static void execute(ExecuteRequest executeRequest) {
        codeWithKey(executeRequest.inPath, executeRequest.outPath, -executeRequest.key);
    }

//    public static void execute(Path inPath, Path outPath) {
//        code(inPath, outPath);
//    }
}
