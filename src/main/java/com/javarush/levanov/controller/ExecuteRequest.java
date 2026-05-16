package com.javarush.levanov.controller;

import java.nio.file.Path;

public class ExecuteRequest extends Request{
    public int key;
    public double precision;
    public Path inPath;
    public Path outPath;
    public Path dictionaryPath;

    public ExecuteRequest(Action action, int key, double precision, Path inPath, Path outPath, Path dictionaryPath) {
        this.action = action;
        this.key = key;
        this.precision = precision;
        this.inPath = inPath;
        this.outPath = outPath;
        this.dictionaryPath = dictionaryPath;
    }
}
