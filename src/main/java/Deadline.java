public class Deadline extends Task{

    String dayStr;

    public Deadline(String descriptionStr, String dayStr){
        super(descriptionStr, "D", dayStr);
        this.dayStr = dayStr;
    }

    public String toString() {
        return super.toString() + "(" + this.dayStr + ")";
    }
}
