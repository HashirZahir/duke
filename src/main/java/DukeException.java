public class DukeException extends Exception{
    enum dukeExceptionType {
        TODO_EMPTY,
        UNKNOWN
    }

    private static final String unknownErrText = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                                knownCommands = "Please type one of these recognized commands:\nlist\ntodo\ndeadline\nevent\nbye",
                                emptyErrText_1 = "☹ OOPS!!! The description of a ",
                                emptyErrText_2 = " cannot be empty.";

    String errText;

    public DukeException(dukeExceptionType det) {
        this.errText = "";
        setExceptionText(det);
    }

    private void setExceptionText(dukeExceptionType det) {
        switch (det) {
            case TODO_EMPTY:
                // TODO
                break;
            case UNKNOWN:
                this.errText = unknownErrText + knownCommands;
        }
    }

    public String toString() {
        return this.errText;
    }
}
