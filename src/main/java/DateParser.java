import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Class to parse strings to Java Date object and get string representation of Date object.
 */
public class DateParser {

    private Date date;
    private SimpleDateFormat dateFormatter;

    /**
     * Initialize date parser with required input format.
     */
    public DateParser() {
        this.dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    /**
     * Parse the date given in string format and get back Java Date object.
     * @param dateTimeStr Date as a string following format shown in class constructor.
     * @return Java Date object of the input date string.
     */
    public Date getDateObj(String dateTimeStr) {
        try {
            this.date = this.dateFormatter.parse(dateTimeStr);
        } catch (ParseException pe) {
            // if there is error, save as current date. TODO: Better error resolution
            System.out.println(pe);
            this.date = new Date();
        }

        return this.date;
    }

    /**
     * Custom string representation of Java Date object.
     * @param date Input Java Date object.
     * @return String representation of Java Date object.
     */
    public String getDateStr(Date date) {
        return this.dateFormatter.format(date);
    }

}
