package sample;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeFile implements File<Employee>{
    public EmployeeFile() {
        employeesArrayList = new ArrayList<Employee>();
    }

    private java.io.File filepath;

    public ArrayList<Employee> getEmployeesArrayList() {
        return employeesArrayList;
    }
    public void setEmployeesArrayList(ArrayList<Employee> employeesArrayList) {
        this.employeesArrayList = employeesArrayList;
    }

    private ArrayList<Employee> employeesArrayList;


    // this array will get filled during readfile method, it fills with company names
    private ArrayList<String> companiesNameReadFromFile;



    //this method will read all datas from file
    public void readFile(){
        employeesArrayList = new ArrayList<Employee>();
        companiesNameReadFromFile = new ArrayList<String>();

        try {
            filepath = new java.io.File("employees data.txt");
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
                createEmployee(arrayOfData[0], arrayOfData[1], arrayOfData[2],arrayOfData[3]);
            }
            System.out.println("number of employees imported: "+employeesArrayList.size());
            System.out.println("number of all company names read from employee file imported: "+
                    companiesNameReadFromFile.size());
        }
        catch (FileNotFoundException e){
            System.out.println("the file for employees' data is missing!");
        }

    }


    //this method will make employee with read file and it gets called inside the readfile method
    //it maked a employee, but employee's company will still be null after this method
    public void createEmployee(String firstName, String lastName,String companyName, String job) {
        Employee employee = new Employee(firstName, lastName,job );
        companiesNameReadFromFile.add(companyName);
        employeesArrayList.add(employee);

    }





    //this method will update the employees information, it add company to them
    //first the company file should be updated then we can add company to this employees
    public void updateInfo(){
        for(Employee employee: employeesArrayList){
            int indexOFThisEmployee = employeesArrayList.indexOf(employee);
            for(Company company: Main.getApp().getCompanyFile().getCompanyArrayList()) {
                if (companiesNameReadFromFile.get(indexOFThisEmployee).equals(company.getName())) {
                    employee.setCompany(company);
                    System.out.println("company of this employee found and added");
                }
            }
        }
    }


    public void save(Employee employee){
        Main.getApp().readAllFiles();
        employeesArrayList.add(employee);
        updateFile();
    }



    //this file will update the employees data text file, by writing to it, and it doesn't append, it cleans the text file
    //then it will write the employees array list to it
    public void updateFile(){
        try {
            filepath = new java.io.File("employees data.txt");
            FileWriter fileWriter = new FileWriter(filepath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Employee employee : employeesArrayList) {
                printWriter.println(employee.getFirstName() + "$" + employee.getLastName() + "$"
                        + employee.getCompany().getName() + "$"+employee.getJob()+"$");
            }
            printWriter.close();
            System.out.println("employees file updated");
        }
        catch (IOException e){
            System.out.println("employees' file is missing!");
        }
    }

    public void remove(Employee employee){
        employeesArrayList.remove(employee);
    }
    public void showDetails(Employee employee){};
    public void edit(Employee employee){
        updateFile();
    };

}
