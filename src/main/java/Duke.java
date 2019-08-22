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
    private static final String markAsDoneMsg = "Nice! I've marked this task as done:\n";
    private static final String triggerListText = "list";
    private static final String triggerDoneText = "done";
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


    ArrayList<Task> taskArrayList;

    public Duke() {
        this.taskArrayList = new ArrayList<Task>();
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
        String[] splitStr = inputStr.split(" ");

        if (inputStr.equals(triggerQuitText)) {
            isQuit = true;
            System.out.println(quitMsg);
        }
        else if (inputStr.equals(triggerListText)) {
            listReply();
        }
        else if (splitStr.length >=2 && splitStr[0].equals(triggerDoneText)) {
            markReply(splitStr[1]);
        }
        else {
            addReply(inputStr);
        }

        System.out.println(horizontalSeparator);
        return !isQuit;
    }

    public void echoReply(String inputStr) {
        System.out.println(inputStr);
    }

    public void addReply(String taskName) {
        this.taskArrayList.add(new Task(taskName));
        System.out.println("added: " + taskName);
    }

    public void listReply() {
        int currIndex = 1;
        for (Task task : this.taskArrayList) {
            System.out.println(currIndex + ". " + task);
            currIndex += 1;
        }
    }
    public void markReply(String indexStr) {
        if(indexStr.matches("^[0-9]+$")) {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < this.taskArrayList.size()) {
                this.taskArrayList.get(index).markTaskDone();
                System.out.println(markAsDoneMsg + this.taskArrayList.get(index));
            }
            else {
                System.out.println("Index provided is out of range. Cannot mark as done");
            }
        }
        else {
            System.out.println("Improper 'Done' command given. Please follow: done [integer].\n example: done 2");
        }
    }
}


