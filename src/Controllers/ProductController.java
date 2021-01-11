package Controllers;

import DBOperations.ProductOperations;
import DBOperations.SellerOperations;
import Models.Order;
import Models.Product;
import Models.User;
import Validation.InputValidator;
import Views.Products.*;
import Views.Validations.ProductValidationView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private UserSession userSession;

    public ProductController(UserSession userSession) {
        this.userSession = userSession;
    }
    ProductValidationView productInputValidation = new ProductValidationView();

    public void addProduct() {
        AddProductView addProductView = new AddProductView();
        Product product = null;
        while (product == null) {
            String productName = addProductView.getProductName();
            while (!InputValidator.isValidString(productName)) {
                productInputValidation.printEnterValidProductName();

                productName = addProductView.getProductName();
            }
            String productCategory = addProductView.getProductCategory();
            while (!InputValidator.isValidString(productCategory)) {
                productInputValidation.printEnterValidProductCategory();

                productCategory = addProductView.getProductCategory();
            }
            Integer productStock = addProductView.getProductStock();
            while (productStock == null) {
                productInputValidation.printEnterValidProductQuantity();

                productStock = addProductView.getProductStock();
            }

            Double productPrice = addProductView.getProductPrice();
            while(productPrice == null)
            {
                productInputValidation.printEnterValidProductPrice();

                productPrice = addProductView.getProductPrice();
            }
            product = new Product(ProductOperations.getAutoIncrementValue() + 1,
                    productName, productStock, productCategory, productPrice);
            boolean isSuccessful = ProductOperations.addProduct(product);
            if (isSuccessful)
                addProductView.printSuccessfulAddedProduct();
            else
                addProductView.printUnsuccessfulAddedProduct();
        }
    }

    public void editProduct() {
        EditProductView editProductView = new EditProductView();
        Product product = null;

        while (product == null) {
            Integer productID = editProductView.getProductID();

            while(productID == null || ProductOperations.findByID(productID) == null)
            {
                if(productID == null) {
                    productInputValidation.printEnterValidProductID();
                }
                else {
                    product = ProductOperations.findByID(productID);
                }

                if(product != null)
                {
                    if(productID !=null)
                    {
                        boolean isSuccessful = ProductOperations.updateProduct(productID, product);
                        if(isSuccessful) {
                            editProductView.printSuccessfulEditProduct();
                            break;
                        }
                        else {
                            editProductView.printUnsuccessfulEditProduct();
                        }
                    }
                }
                else if(product == null){
                    if(productID != null)
                        editProductView.printProductNotFound();
                }

                productID = editProductView.getProductID();
            }
                String productName = editProductView.getNewProductName();
            while (!InputValidator.isValidString(productName)) {
                productInputValidation.printEnterValidProductName();

                productName = editProductView.getNewProductName();
            }
                Integer productQuantity = editProductView.getProductQuantity();
            while (productQuantity == null) {
                productInputValidation.printEnterValidProductQuantity();

                productQuantity = editProductView.getProductQuantity();
            }
                String productCategory = editProductView.getProductCategory();
            while (!InputValidator.isValidString(productCategory)) {
                productInputValidation.printEnterValidProductCategory();

                productCategory = editProductView.getProductCategory();
            }
                Double productPrice = editProductView.getNewProductPrice();
            while(productPrice == null)
            {
                productInputValidation.printEnterValidProductPrice();

                productPrice = editProductView.getNewProductPrice();
            }
                product = new Product(productID, productName, productQuantity, productCategory, productPrice);
                if(product != null)
                {
                    boolean isSuccessful = ProductOperations.updateProduct(productID, product);
                    if (isSuccessful) {
                        editProductView.printSuccessfulEditProduct();
                    } else
                        editProductView.printProductNotFound();
                }
        }

    }

    public void deleteProduct() {
        DeleteProductView deleteProductView = new DeleteProductView();
        Product product = null;

        while (product == null) {
            Integer productID = deleteProductView.getProductID();

            while(productID == null || ProductOperations.findByID(productID) == null)
            {
                if(productID == null) {
                    productInputValidation.printEnterValidProductID();
                }
                else {
                    product = ProductOperations.findByID(productID);
                }

                if(product != null)
                {
                    if(productID !=null)
                    {
                        boolean isSuccessful = ProductOperations.deleteProduct(product);
                        if(isSuccessful) {
                            deleteProductView.printSuccessfulDeletedProduct();
                            break;
                        }
                        else {
                            deleteProductView.printUnsuccessfulDeletedProduct();
                        }
                    }
                }
                else if(product == null){
                    if(productID != null)
                        deleteProductView.printProductNotFound();
                }

                productID = deleteProductView.getProductID();
            }

            product = ProductOperations.findByID(productID);
            if(product != null) {
                boolean isSuccessful = ProductOperations.deleteProduct(product);
                if(isSuccessful) {
                    deleteProductView.printSuccessfulDeletedProduct();
                }
                else {
                    deleteProductView.printUnsuccessfulDeletedProduct();
                }
            }
        }
    }

    public void readAllData() {
        ProductsOverviewView productsOverviewView = new ProductsOverviewView();
        productsOverviewView.printProductsOverViewMessage();
        ProductOperations.readAllData();
    }

    public void sellProduct() {
        SellProductView sellProductView = new SellProductView();
        Product product = null;

        Integer soldProductID = sellProductView.getProductID();


        while(soldProductID == null || ProductOperations.findByID(soldProductID) == null) {
            if (soldProductID != null) {
                product = ProductOperations.findByID(soldProductID);
            }

            if (product != null) {
                if (soldProductID != null) {
                    boolean isSuccessful = ProductOperations.deleteProduct(product);
                    if (isSuccessful) {
                        sellProductView.printSuccessfulSoldProduct();
                        break;
                    } else {
                        sellProductView.printUnsuccessfulSoldProduct();
                    }
                }
            } else if (product == null) {
                if (soldProductID != null)
                    sellProductView.printUnsuccessfulSoldProduct();
                else
                    sellProductView.printInvalidProductID();
            }
            soldProductID = sellProductView.getProductID();
        }

                product = ProductOperations.findByID(soldProductID);
                Integer soldQuantity = sellProductView.getSoldProductQuantity();

                if(soldQuantity == null)
                {
                    while (soldQuantity == null) {
                        sellProductView.printInvalidQuantityMessage();

                        soldQuantity = sellProductView.getSoldProductQuantity();
                    }
                }
                else
                {
                    if (product != null) {
                        if (ProductOperations.sellProduct(product, soldQuantity, userSession.getUser()))
                            sellProductView.printSuccessfulSoldProduct(soldQuantity);
                    } else
                        sellProductView.printUnsuccessfulSoldProduct();
                }
            }


    public void viewSoldProducts() {
        SoldProductsView soldProductsView = new SoldProductsView();
        soldProductsView.printSoldProductsList();

        ProductOperations.viewSoldProducts();
    }

    public void readSoldProductsForSpecificSeller() {
        SoldProductsViewForSpecificSeller soldProductsViewForSpecificSeller = new SoldProductsViewForSpecificSeller();
        List<Order> orders = new ArrayList<>();
        User seller = null;
        while(seller == null) {
            while (orders.isEmpty()) {
                Integer sellerID = soldProductsViewForSpecificSeller.getSellerID();
                while(sellerID == null)
                {
                    soldProductsViewForSpecificSeller.invalidProductIDMessage();

                    sellerID = soldProductsViewForSpecificSeller.getSellerID();
                }

                seller = SellerOperations.findByID(sellerID);
                if (seller != null) {
                    if (SellerOperations.findSellerInSoldProducts(sellerID) != null) {
                        orders = ProductOperations.readSoldProductsForSpecificSeller(seller);
                    } else {
                        soldProductsViewForSpecificSeller.noProductsSoldYet();
                        break;
                    }
                } else
                    soldProductsViewForSpecificSeller.noSellerFound();
            }
        }
    }

    public void readSoldProductsSelectedTimePeriod() throws ParseException {
        SoldProductsSelectedTimePeriodView soldProductsSelectedTimePeriodView = new SoldProductsSelectedTimePeriodView();

        String startingDate = soldProductsSelectedTimePeriodView.getStartingDate();

        while(!InputValidator.isValidDate(startingDate))
        {
            soldProductsSelectedTimePeriodView.printInvalidDateMessage();

            startingDate = soldProductsSelectedTimePeriodView.getStartingDate();
        }


        String finalDate = soldProductsSelectedTimePeriodView.getFinalDate();

        while(!InputValidator.isValidDate(finalDate))
        {

            soldProductsSelectedTimePeriodView.printInvalidDateMessage();

            finalDate = soldProductsSelectedTimePeriodView.getFinalDate();
        }

        if(ProductOperations.readSoldProductsSelectedTimePeriod(startingDate, finalDate, userSession.getUser()));
        else
            soldProductsSelectedTimePeriodView.printNoSalesDuringThisPeriod();

        }

    public void lowProductQuantity()
    {
        ProductOperations.proccessLowQuantity();
    }


}
