package vn.edu.tdmu.enums;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */
public enum State{
    ACTIVE("Active"),INACTIVE("Inactive"),DELETED("Deleted"), LOCKED("Locked");

    public String state;

    private State(final String state){
        this.state = state;
    }

    public String getState(){
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }

    public String getName(){
        return this.name();
    }
}