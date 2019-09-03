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

    public Ui() {
        this.commandParser = new CommandParser();
        fileIOManager = new FileIOManager();
        this.taskList = new TaskList(fileIOManager.loadSavedData());
        greeting();
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

    public void addReply(Task task) {
        this.taskList.addTask(task);
        fileIOManager.saveData(this.taskList.getList());
    }



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
