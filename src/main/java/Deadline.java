public class Deadline extends Task{

    String dayStr;

    public Deadline(String descriptionStr, String dayStr){
        super(descriptionStr);
        this.dayStr = dayStr;
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + this.dayStr + ")";
    }
}
