package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String INPUT = "C:\\Users\\Антон\\monoPostService\\src\\com\\monobank\\input.txt";
    private final static String OUTPUT = "C:\\Users\\Антон\\monoPostService\\src\\com\\monobank\\output.txt";

    public static List<String> inputReader() {
        String str;
        List<String> listOfStrings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT))) {
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

    public static void outputWriter(String str) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT))) {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

