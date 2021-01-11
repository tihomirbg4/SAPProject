package Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static Scanner scanner;
    public InputValidator() {
        this.scanner = new Scanner(System.in);
    }

    public static boolean isValidString(String input)
    {
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public static boolean isValidInteger(int input)
    {

        boolean isValid = false;
        try
        {
            Integer.parseInt(String.valueOf(input));
            isValid = true;
        }
        catch(NumberFormatException e)
        {
        }

        return isValid;
    }

    public static boolean isValidDate(String dataString)
    {
        boolean isValid = false;
        try{
            new SimpleDateFormat("dd.MM.yyyy").parse(dataString);
            isValid = true;
        } catch (ParseException e) {

        }

        return isValid;
    }

}
