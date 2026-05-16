package com.javarush.levanov.controller;

public class ValidationRequest extends Request {
    public String inStringPath;
    public String outStringPath;
    public String dictionaryStringPath;
    public String stringKey;
    public String stringPrecision;
//    public int validatedKey;
//    public double validatedPrecision;
//    public Path encryptedPath;
//    public Path decyptedPath;
//    public Path dictionaryPath;

    public ValidationRequest() {
        this.action = Action.START;
        this.inStringPath =  "";
        this.outStringPath = "";
        this.dictionaryStringPath = "";
        this.stringKey = "";
        this.stringPrecision = "";
//        this.validatedKey = 0;
//        this.validatedPrecision = 0;
    }
}
