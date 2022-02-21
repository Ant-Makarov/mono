
import java.util.List;

import Util.CommandHandler;
import Util.FileHandler;

public class Application {

    public static void main(String[] args) {
        List<String> listOfStrings = FileHandler.inputReader();
        List<String[]> listOfParsedStrings = FileHandler.stringParser(listOfStrings);
        CommandHandler.commandExecutor(listOfParsedStrings);

    }

}
