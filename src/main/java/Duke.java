import java.util.ArrayList;
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
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);
        String inputStr = "";

        duke.greeting();

        while (true) {
            if (scanner.hasNextLine()) {
                inputStr = scanner.nextLine();
                if (!duke.reply(inputStr)) break;
            }
        }
    }

    public Duke() {
    }

    public void greeting() {
        System.out.println(welcomeMsg);
    }

    public boolean reply(String inputStr) {
        // TODO: check if whitespace is considered as input.
        if (inputStr.isEmpty()) {
            return true;
        }

        boolean isQuit = false;
        final String horizontalSeparator = "______________________________________";
        System.out.println(horizontalSeparator);

        if (inputStr.equals(triggerQuitText)) {
            isQuit = true;
            System.out.println(quitMsg);
        }
        else {
            echoReply(inputStr);
        }

        System.out.println(horizontalSeparator);
        return !isQuit;
    }

    public void echoReply(String inputStr) {
        System.out.println(inputStr);
    }

    public void addReply(String taskName) {
    }

    public void markReply() {

    }
}


