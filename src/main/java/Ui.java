/**
 * Class to handle all user replies of Duke.
 */
public class Ui {
    private static String logo = "\n ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String welcomeMsg = "Hello from" + logo + "What can I do for you today?",
            quitMsg = "Bye. Hope to see you again!\n" + logo, markAsDoneMsg = "Nice! I've marked this task as done:\n";




    private CommandParser commandParser;
    private FileIOManager fileIOManager;
    private TaskList taskList;

    /**
     * Initialize Ui class with command parser, file manager and task list.
     */
    public Ui() {
        this.commandParser = new CommandParser();
        fileIOManager = new FileIOManager();
        this.taskList = new TaskList(fileIOManager.loadSavedData());
        greeting();
    }

    /**
     * Display greeting to user.
     */
    public void greeting() {
        System.out.println(welcomeMsg);
    }

    /**
     * Replies to the input given by user.
     * @param inputStr String entered by user to reply to.
     * @return Boolean status if exit command has been issued. True implies normal reply
     * while false implies exit command has been issued.
     */
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
                System.out.println(this.taskList);
                break;
            case DELETE:
                this.taskList.deleteTask(commandArgs[0]);
                fileIOManager.saveData(this.taskList.getList());
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
            case FIND:
                this.taskList.findTask(commandArgs[0]);
                break;
            case IGNORE:
                break;
        }

        System.out.println(horizontalSeparator);
        return !isQuit;
    }

    /**
     * Add the task created from the user input to the task list class and
     * save the current state of task list to file.
     * @param task Task to be added to task list for update.
     */
    public void addReply(Task task) {
        this.taskList.addTask(task);
        fileIOManager.saveData(this.taskList.getList());
    }

    /**
     * Reply to mark done command. Handles integer parsing and index out of bound errors.
     * @param indexStr String index of task list to be marked as done.
     */
    public void markReply(String indexStr) {
        int index = Integer.parseInt(indexStr);

        if (this.taskList.markAsDone(index)) {
            System.out.println(markAsDoneMsg + this.taskList.getList().get(index));
        }
        else {
            System.out.println("Index provided is out of range. Cannot mark as done");
        }


        fileIOManager.saveData(this.taskList.getList());
    }

}
