import java.util.ArrayList;

public class TaskList {

    private static final String numberOfTasksMsg_1 = "Now you have ", getNumberOfTaskMsg_2 = " task",
            getNumberOfTasksMsg_2 = " tasks",
            getGetNumberOfTasksMsg_3 = " in the list",
            taskAddedMsg = "Got it. I've added this task: \n", taskDeletedMsg = "Noted. I've removed this task:",
            deleteTaskNotIntegerMsg = "Sorry! The index you entered was not an integer. Please try again.",
            deleteTaskOutOfBoundsMsg = "Sorry! The task with that index does not exist. Please try again.";

    private ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getList() {
       return this.taskArrayList;
    }

    public String toString() {
        return getTaskListStr(this.taskArrayList);
    }

    private String getTaskListStr(ArrayList<Task> taskArrayList) {
        int currIndex = 1;
        String outStr = "";
        for (Task task : taskArrayList) {
            outStr += currIndex + ". " + task + "\n";
            currIndex += 1;
        }

        return outStr;
    }

    public String getTasksLeftStr() {
        String taskText = (this.taskArrayList.size() > 1) ? getNumberOfTasksMsg_2 : getNumberOfTaskMsg_2;
        return numberOfTasksMsg_1 + this.taskArrayList.size() + taskText + getGetNumberOfTasksMsg_3;
    }

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

    public void addTask(Task task) {
        this.taskArrayList.add(task);
        System.out.println(taskAddedMsg + task);
        System.out.println(getTasksLeftStr());
    }

    public boolean markAsDone(int index) {
        index -= 1;
        if (index < 0 || index >= this.taskArrayList.size()) {
            return false;
        }

        this.taskArrayList.get(index).markTaskDone();

        return true;
    }

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
