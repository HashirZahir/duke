/**
 * Class to handle event task related operations. Extends base class Task.
 */
public class Event extends Task{
    String dateStr;

    /**
     * Initialize event with description and date.
     * @param descriptionStr Description of event.
     * @param dateStr Date of event in specified format as per DateParser.
     */
    public Event(String descriptionStr, String dateStr){
        super(descriptionStr, "E", dateStr);
        this.dateStr = dateStr;
    }

    /**
     * String representation of event object. Uses parent class task toString as well.
     * @return String representation of event object.
     */
    public String toString() {
        return super.toString() + "(at: " + this.dateStr + ")";
    }
}
