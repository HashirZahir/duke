/**
 * Generic Task class to implement common functions for events, todos and deadlines.
 */
public class Task{
    protected String descriptionStr, taskLetter;
    protected String[] argStrArr;
    protected boolean isDone;

    /**
     * Initialize task description and other task specific arguments.
     * @param descriptionStr Description of task.
     * @param taskLetter One letter to represent task.
     * @param argStrArr List of argument strings for tasks.
     */
    public Task(String descriptionStr, String taskLetter, String ...argStrArr) {
        this.descriptionStr = descriptionStr;
        this.taskLetter = taskLetter;
        this.argStrArr = argStrArr;
        this.isDone = false;
    }

    /**
     * Mark task status as done.
     */
    public void markTaskDone() {
        this.isDone = true;
    }

    /**
     * Get task status.
     * @return Boolean on whether task is done.
     */
    public boolean getTaskMarkDoneStatus() {
        return this.isDone;
    }

    /**
     * Get description string of task.
     * @return Description string of task.
     */
    public String getDescriptionStr() {
        return this.descriptionStr;
    }

    /**
     * Get arguments of task.
     * @return String array of task arguments.
     */
    public String[] getArgStrArr() {
        return argStrArr;
    }

    /**
     * Get one letter character of task.
     * @return One letter representation of task.
     */
    public String getTaskLetter() {
        return this.taskLetter;
    }

    /**
     * String representation of generic Task object. Used by other task extended classes as well.
     * @return String representation of Task object.
     */
    public String toString() {
        String outStr = "";
        outStr += "[" + getTaskLetter() + "]";

        if (getTaskMarkDoneStatus()) {
            outStr += "[✓] ";
        }
        else {
            outStr += "[✗] ";
        }

        outStr += this.descriptionStr;

        return outStr;
    }
}