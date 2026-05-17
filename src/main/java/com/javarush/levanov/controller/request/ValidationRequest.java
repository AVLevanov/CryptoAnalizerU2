package com.javarush.levanov.controller.request;

import com.javarush.levanov.controller.Action;

public class ValidationRequest extends Request {
    public String inStringPath;
    public String outStringPath;
    public String dictionaryStringPath;
    public String stringKey;
    public String stringPrecision;

    public ValidationRequest() {
        this.action = Action.START;
        this.inStringPath =  "";
        this.outStringPath = "";
        this.dictionaryStringPath = "";
        this.stringKey = "";
        this.stringPrecision = "";
    }
}
