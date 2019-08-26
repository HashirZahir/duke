import java.util.Arrays;
import java.util.ArrayList;

public class CommandParser {
    public enum commandType {
        LIST,
        QUIT,
        MARKDONE,
        DEFAULT
    }

    private static final String triggerListText = "list";
    private static final String triggerDoneText = "done";
    private static final String triggerQuitText = "bye";

    private String inputStr, argStr;

    public CommandParser() {}

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public commandType getCommandType() {
        String[] splitStr = inputStr.split(" ");
        String command = splitStr[0];
        String restOfStr = "";
        if (splitStr.length >= 2) {
            String[] restOfStrArr = Arrays.copyOfRange(splitStr, 1, splitStr.length);
            restOfStr = String.join(" ", restOfStrArr);
        }
        setArgs(restOfStr);

        if (command.equals(triggerQuitText)) {
            return commandType.QUIT;
        }
        else if (command.equals(triggerListText)) {
            return commandType.LIST;
        }
        else if (command.equals(triggerDoneText)) {
            return commandType.MARKDONE;
        }

        return commandType.DEFAULT;
    }

    public void setArgs(String argsStr) {
        this.argStr = argsStr;
    }

    public ArrayList<String> getArgs() {
        ArrayList<String> argStrArr = new ArrayList<String>();
        argStrArr.add(this.argStr);
        return argStrArr;
    }


}
