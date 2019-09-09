import java.util.ArrayList;

/**
 * Class to handle operations on list of tasks.
 */
public class TaskList {

    private static final String numberOfTasksMsg_1 = "Now you have ", getNumberOfTaskMsg_2 = " task",
            getNumberOfTasksMsg_2 = " tasks",
            getGetNumberOfTasksMsg_3 = " in the list",
            taskAddedMsg = "Got it. I've added this task: \n", taskDeletedMsg = "Noted. I've removed this task:",
            deleteTaskNotIntegerMsg = "Sorry! The index you entered was not an integer. Please try again.",
            deleteTaskOutOfBoundsMsg = "Sorry! The task with that index does not exist. Please try again.";

    private ArrayList<Task> taskArrayList;

    /**
     * Initialize object with array list of tasks.
     * @param taskArrayList Input array list of tasks.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Get the list of tasks.
     * @return array list of tasks.
     */
    public ArrayList<Task> getList() {
       return this.taskArrayList;
    }

    /**
     * String representation of task list object.
     * @return String representation of task list object.
     */
    public String toString() {
        return getTaskListStr(this.taskArrayList);
    }

    /**
     * String representation of list of tasks.
     * @param taskArrayList Input task array list.
     * @return String representation of taskArrayList.
     */
    private String getTaskListStr(ArrayList<Task> taskArrayList) {
        int currIndex = 1;
        String outStr = "";
        for (Task task : taskArrayList) {
            outStr += currIndex + ". " + task + "\n";
            currIndex += 1;
        }

        return outStr;
    }

    /**
     * Display number of tasks in the task list.
     * @return String formatted output of number of tasks in taskArrayList.
     */
    public String getTasksLeftStr() {
        String taskText = (this.taskArrayList.size() > 1) ? getNumberOfTasksMsg_2 : getNumberOfTaskMsg_2;
        return numberOfTasksMsg_1 + this.taskArrayList.size() + taskText + getGetNumberOfTasksMsg_3;
    }

    /**
     * Find the task given input matching text and output to terminal.
     * @param findStr String to be used to search in task list.
     */
    public void findTask(String findStr) {
        ArrayList<Task> foundTaskList = new ArrayList<Task>();
        for (Task task : taskArrayList) {
            if (task.toString().contains(findStr)) {
                foundTaskList.add(task);
            }
        }

        if (foundTaskList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");
            System.out.println(getTaskListStr(foundTaskList));
        }
        else {
            System.out.println("No match found!");
        }


    }

    /**
     * Add task to task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
        System.out.println(taskAddedMsg + task);
        System.out.println(getTasksLeftStr());
    }

    /**
     * Mark task in task list as done given index in list. Index out of range handled as well.
     * @param index Index of task to be marked as done.
     * @return Boolean if task existed and was marked as done.
     */
    public boolean markAsDone(int index) {
        index -= 1;
        if (index < 0 || index >= this.taskArrayList.size()) {
            return false;
        }

        this.taskArrayList.get(index).markTaskDone();

        return true;
    }

    /**
     * Delete task from task list.
     * @param indexStr Index string of task to be deleted from the task list.
     */
    public void deleteTask(String indexStr) {
        int index = 0;
        try {
            index = Integer.parseInt(indexStr);
        }
        catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println(deleteTaskNotIntegerMsg);
            return;
        }

        // 0-index arrayList
        index -= 1;

        if (index >= taskArrayList.size() || index < 0) {
            System.out.println("You have opted to remove task no. " + (index+1));
            System.out.println(deleteTaskOutOfBoundsMsg);
            System.out.println("Please enter index in range of 1 to " + taskArrayList.size() + " inclusive");
        }
        else {
            System.out.println(taskDeletedMsg);
            System.out.println(taskArrayList.get(index));
            taskArrayList.remove(index);
            System.out.println(getTasksLeftStr());
        }
    }
}
