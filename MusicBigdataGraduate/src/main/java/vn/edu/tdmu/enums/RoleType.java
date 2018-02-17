package vn.edu.tdmu.enums;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */
public enum RoleType{
    USER("USER"),ADMIN("ADMIN"),DBA("DBA");

    private String roleType;

    RoleType(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType(){
        return this.roleType;
    }
}