import java.util.Arrays;
import java.util.ArrayList;

public class CommandParser {
    public enum commandType {
        LIST,
        QUIT,
        MARKDONE,
        TODO,
        DEFAULT
    }

    private static final String triggerListText = "list", triggerDoneText = "done",
                                triggerQuitText = "bye", triggerTodoText = "todo";

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
        else if (command.equals(triggerTodoText)) {
            return commandType.TODO;
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
