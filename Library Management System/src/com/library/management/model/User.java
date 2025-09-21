package com.library.management.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User{
    private final String userId;
    private  String name;
    private  String email;
    private  String  phone;
    private final LocalDate joinDate;
    private final List<String> borrowingHistory = new ArrayList<>();// store loanId

// Constructor
    public User(String userId,String name,String email,String phone,LocalDate joinDate)
    {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.joinDate = joinDate;
    }

    public String getUserId() { return userId;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public LocalDate getJoinDate() {return joinDate;}

    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}

    public void addToHistory(String loanId){ borrowingHistory.add(loanId);}
    public List<String> getBorrowingHistory() {return Collections.unmodifiableList(borrowingHistory);}
    @Override
    public String toString() { return String.format("%s | %s | %s",userId,name,email);}

}