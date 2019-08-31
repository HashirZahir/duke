import java.util.Date;

public class Deadline extends Task{

    Date date;
    DateParser dateParser;

    public Deadline(String descriptionStr, String dateStr){
        super(descriptionStr, "D", dateStr);
        this.dateParser = new DateParser();
        this.date = dateParser.getDateObj(dateStr);
    }

    public String toString() {
        return super.toString() + "(by: " + this.dateParser.getDateStr(date) + ")";
    }
}
