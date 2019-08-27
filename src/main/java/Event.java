public class Event extends Task{
    String dateStr;

    public Event(String descriptionStr, String dateStr){
        super(descriptionStr);
        this.dateStr = dateStr;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + this.dateStr + ")";
    }
}
