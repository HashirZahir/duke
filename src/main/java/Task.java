public class Task{
    protected String descriptionStr, taskLetter;
    protected String[] argStrArr;
    protected boolean is_done;

    public Task(String descriptionStr, String taskLetter, String ...argStrArr) {
        this.descriptionStr = descriptionStr;
        this.taskLetter = taskLetter;
        this.argStrArr = argStrArr;
        this.is_done = false;
    }

    public void markTaskDone() {
        this.is_done = true;
    }

    public boolean getTaskMarkDoneStatus() {
        return this.is_done;
    }

    public String getDescriptionStr() {
        return this.descriptionStr;
    }

    public String[] getArgStrArr() {
        return argStrArr;
    }

    public String getTaskLetter() {
        return this.taskLetter;
    }

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