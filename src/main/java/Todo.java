/**
 * Class to handle todo task related operations. Extends base class Task.
 */
public class Todo extends Task{
    /**
     * Initialize object with description.
     * @param description Description text of todo task.
     */
    public Todo(String description){
        super(description, "T");
    }

    /**
     * Return String representation of todo object.
     * @return String representation of todo object.
     */
    public String toString() {
        return super.toString();
    }
}
