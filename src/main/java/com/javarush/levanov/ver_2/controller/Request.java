package com.javarush.levanov.ver_2.controller;

public class Request {
    public int mode;
    public String inPath;
    public String outPath;
    public int key;

    // конструктор для encrypt и decrypt
    public Request(int mode, String inPath, String outPath, int key) {
        this.mode = mode;
        this.inPath = inPath;
        this.outPath = outPath;
        this.key = key;
    }

    // конструктор для bruteForce
    public Request(int mode, String inPath, String outPath) {
        this.mode = mode;
        this.inPath = inPath;
        this.outPath = outPath;
    }
}
