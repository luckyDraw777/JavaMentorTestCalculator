package com.calc.exception;

import com.calc.util.Message;
import com.calc.util.ServiceMessage;

public class WrongInputException extends Exception{

    public Message getExceptionMessage(){
        return new Message(ServiceMessage.WRONG_INPUT.getMessage());
    }
}
