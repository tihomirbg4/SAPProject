package Views;

import java.util.Scanner;

public abstract class View {
    protected Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }
}
