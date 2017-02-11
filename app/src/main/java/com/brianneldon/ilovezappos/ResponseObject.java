package com.brianneldon.ilovezappos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/1/2017.
 */

public class ResponseObject {
    private String originalTerm;
    private int currentResultCount;
    private int totalResultCount;
    private String term;
    private List<Product> results;
    private String statusCode;
//    private static Product currentProduct;

//    public static Product getCurrentProduct() {
//        return currentProduct;
//    }
//
//    public static void setCurrentProduct(int index) {
//        if(index >= 0 && index < results.size()){
//            currentProduct = results.get(index);
//        }
//    }

//    public static int getCurrentResultCount() {return currentResultCount;}
//
//    public static void setCurrentResultCount(int count) {currentResultCount = count;}
//
    public List<Product> getResults() {return results;}

    public void setResults(List<Product> results) {this.results = results;}

    public ResponseObject(){
        results = new ArrayList<>();
    }

//    public static ResponseObject parseJSON(String response) {
//        Gson gson = new GsonBuilder().create();
//        ResponseObject responseObject = gson.fromJson(response, ResponseObject.class);
//        return responseObject;
//    }

    public String toString(){
        String string =  "Original term: " + originalTerm
                + "\ncurrentResultCount: " + currentResultCount
                + "\ntotalResultCount: " + totalResultCount
                + "\nterm: " + term
                + "\nstatusCode: " + statusCode;
        return string;
    }


}
