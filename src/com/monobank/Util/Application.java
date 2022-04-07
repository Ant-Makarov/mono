package Util;

import java.util.List;

import Services.NotificationService;
import Util.CommandHandler;
import Util.FileHandler;

public class Application {

    public static void main(String[] args) {
        List<String> listOfStrings = FileHandler.inputReader();
        List<String[]> listOfParsedStrings = FileHandler.stringParser(listOfStrings);
        Thread thread = new Thread(new NotificationSendingProcessing(CommandHandler.getService()));
        thread.start();
        CommandHandler.commandExecutor(listOfParsedStrings);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
