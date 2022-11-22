package sample;

import java.util.ArrayList;

public class Company {
    public Company(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        manager = null;
    }

    private String name;
    private String address;
    private ArrayList<Employee> employeeArrayList;
    private ArrayList<Product> productArrayList;
    private Manager manager;
    private String phoneNumber;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ArrayList<Employee> getEmployeeArrayList() {
        return employeeArrayList;
    }
    public void setEmployeeArrayList(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }
    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }
    public Manager getManager() {
        return manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void save(){
        Main.getApp().getCompanyFile().save(this);
    }


    public void remove(){
        Main.getApp().getCompanyFile().remove(this);
    }

    public void edit(){
        Main.getApp().getCompanyFile().edit(this);
    }
}
