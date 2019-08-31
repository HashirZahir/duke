import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateParser {

    private Date date;
    private SimpleDateFormat dateFormatter;

    public DateParser() {
        this.dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

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

    public String getDateStr(Date date) {
        return this.dateFormatter.format(date);
    }

}
