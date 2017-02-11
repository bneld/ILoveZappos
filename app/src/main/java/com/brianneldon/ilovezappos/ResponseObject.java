package com.brianneldon.ilovezappos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/1/2017.
 * The object that is populated by the json returned from the Zappos API.
 */

public class ResponseObject {
    private String originalTerm;
    private int currentResultCount;
    private int totalResultCount;
    private String term;
    private List<Product> results;
    private String statusCode;

    public List<Product> getResults() {return results;}

    public void setResults(List<Product> results) {this.results = results;}

    public ResponseObject(){
        results = new ArrayList<>();
    }

    public String toString(){
        String string =  "Original term: " + originalTerm
                + "\ncurrentResultCount: " + currentResultCount
                + "\ntotalResultCount: " + totalResultCount
                + "\nterm: " + term
                + "\nstatusCode: " + statusCode;
        return string;
    }


}
