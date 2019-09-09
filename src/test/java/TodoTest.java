import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoTestDescription() {
        Task todo = new Todo("Buy Eggs");
        assertEquals(todo.toString(), "[T][✗] Buy Eggs");
    }
}
