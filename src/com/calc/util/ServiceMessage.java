package com.calc.util;

public enum ServiceMessage {

    HELLO_MESSAGE("Hi! I'm SuperCalc! What do you want to calculate?"),
    INSTRUCTION_MESSAGE("Bla bla bla"),
    WRONG_INPUT("Wrong input! Please try again."),
    WRONG_NUMBER("Wrong number! Please try again.");

    private String message;

    ServiceMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
