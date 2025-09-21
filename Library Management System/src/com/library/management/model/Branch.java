package com.library.management.model;

public class Branch {
    private final String branchId;
    private String name;
    private String location;

    public Branch(String branchId, String name, String location) {
        this.branchId = branchId;
        this.name = name;
        this.location = location;
    }
    public String getBranchId() {return branchId;}
    public String getName() {return name;}
    public String getLocation() {return location;}

    public void setName(String name) {this.name = name;}
    public void setLocation(String location) {this.location = location;}

    @Override
    public String toString() {
        return "Branch [branchId=" + branchId + ", name=" + name + ", location=" + location + "]";
    }
}
