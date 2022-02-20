package com.monobank.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> inputReader(String fileName) {
        String str;
        List<String> listOfStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                str = reader.readLine();
                listOfStrings.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfStrings;
    }

    public static List<String[]> stringParser(List<String> list) {
        String delimiter = "\\|";
        List<String[]> listOfParsedStrings = new ArrayList<>();
        for (String el : list) {
            String[] array;
            array = el.split(delimiter);
            listOfParsedStrings.add(array);
        }
        return listOfParsedStrings;
    }

    public static void outputWriter() {

    }
}
