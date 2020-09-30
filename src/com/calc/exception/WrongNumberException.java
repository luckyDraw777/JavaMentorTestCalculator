package com.calc.exception;

import com.calc.util.Message;
import com.calc.util.ServiceMessage;

public class WrongNumberException extends Exception{

    public Message getExceptionMessage(){
        return new Message(ServiceMessage.WRONG_NUMBER.getMessage());
    }
}
