public class Task{
    String name;
    boolean is_done;

    public Task(String name) {
        this.name = name;
        this.is_done = false;
    }

    public void markTaskDone() {
        this.is_done = true;
    }

    public String toString() {
        String outStr = "";

        if (this.is_done) {
            outStr += "[✓] ";
        }
        else {
            outStr += "[✗] ";
        }

        outStr += this.name + "\n";
        return outStr;
    }
}