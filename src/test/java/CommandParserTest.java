import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    @Test
    public void commandTypeTest() {
        CommandParser commandParser = new CommandParser();

       String[] commandStrArr = {"list","done 2", "todo Buy Eggs", "lorem ipsum"};
       String[] commandTypeStrArr= {"list", "done", "todo", "unknown"};

       for (int idx=0;idx<commandStrArr.length;idx++) {
           commandParser.setInputStr(commandStrArr[idx]);
           assertEquals(commandParser.getCommandText(), commandTypeStrArr[idx]);
       }
    }
}
