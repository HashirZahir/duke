import java.util.Arrays;
import java.util.ArrayList;

public class CommandParser {
    public enum commandType {
        LIST,
        QUIT,
        MARKDONE,
        TODO,
        DEADLINE,
        DEFAULT
    }

    private static final String triggerListText = "list", triggerDoneText = "done",
                                triggerQuitText = "bye", triggerTodoText = "todo",
                                triggerDeadlineText = "deadline";
    public static final String splitDeadlineText = "/by ";

    private String inputStr;
    private String[] argStrArr;
    private commandType command;

    public CommandParser() {
        this.command = commandType.DEFAULT;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
        process();
    }

    private void process() {
        String[] splitStr = this.inputStr.split(" ");
        String command = splitStr[0];
        String restOfStr = "";
        if (splitStr.length >= 2) {
            String[] restOfStrArr = Arrays.copyOfRange(splitStr, 1, splitStr.length);
            restOfStr = String.join(" ", restOfStrArr);
        }

        if (command.equals(triggerQuitText)) {
            this.command = commandType.QUIT;
        }
        else if (command.equals(triggerListText)) {
            this.command = commandType.LIST;
        }
        else if (command.equals(triggerDoneText)) {
            this.command = commandType.MARKDONE;
        }
        else if (command.equals(triggerTodoText)) {
            this.command = commandType.TODO;
        }
        else if (command.equals(triggerDeadlineText)) {
            this.command = commandType.DEADLINE;
        }
        else {
            this.command = commandType.DEFAULT;
        }

        setArgs(restOfStr);
    }

    public commandType getCommandType() {
       return this.command;
    }

    private void setArgs(String argStr) {
        switch(this.command) {
            case DEADLINE:
                this.argStrArr = argStr.split(splitDeadlineText);
                String deadlineText = splitDeadlineText.split("/")[1];
                this.argStrArr[1] = deadlineText + this.argStrArr[1];
                break;
            default:
                this.argStrArr = new String[]{argStr};
                break;
        }
    }

    public String[] getArgs() {
        return this.argStrArr;
    }


}
