package com.javarush.levanov.result;

import com.javarush.levanov.controller.Action;

import java.nio.file.Path;

public class ValidationResult extends Result {
    public int key;
    public double precision;
    public Path inPath;
    public Path outPath;
    public Path dictionaryPath;

    public ValidationResult(Action action) {
        this.action = action;
        this.status = Status.SUCCESS;
    }
}
