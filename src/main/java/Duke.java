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
    private static final String taskAddedMsg = "Got it. I've added this task: \n";
    private static final String numberOfTasksMsg_1 = "Now you have ", getNumberOfTasksMsg_2 = " tasks in the list";

    private CommandParser commandParser;

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
        this.commandParser = new CommandParser();
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

        commandParser.setInputStr(inputStr);
        CommandParser.commandType command = commandParser.getCommandType();
        ArrayList<String> commandArgs = commandParser.getArgs();
        Task t;

        switch(command) {
            case QUIT:
                isQuit = true;
                System.out.println(quitMsg);
                break;
            case LIST:
                listReply();
                break;
            case MARKDONE:
                markReply(commandArgs.get(0));
                break;
            case TODO:
                String todoTaskName = commandArgs.get(0);
                t = new Todo(todoTaskName);
                addReply(t);
                break;
            case DEFAULT:
                t = new Task(inputStr);
                addReply(t);
                break;
        }

        System.out.println(horizontalSeparator);
        return !isQuit;
    }

    public void echoReply(String inputStr) {
        System.out.println(inputStr);
    }

    public void addReply(Task task) {
        this.taskArrayList.add(task);
        System.out.println(taskAddedMsg + task + numberOfTasksMsg_1 + this.taskArrayList.size() + getNumberOfTasksMsg_2);

    }

    public void listReply() {
        int currIndex = 1;
        for (Task task : this.taskArrayList) {
            System.out.print(currIndex + ". " + task);
            currIndex += 1;
        }
    }
    public void markReply(String indexStr) {
        if(indexStr.matches("^[0-9]+$")) {
            int index = Integer.parseInt(indexStr) - 1;
            if (index >= 0 && index < this.taskArrayList.size()) {
                this.taskArrayList.get(index).markTaskDone();
                System.out.print(markAsDoneMsg + this.taskArrayList.get(index));
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


