package com.calc.util;

import com.calc.exception.WrongInputException;
import com.calc.exception.WrongNumberException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

    public static boolean isNumberBetweenInterval(int a, int b){
        return a > 0 && a < 11 && b > 0 && b < 11;
    }

    public static Map<String, String[]> parseMessage(Message message) throws WrongInputException, WrongNumberException {

        Map<String, String[]> numbers;
        if(isInputValid(message) && Pattern.compile("[\\D\\W]+").matcher(message.getMessage()).find()){
            numbers = parseNumbers(message);

        }else throw new WrongInputException();

        return numbers;
    }


    public static Map<String, String[]> parseNumbers(Message message) throws WrongInputException, WrongNumberException {
        Map<String, String[]> input = new HashMap<>();


        if (isOnlyArabicNumbers(message.getMessage())) {
            checkLastCharacter(message.getMessage());
            String s = message.getMessage().replaceAll(" ", "");

            String[] sign = s.split("[^\\D\\W\\s]+");
            String[] numbers = s.split("[\\D\\W\\s]+");

            if(numbers.length != 2) throw new WrongInputException();
            if (isNumberBetweenInterval(Integer.parseInt(numbers[0]),
                    Integer.parseInt(numbers[1]))) input.put(sign[1], numbers);
            else throw new WrongNumberException();

        } else if (isOnlyRomeNumbers(message.getMessage())) {
            checkLastCharacter(message.getMessage());
            String s = message.getMessage().replaceAll(" ", "");
            String[] numbers = s.split("[^IVX]+");
            String[] sign = s.split("[IVX]+");

            if(numbers.length != 2) throw new WrongInputException();
            if(isNumberBetweenInterval(romanToArabic(numbers[0]),
                    romanToArabic(numbers[1]))) input.put(sign[1],
                    new String[]{Integer.toString(romanToArabic(numbers[0])), Integer.toString(romanToArabic(numbers[1]))});
            else throw new WrongNumberException();

        } else throw new WrongInputException();


        return input;
    }

    public static int romanToArabic(String input) throws WrongInputException {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomeNumber> romanNumerals = RomeNumber.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomeNumber symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new WrongInputException();
        }

        return result;
    }

    public static String arabicToRoman(int number) throws WrongNumberException {
        if ((number <= 0) || (number > 4000)) {
            throw new WrongNumberException();
        }

        List<RomeNumber> romanNumerals = RomeNumber.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomeNumber currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static boolean isInputValid(Message message){
        Pattern p = Pattern.compile("[^IVX0-9\\+\\-\\*\\/\\s]+");
        Matcher m = p.matcher(message.getMessage());

        return !m.find();
    }

    public static boolean isOnlyArabicNumbers(String message){
        return !Pattern.compile("[^0-9\\+\\-\\*\\/\\s]+").matcher(message).find();
    }

    public static boolean isOnlyRomeNumbers(String message){
        return !Pattern.compile("[^IXV\\+\\-\\*\\/\\s]+").matcher(message).find();
    }

    public static void checkLastCharacter(String message) throws WrongInputException {
        char lastChar = message.toCharArray()[message.length() - 1];

        if(isOnlyArabicNumbers(message)) {
            if (!Character.isDigit(lastChar)) throw new WrongInputException();
        }else if(isOnlyRomeNumbers(message)){
            if(Pattern.compile("[^IVX]$").matcher(message).find()) throw new WrongInputException();
        }
    }
}
