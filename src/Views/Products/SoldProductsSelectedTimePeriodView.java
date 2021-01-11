package Views.Products;

import Views.View;

public class SoldProductsSelectedTimePeriodView extends View {

    public String getStartingDate()
    {
        System.out.println("Enter starting date");
        String startingDate = scanner.nextLine();

        return startingDate;
    }

    public String getFinalDate()
    {
        System.out.println("Enter final date");
        String finalDate = scanner.nextLine();

        return finalDate;
    }

    public void printNoSalesDuringThisPeriod()
    {
        System.out.println("No sales during this period");
    }

    public void printInvalidDateMessage()
    {
        System.out.println("Enter valid date for example: DD.MM.YY");
    }
}
