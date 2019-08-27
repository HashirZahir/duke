import java.util.Arrays;

public class CommandParser {
    public enum commandType {
        LIST,
        QUIT,
        MARKDONE,
        TODO,
        DEADLINE,
        EVENT,
        IGNORE,
        DEFAULT
    }

    private static final String triggerListText = "list", triggerDoneText = "done",
                                triggerQuitText = "bye", triggerTodoText = "todo",
                                triggerDeadlineText = "deadline", triggerEventText = "event";
    public static final String splitDeadlineText = "/by ", splitEventText = "/at ";

    private String inputStr, splitText;
    private String[] argStrArr;
    private commandType command;

    public CommandParser() {
        this.command = commandType.DEFAULT;
        this.splitText = " ";
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;

        try {
            process();
        }
        catch (DukeException e) {
            this.command = commandType.IGNORE;
            System.out.println(e);
        }
    }

    private void process() throws DukeException{
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
            this.splitText = splitDeadlineText;
        }
        else if (command.equals(triggerEventText)) {
            this.command = commandType.EVENT;
            this.splitText = splitEventText;
        }
        else {
            throw new DukeException(DukeException.dukeExceptionType.UNKNOWN);
        }

        setArgs(restOfStr);
    }

    public commandType getCommandType() {
       return this.command;
    }

    public String getCommandText() {
        switch(this.command) {
            case LIST:
                return "list";
            case QUIT:
                return "bye";
            case MARKDONE:
                return "done";
            case TODO:
                return "todo";
            case DEADLINE:
                return "deadline";
            case EVENT:
                return "event";
            default:
                return "unknown";
        }
    }

    private void setArgs(String argStr) throws DukeException {

        switch(this.command) {
            case DEADLINE:
                if (argStr.isBlank()) {
                    throw new DukeException(DukeException.dukeExceptionType.DEADLINE_EMPTY, this.getCommandText());
                }
            case EVENT:
                if (argStr.isBlank()) {
                    throw new DukeException(DukeException.dukeExceptionType.EVENT_EMPTY, this.getCommandText());
                }
                this.argStrArr = argStr.split(this.splitText);
                String deadlineText = this.splitText.split("/")[1];
                this.argStrArr[1] = deadlineText + this.argStrArr[1];
                break;
            case TODO:
                if (argStr.isBlank()) {
                    throw new DukeException(DukeException.dukeExceptionType.TODO_EMPTY, this.getCommandText());
                }
            default:
                this.argStrArr = new String[]{argStr};
                break;
        }
    }

    public String[] getArgs() {
        return this.argStrArr;
    }


}
