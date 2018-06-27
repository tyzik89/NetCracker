package ru.sukmanov.excepts;

public class NotConsistencyException extends Exception{

    public NotConsistencyException() {};

    public NotConsistencyException(String msg) {
        super(msg);
    }
}
