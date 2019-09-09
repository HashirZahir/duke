import java.util.Date;

/**
 * Class to handle deadline task related operations. Extends base class Task..
 */
public class Deadline extends Task{

    Date date;
    DateParser dateParser;

    /**
     * Initialize deadline object with description and date.
     * @param descriptionStr Description string of deadline task.
     * @param dateStr Date string of deadline task.
     */
    public Deadline(String descriptionStr, String dateStr){
        super(descriptionStr, "D", dateStr);
        this.dateParser = new DateParser();
        this.date = dateParser.getDateObj(dateStr);
    }

    /**
     * Get the string representation of deadline object.
     * @return String representation of deadline object.
     */
    public String toString() {
        return super.toString() + "(by: " + this.dateParser.getDateStr(date) + ")";
    }
}
