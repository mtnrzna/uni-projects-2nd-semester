package sample;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductFile implements File<Product>{
    public ProductFile() {
        productArrayList = new ArrayList<Product>();
    }

    private java.io.File filepath;

    private ArrayList<Product> productArrayList ;
    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }
    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }


    // this array will get filled during readfile method, it fills with company names
    private ArrayList<String> companiesNameReadFromFile;


    //this method will read all datas from file
    public void readFile(){
        productArrayList = new ArrayList<Product>();
        companiesNameReadFromFile = new ArrayList<String>();
        try {
            filepath = new java.io.File("products data.txt");
            Scanner scanner = new Scanner(filepath);
            System.out.println(scanner.hasNextLine());
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();

                String arrayOfData[] = new String[5];

                int i = 0;
                while (!s.equals("")) {
                    String temp = s.substring(0, s.indexOf("$") +1);
                    arrayOfData[i] = temp.replace("$", "");
                    s = s.replace(temp, "");
                    System.out.println(arrayOfData[i]);
                    i++;
                }
                createProduct(arrayOfData[0], arrayOfData[1],arrayOfData[2],
                        LocalDate.parse(arrayOfData[3]), LocalDate.parse(arrayOfData[4]));
            }
            System.out.println("number of all products imported: "+productArrayList.size());
            System.out.println("number of all company names read from product file imported: "+
                    companiesNameReadFromFile.size());
        }
        catch (FileNotFoundException e){
            System.out.println("the file for products' data is missing!");
        }

    }


    //this method will make product with read file and it gets called inside the readfile method
    //it maked a product, but product's company will still be null after this method
    public void createProduct(String name, String code, String companyName, LocalDate expireDate, LocalDate produceDate) {
        Product product = new Product(name, code, expireDate, produceDate);
        companiesNameReadFromFile.add(companyName);
        productArrayList.add(product);
    }






    //this method will update the products information, it add company to them
    //first the company file should be updated then we can add company to this products
    public void updateInfo(){
        for(Product product: productArrayList){
            int indexOFThisProduct = productArrayList.indexOf(product);
            for(Company company: Main.getApp().getCompanyFile().getCompanyArrayList()) {
                if (companiesNameReadFromFile.get(indexOFThisProduct).equals(company.getName())) {
                    product.setCompany(company);
                    System.out.println("company of this product found and added");
                }
            }
        }
    }



    public void save(Product product){
        Main.getApp().readAllFiles();
        productArrayList.add(product);
        updateFile();
    };


    //this file will update the products data text file, by writing to it, and it doesn't append, it cleans the text file
    //then it will write the products array list to it
    public void updateFile(){
        try {
            filepath = new java.io.File("products data.txt");
            FileWriter fileWriter = new FileWriter(filepath, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Product product : productArrayList) {
                printWriter.println(product.getName() + "$" + product.getCode() + "$"
                        + product.getCompany().getName()+"$"+product.getExpireDate()
                        +"$"+ product.getProduceDate()+"$");
            }
            printWriter.close();
            System.out.println("products file updated");
        }
        catch (IOException e){
            System.out.println("products' file is missing!");
        }
    }

    public void remove(Product product){
        productArrayList.remove(product);
    };
//    public void showDetails(Product product){};
    public void edit(Product product){
        updateFile();
    };

}
