import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void parseDateTest() {
        String[] dateStrArr = {"04/06/2018 1030", "12/12/2010 0620"};
        // NOTE: Month value for gregorian calendar is 0 based
        Integer[][] dateYYMMDDHHmmStrArr = {{2018, 5, 4, 10, 30}, {2010, 11, 12, 06, 20}};

        for (int idx=0;idx<dateStrArr.length;idx++) {
            DateParser dateParser = new DateParser();
            Date dateParserDate = dateParser.getDateObj(dateStrArr[idx]);

            Integer[] dateYYMMDDHHmmStr = dateYYMMDDHHmmStrArr[idx];
            Integer year = dateYYMMDDHHmmStr[0], month = dateYYMMDDHHmmStr[1],
                   day = dateYYMMDDHHmmStr[2], hour = dateYYMMDDHHmmStr[3],
                   minute = dateYYMMDDHHmmStr[4];
            Date actualDate = new GregorianCalendar(year, month, day, hour, minute).getTime();

            assertEquals(actualDate, dateParserDate);
        }
    }
}
