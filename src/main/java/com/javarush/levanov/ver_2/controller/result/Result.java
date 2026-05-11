package com.javarush.levanov.ver_2.controller.result;

import com.javarush.levanov.ver_2.controller.Action;

public abstract class Result {
    public Action action;
    public Status status;
    public String message;
}
