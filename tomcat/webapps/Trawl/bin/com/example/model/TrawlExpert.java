package com.example.model;
import java.util.*;
public class TrawlExpert {

public List getBrands(String phylum) {
    List brands = new ArrayList();
    if (phylum.equals("Arthropoda")) {
        brands.add("Some arthropoda data");
        brands.add("Some more Arthropoda data");
    }
    else {
        brands.add("This aint arthropoda");
        brands.add("Under construction");
    }
    return brands;
    }
}

// For Testing on Java's Console
// public static void main(String[]args){
//     String color = "amber";
//     System.out.println(brand.get(0));
// }