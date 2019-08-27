public class DukeException extends Exception{
    enum dukeExceptionType {
        EVENT_EMPTY,
        DEADLINE_EMPTY,
        TODO_EMPTY,
        UNKNOWN
    }

    private static final String unknownErrText = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                                knownCommands = "Please type one of these recognized commands:\nlist\ntodo\ndeadline\nevent\nbye",
                                emptyErrText_1 = "☹ OOPS!!! The description of a ",
                                emptyErrText_2 = " cannot be empty.";

    String errText, commandStr;

    public DukeException(dukeExceptionType det, String commandStr) {
        this.errText = "";
        setExceptionText(det, commandStr);
    }

    public DukeException(dukeExceptionType det) {
        this(det, "unknown");
    }


    private void setExceptionText(dukeExceptionType det, String commandStr) {
        switch (det) {
            case TODO_EMPTY:
            case EVENT_EMPTY:
            case DEADLINE_EMPTY:
                this.errText = getEmptyText(commandStr);
                break;
            case UNKNOWN:
                this.errText = unknownErrText + knownCommands;
                break;
        }
    }

    private String getEmptyText(String command) {
        return emptyErrText_1 + command + emptyErrText_2;
    }

    public String toString() {
        return this.errText;
    }
}
