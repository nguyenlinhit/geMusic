package vn.edu.tdmu.enums;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */

public enum Country{
    UNKNOWN("Unknown"),VN("Việt Nam"),US("United States"),UK("United Kingdom"), FR("France"), KR("Korea"), CA("Canada");

    String country;

    Country(String country){
        this.country = country;
    }

    public String getCountry(){
        return this.country;
    }
}