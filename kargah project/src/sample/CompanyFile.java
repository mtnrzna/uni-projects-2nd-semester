package sample;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CompanyFile implements File<Company>{
    public CompanyFile() {
        companyArrayList = new ArrayList<Company>();
    }

    private java.io.File filepath;

    private ArrayList<Company> companyArrayList ;
    public ArrayList<Company> getCompanyArrayList() {
        return companyArrayList;
    }
    public void setCompanyArrayList(ArrayList<Company> companyArrayList) {
        this.companyArrayList = companyArrayList;
    }


    // this array will get filled during readfile method, it fills with manager names
    private ArrayList<String> managersNameReadFromFile;


    //this method will read all datas from file
    public void readFile(){
        companyArrayList = new ArrayList<Company>();
        managersNameReadFromFile = new ArrayList<String>();
        try {
            filepath = new java.io.File("companies data.txt");
            Scanner scanner = new Scanner(filepath);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();

                String arrayOfData[] = new String[4];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
                    s = s.replace(temp, "");
                    System.out.println(arrayOfData[i]);
                    i++;
                }
//                if(Main.getApp().isNumeric(arrayOfData[3])) {
                    createCompany(arrayOfData[0], arrayOfData[1],arrayOfData[2],arrayOfData[3]);
//                }
//                else
//                    System.out.println("the phone number saved in file is not integer!");
            }
            System.out.println("number of all companies imported: "+companyArrayList.size());
            System.out.println("number of all manager names read from company file imported: "+
                    managersNameReadFromFile.size());
        }
        catch (FileNotFoundException e){
            System.out.println("the file for companies' data is missing!");
        }

    }


    //this method will make company with read file and it gets called inside the readfile method
    //it maked a company, but company's manager will still be null after this method
    public void createCompany(String name, String adress,String managerName, String phoneNumber) {
            Company company = new Company(name, adress, phoneNumber);
        managersNameReadFromFile.add(managerName);
        companyArrayList.add(company);
    }



    //this method will update the companies information, it add managers to them
    //first the managers file should be updated then we can add managers to this companies
    public void updateInfo(){
        for(Company company: companyArrayList){
            int indexOFThisCompany = companyArrayList.indexOf(company);
            for(Manager manager: Main.getApp().getManagerFile().getManagerArrayList()) {
                if (managersNameReadFromFile.get(indexOFThisCompany).equals(manager.getFirstName()+" "+
                        manager.getLastName())) {
                    company.setManager(manager);
                    System.out.println("manager of this company found and added");
                }
            }
        }
    }



    public void save(Company company){
        companyArrayList.add(company);
        managersNameReadFromFile.add(company.getManager().getFirstName()+" "
                + company.getManager().getLastName());
        updateFile();
    };



    //this file will update the companies data text file, by writing to it, and it doesn't append, it cleans the text file
    //then it will write the company array list to it
    public void updateFile(){
        try {
            filepath = new java.io.File("companies data.txt");
            FileWriter fileWriter = new FileWriter(filepath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Company company : companyArrayList) {
                printWriter.println(company.getName() + "$" + company.getAddress() + "$"
                        + company.getManager().getFirstName()+" "+company.getManager().getLastName()
                        +"$"+ company.getPhoneNumber()+"$");
            }
            printWriter.close();
            System.out.println("companies file updated");
        }
        catch (IOException e){
            System.out.println("companies' file is missing!");
        }
    }




    //this method will find all products and employees and the manager, associated with the company parameter,
    // and then it will remove them from their file's array list, and then it will remove  the company itself-
    //from the company array list, and at last it will update their text files
    public void remove(Company company){
//        will delete company, it's manager and all it's employees
        ArrayList<Product> productsOfThisCompany = new ArrayList<Product>();
        for(Product product: Main.getApp().getProductFile().getProductArrayList()){
            if(product.getCompany() == company) {
                productsOfThisCompany.add(product);
            }
        }
        for(Product product: productsOfThisCompany){
            product.remove();
        }


        ArrayList<Employee> employeesOfThisCompany = new ArrayList<Employee>();
        for(Employee employee: Main.getApp().getEmployeeFile().getEmployeesArrayList()){
            if(employee.getCompany() == company) {
                employeesOfThisCompany.add(employee);
            }
        }
        for(Employee employee: employeesOfThisCompany){
            employee.remove();
        }


        company.getManager().remove();
        companyArrayList.remove(company);
        Main.getApp().getProductFile().updateFile();
        Main.getApp().getManagerFile().updateFile();
        Main.getApp().getEmployeeFile().updateFile();
        updateFile();





        Main.getApp().readAllFiles();
        //because readFile() method ggets called many times during runtime, the original "company"
        // object is different form "company object here, but their info is the same
        for(Company company1 : companyArrayList){
            if(company.getName().equals(company1.getName())) {
                companyArrayList.remove(company1);
                break;
            }

        }
        updateFile();
        System.out.println(company.getName()+" deleted");
    };



    //when following method is called, it will update the text file and it applys all the changes
    // in the company array list, e.x. if company's name changes, this method will change
    //company text file, its manager's file, it's employees' and products' files
    public void edit(Company company){
        ArrayList<Product> productsOfThisCompany = new ArrayList<Product>();
        for(Product product: Main.getApp().getProductFile().getProductArrayList()){
            if(product.getCompany() == company) {
                productsOfThisCompany.add(product);
            }
        }
        for(Product product: productsOfThisCompany){
            product.edit();
        }


        ArrayList<Employee> employeesOfThisCompany = new ArrayList<Employee>();
        for(Employee employee: Main.getApp().getEmployeeFile().getEmployeesArrayList()){
            if(employee.getCompany() == company) {
                employeesOfThisCompany.add(employee);
            }
        }
        for(Employee employee: employeesOfThisCompany){
            employee.edit();
        }
        company.getManager().edit();

        updateFile();
    };

}
