import java.util.Arrays;

/**
 * Class to parse commands typed by the user and return relevant parts of input String.
 */
public class CommandParser {
    public enum commandType {
        LIST,
        DELETE,
        QUIT,
        MARKDONE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        IGNORE,
        DEFAULT
    }

    private static final String triggerListText = "list", triggerDeleteText = "delete",triggerDoneText = "done",
                                triggerQuitText = "bye", triggerTodoText = "todo",
                                triggerDeadlineText = "deadline", triggerEventText = "event",
                                triggerFindText = "find";

    public static final String splitDeadlineText = "/by ", splitEventText = "/at ";

    private String inputStr, splitText;
    private String[] argStrArr;
    private commandType command;

    /**
     * Initialize CommandParser class with default command type and empty string.
     */
    public CommandParser() {
        this.command = commandType.DEFAULT;
        this.splitText = " ";
    }

    /**
     * Set the input string to be parsed by the parser.
     * @param inputStr input string to be parsed.
     */
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

    /**
     * Splits the input string to find the command type.
     * @throws DukeException Unknown input command string entered will throw exception.
     */
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
        else if (command.equals(triggerDeleteText)) {
            this.command = commandType.DELETE;
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
        else if (command.equals(triggerFindText)) {
            this.command = commandType.FIND;
        }
        else {
            throw new DukeException(DukeException.dukeExceptionType.UNKNOWN);
        }

        setArgs(restOfStr);
    }

    /**
     * Get the command type of the input string after processing.
     * @return enum command type returned.
     */
    public commandType getCommandType() {
       return this.command;
    }

    /**
     * Get the string output of the command type after processing.
     * @return command type string.
     */
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
            case FIND:
                return "find";
            default:
                return "unknown";
        }
    }

    /**
     * Parse any additional arguments after the command string.
     * @param argStr additional string arguments to be parsed.
     * @throws DukeException empty or wrong arguments will return exception .
     */
    private void setArgs(String argStr) throws DukeException {

        if (this.command == commandType.DEADLINE || this.command == commandType.EVENT) {
            String[] splitStr = argStr.split(this.splitText);
            if (argStr.isBlank() || splitStr[0].isBlank()) {
                if (this.command == commandType.DEADLINE) {
                    throw new DukeException(DukeException.dukeExceptionType.DEADLINE_DESC_EMPTY, this.getCommandText());
                }
                else if (this.command == commandType.EVENT) {
                    throw new DukeException(DukeException.dukeExceptionType.EVENT_DESC_EMPTY, this.getCommandText());
                }
            }
            else if (splitStr.length <= 1) {
                if (this.command == commandType.DEADLINE) {
                    throw new DukeException(DukeException.dukeExceptionType.DEADLINE_DATE_EMPTY, this.getCommandText());
                }
                else if (this.command == commandType.EVENT) {
                    throw new DukeException(DukeException.dukeExceptionType.EVENT_DATE_EMPTY, this.getCommandText());
                }
            }
            else {
                this.argStrArr = argStr.split(this.splitText);
            }

        }
        else if (this.command == commandType.TODO) {
            if (argStr.isBlank()) {
                throw new DukeException(DukeException.dukeExceptionType.TODO_DESC_EMPTY, this.getCommandText());
            }
            else {
                this.argStrArr = new String[]{argStr};
            }
        }
        else if (this.command == commandType.MARKDONE) {
            if(argStr.matches("^[0-9]+$")) {
                this.argStrArr = new String[]{argStr};
            }
            else {
                System.out.println("Improper 'Done' command given. Please follow: done [integer].\n example: done 2");
            }
        }
        else {
            this.argStrArr = new String[]{argStr};
        }


    }

    /**
     * Return all the parsed arguments.
     * @return array of string arguments returned.
     */
    public String[] getArgs() {
        return this.argStrArr;
    }


}
