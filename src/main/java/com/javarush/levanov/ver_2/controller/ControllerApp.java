package com.javarush.levanov.ver_2.controller;

import com.javarush.levanov.ver_2.actions.BruteForce;
import com.javarush.levanov.ver_2.actions.Decrypt;
import com.javarush.levanov.ver_2.actions.Encrypt;

public class ControllerApp {
    public void executeRequest(Request request) {
        switch (request.action) {
            case ENCRYPT -> {
                Encrypt.execute(request.inPath, request.outPath, request.key);
                System.out.println("execute request " + request.action + ":   inPath=" + request.inPath + ",   outPath=" + request.outPath + ",   key=" + request.key);
            }
            case DECRYPT -> {
                Decrypt.execute(request.inPath, request.outPath, request.key);
                System.out.println("execute request " + request.action + ":   inPath=" + request.inPath + ",   outPath=" + request.outPath + ",   key=" + request.key);
            }
            case BRUTE_FORCE -> {
                BruteForce.execute(request.inPath, request.outPath);
                System.out.println("execute request " + request.action + ":   inPath=" + request.inPath + ",   outPath=" + request.outPath);
            }
        }
    }
}
