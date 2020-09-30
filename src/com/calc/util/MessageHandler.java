package com.calc.util;


import com.calc.exception.WrongInputException;
import com.calc.exception.WrongNumberException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHandler {

    public static Message getMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return new Message(reader.readLine());
    }

    public static void printMessage(Message message){
        System.out.println(message.getMessage());
    }



}
