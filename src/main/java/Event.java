public class Event extends Task{
    String dateStr;

    public Event(String descriptionStr, String dateStr){
        super(descriptionStr, "E", dateStr);
        this.dateStr = dateStr;
    }

    public String toString() {
        return super.toString() + "(" + this.dateStr + ")";
    }
}
