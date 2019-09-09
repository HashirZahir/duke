import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class to handle file input/output (IO) operations.
 */
public class FileIOManager {

    private final String dukeFileLocationStr = "data/", dukeFileNameStr = "duke_tasks.txt",
                        dukeFileStr = dukeFileLocationStr + dukeFileNameStr, fileCharRegex = " \\| ", fileCharText = " | ",
                        noFileErrorText = "Could not find previous saved data for Duke, starting fresh new session....\n";

    private BufferedReader fileReader;
    private BufferedWriter fileWriter;

    public FileIOManager() {}

    /**
     * Load the previously saved data from disk.
     * @return Return array list of task objects from file.
     */
    public ArrayList<Task> loadSavedData() {
        ArrayList<Task> taskArr = new ArrayList<Task>();

        try {
            fileReader = new BufferedReader(new FileReader(dukeFileStr));
            for(String lineStr; (lineStr = fileReader.readLine()) != null; ) {
                Task t = getTaskFromStr(lineStr);
                taskArr.add(t);
            }

            fileReader.close();
        }
        catch (IOException e) {
            System.out.println(e + "\n" + noFileErrorText);
        }

        return taskArr;
    }

    /**
     * Save list of tasks to file.
     * @param taskArr Current list of tasks that need to be saved to file.
     */
    public void saveData(ArrayList<Task> taskArr) {
        try {
            fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dukeFileStr)));
        }
        catch (FileNotFoundException e) {
            new File(dukeFileLocationStr).mkdirs();
            saveData(taskArr);
        }

        try {
            for (Task t : taskArr) {
                fileWriter.write(getStrFromTask(t));
            }

            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error while saving task to file: " + e);
        }

    }

    /**
     * Get task object from string in file.
     * @param inputStr A line of string from file.
     * @return Task object representation of inputStr.
     */
    private Task getTaskFromStr(String inputStr) {
        Task task;
        String[] splitStr = inputStr.split(fileCharRegex);
        String taskLetter = splitStr[0];
        boolean taskIsDone = Integer.parseInt(splitStr[1]) != 0;
        String[] args = Arrays.copyOfRange(splitStr, 2, splitStr.length);

        switch (taskLetter) {
            case "E":
               task = new Event(args[0], args[1]);
               break;
            case "D":
                task = new Deadline(args[0], args[1]);
                break;
            case "T":
                task = new Todo(args[0]);
                break;
            default:
                // TODO: How to handle malformed/unknown string?
                task = new Task("", "");
                break;
        }

        if (taskIsDone) {
            task.markTaskDone();
        }

        return task;
    }

    /**
     * Get string to be saved to file from task object.
     * @param t Task object to get string format of.
     * @return String with separators to be stored in file to be recovered later.
     */
    private String getStrFromTask(Task t) {
        String outStr = "";
        outStr += t.getTaskLetter() + fileCharText;
        outStr += (t.getTaskMarkDoneStatus() ? "1" : "0") + fileCharText;
        outStr += t.getDescriptionStr();
        for (String argStr : t.getArgStrArr()) {
            outStr += fileCharText + argStr;
        }

        return outStr+"\n";
    }
}
