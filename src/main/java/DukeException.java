/**
 * Custom exception class for Duke project.
 */
public class DukeException extends Exception{
    enum dukeExceptionType {
        EVENT_DESC_EMPTY,
        DEADLINE_DESC_EMPTY,
        TODO_DESC_EMPTY,
        EVENT_DATE_EMPTY,
        DEADLINE_DATE_EMPTY,
        UNKNOWN
    }

    private static final String unknownErrText = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n",
                                knownCommands = "Please type one of these recognized commands:\nlist\ndelete\n" +
                                                "todo\ndeadline\nevent\nfind\nbye",
                                emptyErrText1 = "☹ OOPS!!! The description of a ",
                                emptyDateErrText1 = "☹ OOPS!!! The date of a ",
                                emptyErrText2 = " cannot be empty.";

    String errText, commandStr;

    /**
     * Initialize duke exception with exception type and the command string that caused it.
     * @param det Type of duke exception.
     * @param commandStr Command string that caused the exception.
     */
    public DukeException(dukeExceptionType det, String commandStr) {
        this.errText = "";
        setExceptionText(det, commandStr);
    }

    /**
     * Initialize duke exception with only command type and unknown command string.
     * @param det Type of duke exception.
     */
    public DukeException(dukeExceptionType det) {
        this(det, "unknown");
    }

    /**
     * Set exception error message based on exception type and the command string from user.
     * @param det Type of Duke exception.
     * @param commandStr Command string input by user.
     */
    private void setExceptionText(dukeExceptionType det, String commandStr) {
        switch (det) {
            case TODO_DESC_EMPTY:
            case EVENT_DESC_EMPTY:
            case DEADLINE_DESC_EMPTY:
                this.errText = getEmptyDescText(commandStr);
                break;
            case EVENT_DATE_EMPTY:
            case DEADLINE_DATE_EMPTY:
                this.errText = getEmptyDateText(commandStr);
                break;
            case UNKNOWN:
                this.errText = unknownErrText + knownCommands;
                break;
        }
    }

    /**
     * Error message for empty Task description.
     * @param command Type of task with empty description.
     * @return Error message to be shown to user.
     */
    private String getEmptyDescText(String command) {
        return emptyErrText1 + command + emptyErrText2;
    }

    /**
     * Error message for empty date input for tasks.
     * @param command Type of task with missing date information.
     * @return Error message to be shown to user.
     */
    private String getEmptyDateText(String command) {
        return emptyDateErrText1 + command + emptyErrText2;
    }

    /**
     * String representation of custom exception object.
     * @return String representation of custom exception object.
     */
    public String toString() {
        return this.errText;
    }
}
