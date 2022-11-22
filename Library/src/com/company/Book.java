package com.company;

import java.util.ArrayList;

public class Book {
    private String name;
    private String authorName;
    private String id;
    private int year;
    private String status;
    private ArrayList<Loan> loans;


    public Book(){
        this.status = "free";
        this.loans = new ArrayList<Loan>();
    }

    public void details(){
        System.out.println("name : " + name + "\nauthorName : " + authorName
                + "\nid : " + id + "\nyear : " + year);
    }


    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
