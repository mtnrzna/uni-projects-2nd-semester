package sample;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerFile implements File<Manager>{
    public ManagerFile() {
        managerArrayList = new ArrayList<Manager>();
    }

    private java.io.File filepath;

    private ArrayList<Manager> managerArrayList;
    public ArrayList<Manager> getManagerArrayList() {
        return managerArrayList;
    }
    public void setManagerArrayList(ArrayList<Manager> managerArrayList) {
        this.managerArrayList = managerArrayList;
    }


    // this array will get filled during readfile method, it fills with company names
    private ArrayList<String> companiesNameReadFromFile;



    //this method will read all datas from file
    public void readFile(){
        managerArrayList = new ArrayList<Manager>();
        companiesNameReadFromFile = new ArrayList<String>();
        try {
            filepath = new java.io.File("managers data.txt");
            Scanner scanner = new Scanner(filepath);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();

                String arrayOfData[] = new String[3];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
                    s = s.replace(temp, "");
                    System.out.println(arrayOfData[i]);
                    i++;
                }
                createManager(arrayOfData[0], arrayOfData[1], arrayOfData[2]);
            }
            System.out.println("number of all managers imported: "+managerArrayList.size());
            System.out.println("number of all company names read from manager file imported: "+
                    companiesNameReadFromFile.size());
        }
        catch (FileNotFoundException e){
            System.out.println("the file for managers' data is missing!");
        }

    }


    //this method will make manager with read file and it gets called inside the readfile method
    //it maked a manager, but manager's company will still be null after this method
    public void createManager(String firstName, String lastName,String companyName) {
        Manager manager = new Manager(firstName, lastName);
        companiesNameReadFromFile.add(companyName);
        managerArrayList.add(manager);
    }



    //this method will update the managers information, it add company to them
    //first the company file should be updated then we can add company to this managers
    public void updateInfo(){
        for(Manager manager: managerArrayList){
            int indexOFThisManager = managerArrayList.indexOf(manager);
            for(Company company: Main.getApp().getCompanyFile().getCompanyArrayList()) {
                if (companiesNameReadFromFile.get(indexOFThisManager).equals(company.getName())) {
                    manager.setCompany(company);
                    System.out.println("company of this manager found and added");
                }
            }
        }
    }


    public void save(Manager manager){
        managerArrayList.add(manager);
        companiesNameReadFromFile.add(manager.getCompany().getName());
        updateFile();

    }


    //this file will update the managers data text file, by writing to it, and it doesn't append, it cleans the text file
    //then it will write the managers array list to it
    public void updateFile(){
        try {
            filepath = new java.io.File("managers data.txt");
            FileWriter fileWriter = new FileWriter(filepath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Manager manager : managerArrayList) {
                printWriter.println(manager.getFirstName() + "$" + manager.getLastName() +
                        "$"+manager.getCompany().getName() + "$");
            }
            printWriter.close();
            System.out.println("managers file updated");
        }
        catch (IOException e){
            System.out.println("managers' file is missing!");
        }
    }


    //this method will remove the manager parameter forom the manager arraylist
    public void remove(Manager manager){
        managerArrayList.remove(manager);
    };
    public void showDetails(Manager manager){};
    public void edit(Manager manager){
        updateFile();
    };

}
