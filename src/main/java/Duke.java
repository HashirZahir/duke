import java.util.Scanner;

/**
 * Main class for Duke project.
 */
public class Duke {

    private Ui ui;

    /**
     * Main function to be called for Duke class.
     * @param args Command line arguments passed to main. Not used.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initialize user interface of Duke.
     */
    public Duke() {
        this.ui = new Ui();
    }

    /**
     * Main function that runs forever replying to inputs until exit command entered.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inputStr = "";
        while (true) {
            if (scanner.hasNextLine()) {
                inputStr = scanner.nextLine();

                // continue replying until quitMsg "bye" is entered by user, else quit duke
                if (!this.ui.reply(inputStr)) break;
            }
        }
    }


}

