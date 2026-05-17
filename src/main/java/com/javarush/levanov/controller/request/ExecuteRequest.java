package com.javarush.levanov.controller.request;

import com.javarush.levanov.controller.Action;
import com.javarush.levanov.utilApps.Coder;

import java.nio.file.Path;

public class ExecuteRequest extends Request{
    public int key;
    public double precision;
    public Coder coder;
    public Path inPath;
    public Path outPath;
    public Path dictionaryPath;

    public ExecuteRequest(Coder coder, Action action, int key, double precision, Path inPath, Path outPath, Path dictionaryPath) {
        this.coder = coder;
        this.action = action;
        this.key = key;
        this.precision = precision;
        this.inPath = inPath;
        this.outPath = outPath;
        this.dictionaryPath = dictionaryPath;
    }
}
