package Validation;

import java.util.Scanner;

public abstract class Validator {
    protected Scanner scanner;

    public Validator() {
        this.scanner = new Scanner(System.in);
    }
}
