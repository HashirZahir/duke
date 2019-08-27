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
    private static final String numberOfTasksMsg_1 = "Now you have ", getNumberOfTaskMsg_2 = " task",
                                getNumberOfTasksMsg_2 = " tasks", getGetNumberOfTasksMsg_3 = " in the list";

    private static FileIOManager fileIOManager;
    private static ArrayList<Task> taskArrayList;
    private CommandParser commandParser;

    public static void main(String[] args) {
        Duke duke = new Duke();
        fileIOManager = new FileIOManager();
        Scanner scanner = new Scanner(System.in);
        String inputStr = "";

        taskArrayList = fileIOManager.loadSavedData();
        duke.greeting();

        while (true) {
            if (scanner.hasNextLine()) {
                inputStr = scanner.nextLine();
                if (!duke.reply(inputStr)) break;
            }
        }
    }

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
        String[] commandArgs = commandParser.getArgs();
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
                markReply(commandArgs[0]);
                break;
            case TODO:
                t = new Todo(commandArgs[0]);
                addReply(t);
                break;
            case DEADLINE:
                t = new Deadline(commandArgs[0], commandArgs[1]);
                addReply(t);
                break;
            case EVENT:
                t = new Event(commandArgs[0], commandArgs[1]);
                addReply(t);
                break;
            case IGNORE:
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
        String taskText = (this.taskArrayList.size()>1) ? getNumberOfTasksMsg_2 : getNumberOfTaskMsg_2;
        taskText = taskAddedMsg + task + "\n" + numberOfTasksMsg_1 + this.taskArrayList.size() + taskText + getGetNumberOfTasksMsg_3;
        System.out.println(taskText);

        fileIOManager.saveData(this.taskArrayList);
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

        fileIOManager.saveData(this.taskArrayList);
    }
}


