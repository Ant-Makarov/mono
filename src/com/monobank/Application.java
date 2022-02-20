package com.monobank;

import java.util.List;

import com.monobank.Util.CommandHandler;
import com.monobank.Util.FileHandler;

public class Application {

    public static void main(String[] args) {
        String input ="";
        String output = "";
        try {
            input = args[0];
            output = args[1];
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        List<String> listOfStrings = FileHandler.inputReader(input);
        List<String[]> listOfParsedStrings = FileHandler.stringParser(listOfStrings);
        CommandHandler.commandExecutor(listOfParsedStrings);

    }

}
