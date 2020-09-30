package com.calc.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageHandler {

    public static Message getMessage() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return new Message(reader.readLine());
    }

    public static void printMessage(Message message){
        System.out.println(message.getMessage());
    }



}
