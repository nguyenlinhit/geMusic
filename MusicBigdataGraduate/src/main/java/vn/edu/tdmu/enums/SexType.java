package vn.edu.tdmu.enums;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */
public enum SexType{
    MALE("Male"),FEMALE("Female");

    String sexType;

    private SexType(String sexType){
        this.sexType = sexType;
    }

    public String getSexType(){
        return this.sexType;
    }
}