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
                                                                                    "todo\ndeadline\nevent\nbye",
                                emptyErrText1 = "☹ OOPS!!! The description of a ",
                                emptyDateErrText1 = "☹ OOPS!!! The date of a ",
                                emptyErrText2 = " cannot be empty.";

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

    private String getEmptyDescText(String command) {
        return emptyErrText1 + command + emptyErrText2;
    }

    private String getEmptyDateText(String command) {
        return emptyDateErrText1 + command + emptyErrText2;
    }

    public String toString() {
        return this.errText;
    }
}
