package com.brianneldon.ilovezappos;

import java.util.List;

/**
 * Created by Brian on 2/9/2017.
 * This keeps a static list of the results returned from the API.
 * it also keeps track of the currently displayed product.
 */

public class ProductManager {

    private static List<Product> products;
    private static Product currentProduct;
    private static int cartCount = 0;

    public static List<Product> getProducts() {return products;}

    public static void setProducts(List<Product> products) {ProductManager.products = products;}

    public static Product getCurrentProduct() {
        return currentProduct;
    }

    public static void setCurrentProduct(int index) {
        if(index >= 0 && index < products.size()){
            currentProduct = products.get(index);
        }
    }
    public static int getCurrentResultCount(){
        return products.size();
    }

    public static int getCartCount(){
        return cartCount;
    }

    public static void incCartCount(){
        cartCount++;
    }


}
