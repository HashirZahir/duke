import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String welcomeMsg = "Hello from " + logo + "What can I do for you today?";
    private static final String quitMsg = "Bye. Hope to see you again!\n" + logo;
    private static final String triggerQuitText = "bye";

    public static void main(String[] args) {
        Duke duke = new Duke(replyModeType.ECHO);
        Scanner scanner = new Scanner(System.in);
        String inputStr = "";

        duke.greeting();

        while (true) {
            if (scanner.hasNextLine()) {
                inputStr = scanner.nextLine();
                if (inputStr.equals(triggerQuitText)) break;
                duke.reply(inputStr);
            }
        }

        duke.quit();

    }


    enum replyModeType{
        ECHO,
        ADD,
        MARK
    }

    replyModeType replyMode;
    String inputStr;

    public Duke(replyModeType replyMode) {
        this.replyMode = replyMode;
        this.inputStr="";
    }

    public replyModeType getReplyMode() {
        return this.replyMode;
    }

    public void setReplyMode(replyModeType replyMode) {
        this.replyMode = replyMode;
    }

    public void greeting() {
        System.out.println(welcomeMsg);
    }

    public void quit() {
        setReplyMode(replyModeType.ECHO);
        reply(quitMsg);
    }

    public void reply(String input) {
        // TODO: check if whitespace is considered as input.
        if (input.isEmpty()) {
            return;
        }
        else {
            this.inputStr = input;
        }

        final String horizontalSeparator = "______________________________________";

        System.out.println(horizontalSeparator);

        switch(replyMode) {
            default:
            case ECHO:
                echoReply();
                break;
            case ADD:
                addReply();
                break;
            case MARK:
                markReply();
                break;
        }

        System.out.println(horizontalSeparator);
    }

    public void echoReply() {
        System.out.println(this.inputStr);
    }

    public void addReply() {

    }

    public void markReply() {

    }
}

/**public class Task{

}**/
