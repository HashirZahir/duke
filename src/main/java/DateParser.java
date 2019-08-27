import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateParser {

    private Date date;
    private SimpleDateFormat dateFormatter;

    public DateParser(String dateTimeStr) {
        this.dateFormatter = new SimpleDateFormat("dd/mm/yyyy hhmm");
        try {
            this.date = this.dateFormatter.parse(dateTimeStr);
        }
        catch (ParseException pe) {
            // if there is error, save as current date. TODO: Better error resolution
            System.out.println(pe);
            this.date = new Date();
        }
    }

    public String getDateTimeStr() {
        return this.dateFormatter.format(this.date);
    }
}
