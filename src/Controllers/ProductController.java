package Controllers;

import DBOperations.ProductOperations;
import Models.Product;
import Models.Seller;
import Views.Products.AddProductView;
import Views.Products.DeleteProductView;
import Views.Products.EditProductView;
import Views.Products.ProductsOverviewView;
import Views.Products.SellProductView;

public class ProductController {
    public Product addProduct()
    {
        AddProductView addProductView = new AddProductView();
        Product product = null;

        while(product == null)
        {
            String productName = addProductView.getProductName();
            String productCategory = addProductView.getProductCategory();
            int productStock = addProductView.getProductStock();
            double productPrice = addProductView.getProductPrice();
            product = new Product(ProductOperations.getAutoIncrementValue() + 1,
                    productName, productStock, productCategory, productPrice);
            boolean isSuccessful = ProductOperations.addProduct(product);
            if(isSuccessful)
                addProductView.printSuccessfulAddedProduct();
            else
            addProductView.printUnsuccessfulAddedProduct();
        }

        return product;
    }

    public Product editProduct() {
        EditProductView editProductView = new EditProductView();
        Product product = null;

        while(product == null)
        {
            int productID = editProductView.getProductID();
            String productName = editProductView.getNewProductName();
            int productStock = editProductView.getProductStock();
            String productCategory = editProductView.getProductCategory();
            double productPrice = editProductView.getProductPrice();
            product = new Product(productID, productName, productStock
            , productCategory, productPrice);
            boolean isSuccessful = ProductOperations.updateProduct(productID, product);
            if(ProductOperations.findByID(productID) != null && isSuccessful)
            {
                editProductView.printSuccessfulEditProduct();
            }
            else
                editProductView.printUnsuccessfulEditProduct();
        }

        return product;
    }

    public void deleteProduct() {
        DeleteProductView deleteProductView = new DeleteProductView();
        Product product = null;

        while(product == null)
        {
            int productID = deleteProductView.getProductID();
            product = ProductOperations.findByID(productID);

            if(ProductOperations.deleteProduct(product))
            {
                deleteProductView.printSuccessfulDeletedProduct();
            }
            else
            {
                deleteProductView.printUnsuccessfulDeletedProduct();
            }
        }
    }

    public void readAllData()
    {
        ProductsOverviewView productsOverviewView = new ProductsOverviewView();
        productsOverviewView.printProductsOverViewMessage();
        ProductOperations.readAllData();
    }

//    public Product sellProduct()
//    {
//        SellProductView sellProductView = new SellProductView();
//        Product product = null;
//
//        while(product == null)
//        {
//            int sellProductID = sellProductView.getProductID();
//            if(ProductOperations.findByID(sellProductID) != null)
//            {
//                int soldProductQuantity = sellProductID;
//                product = ProductOperations.findByID(sellProductID);
//               // boolean isSuccessful = ProductOperations.sellProduct(product, soldProductQuantity,);
//                if(isSuccessful)
//                    sellProductView.printSuccessfulSoldProduct();
//                else
//                    sellProductView.printUnsuccessfulSoldProduct();
//            }
//        }
//
//        return product;
//    }

}
