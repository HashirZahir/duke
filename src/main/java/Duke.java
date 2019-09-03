import java.util.Scanner;

public class Duke {

    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        this.ui = new Ui();
    }

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

