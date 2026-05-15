package com.javarush.levanov.ver2.controller;

public class Request {
    public Action action;
    public String inPath;
    public String outPath;
    public int key;

    // конструктор для encrypt и decrypt
    public Request(Action action, String inPath, String outPath, int key) {
        this.action = action;
        this.inPath = inPath;
        this.outPath = outPath;
        this.key = key;
    }

    // конструктор для bruteForce
    public Request(Action action,  String inPath, String outPath) {
        this.action = action;
        this.inPath = inPath;
        this.outPath = outPath;
    }


}
