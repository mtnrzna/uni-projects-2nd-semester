package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WarehouseManagerWindow implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Main.getApp().readAllFiles();
        showBlankPane();
    }




    @FXML private Pane blankPane, companiesPane, addCompanyPane,productsPane, addProductPane,searchPane,
            productsOfOneComPane, companyDetailPane, oneProductsDetailPane;



    public void showBlankPane(){
        blankPane.toFront();
    }


    /*****************************************Company Pane*******************************************/

    public void showCompaniesPane(){
        companiesPane.toFront();
        companiesShouldBeHere.getChildren().clear();
        for (Company company: Main.getApp().getCompanyFile().getCompanyArrayList()){
            showCompany(company);
        }
    }

    @FXML Pane companiesShouldBeHere;
    public void showCompany(Company company){
        HBox hBox = new HBox();
        hBox.setPrefWidth(463);
        hBox.setPrefHeight(108);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setStyle("-fx-border-color: #006600;");
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(40);

        Label companyNameLabel = new Label(company.getName());
        companyNameLabel.setStyle("-fx-background-color: #E5FFCC;");


        VBox vBox = new VBox();
        vBox.setPrefWidth(235);
        vBox.setPrefHeight(108);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Button companyDetailButton = new Button("Company's Details");
        companyDetailButton.setOnAction(actionEvent -> showCompanyDetailPane(company));
        companyDetailButton.setPrefWidth(150);
        companyDetailButton.setStyle("-fx-border-color: #006600;");

        Button seeProductsButton = new Button("See Products");
        seeProductsButton.setOnAction(actionEvent -> showProductsOfOneComPane(company));
        seeProductsButton.setPrefWidth(150);
        seeProductsButton.setStyle("-fx-border-color: #006600;");


        Button deleteCompanyButton = new Button("Delete");
        deleteCompanyButton.setStyle("-fx-border-color:  linear-gradient(#A80000,#B95050);");
        deleteCompanyButton.setOnAction(actionEvent -> deleteCompany(company));

        vBox.getChildren().addAll(companyDetailButton, seeProductsButton);
        hBox.getChildren().addAll(companyNameLabel, vBox, deleteCompanyButton);
        companiesShouldBeHere.getChildren().add(hBox);
    }


    /*****************************************Add Company Pane*******************************************/

    @FXML TextField companyNameTxtFld, managerFirstnameTxtFld,managerLastnameTxtFld, addressTxtFld,numberTxtFld;
    @FXML Pane employeesShouldBeHere;

//    @FXML TextField employeeFirstnameTxtFldDefault, employeeLastnameTxtFldDefault, employeeJobTxtFldDefault;

    int numberOfEmployees;
    public void showAddCompanyPane(){
        addCompanyPane.toFront();

        companyNameTxtFld.setText("");
        managerFirstnameTxtFld.setText("");
        managerLastnameTxtFld.setText("");
        addressTxtFld.setText("");
        numberTxtFld.setText("");
        employeesShouldBeHere.getChildren().clear();
        addAnotherEmployee();

    }

    public void addAnotherEmployee(){
        HBox hBox = new HBox();
        hBox.setPrefWidth(200);
        hBox.setPrefHeight(100);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setStyle("-fx-border-color: #006600;");
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);

        VBox vBox = new VBox();
        vBox.setPrefWidth(247);
        vBox.setPrefHeight(100);
        vBox.setAlignment(Pos.CENTER);


        TextField employeeFirstnameTxtFld = new TextField();
        employeeFirstnameTxtFld.setPromptText("employee's firstname");

        TextField employeeLastnameTxtFld = new TextField();
        employeeLastnameTxtFld.setPromptText("employee's lastname");

        TextField employeeJobTxtFld = new TextField();
        employeeJobTxtFld.setPromptText("employee's job");

        Button addAnotherEmployeeButton = new Button("add another employee");
        addAnotherEmployeeButton.setStyle("-fx-border-color: #006600;");
        addAnotherEmployeeButton.setOnAction(actionEvent -> addAnotherEmployee());

        vBox.getChildren().addAll(employeeFirstnameTxtFld, employeeLastnameTxtFld, employeeJobTxtFld);
        hBox.getChildren().addAll(vBox, addAnotherEmployeeButton);
        employeesShouldBeHere.getChildren().add(hBox);

//
//        System.out.println("////////////////////////////////////////");
//        boolean employeeDataFilled = false;
//        while (employeeFirstnameTxtFld.getText().trim().equals("") && employeeLastnameTxtFld.getText().trim()
//                .equals("") && employeeJobTxtFld.getText().trim().equals("") && employeeDataFilled == false) {
//            if (!employeeFirstnameTxtFld.getText().trim().equals("") && !employeeLastnameTxtFld.getText().trim()
//                    .equals("") && !employeeJobTxtFld.getText().trim().equals("")) {
//                employeesFirstnames.add(employeeFirstnameTxtFld.getText());
//                employeesLastnames.add(employeeLastnameTxtFld.getText());
//                employeesJobs.add(employeeJobTxtFld.getText());
//                System.out.println("*************************************");
//                employeeDataFilled = true;
//            }
//        }

        numberOfEmployees++;
    }


    public void addNewCompany() throws IOException {
        if(!companyNameTxtFld.getText().equals("") && !managerFirstnameTxtFld.getText().equals("")
            && !managerLastnameTxtFld.getText().equals("") && !addressTxtFld.getText().equals("")
            && !numberTxtFld.getText().equals("") && Main.getApp().isNumeric(numberTxtFld.getText())) {
            Company company = new Company(companyNameTxtFld.getText(), addressTxtFld.getText(),
                    numberTxtFld.getText());
            Manager manager = new Manager(managerFirstnameTxtFld.getText(), managerLastnameTxtFld.
                    getText());

//
//            //check if a company with this name was signed up bedore
//            for(Company company1: Main.getApp().getCompanyFile().getCompanyArrayList()){
//                if(company.getName().trim().equals(company1.getName().trim()))
//                    foundDuplicate = true;
//            }

            manager.setCompany(company);
            company.setManager(manager);
            company.save();
            manager.save();

            if(numberOfEmployees !=0) {
                for (int i = 0; i < numberOfEmployees; i++) {
                    HBox hBox = (HBox) employeesShouldBeHere.getChildren().get(i);
                    VBox vBox = (VBox) hBox.getChildren().get(0);
                    TextField employeeFirstnameTxtFld = (TextField) vBox.getChildren().get(0);
                    TextField employeeLastnameTxtFld = (TextField) vBox.getChildren().get(1);
                    TextField employeeJobTxtFld = (TextField) vBox.getChildren().get(2);

                    if (!employeeFirstnameTxtFld.getText().trim().equals("") && !employeeLastnameTxtFld.getText().trim()
                            .equals("") && !employeeJobTxtFld.getText().trim().equals("")) {
                        Employee employee = new Employee(employeeFirstnameTxtFld.getText(), employeeLastnameTxtFld
                                .getText(), employeeJobTxtFld.getText());
                        employee.setCompany(company);
                        employee.save();
                        System.out.println("employee added");
                    }
                    Main.getApp().getEmployeeFile().updateFile();
                }
            }

            Main.getApp().showPopupWithOk("Company Added");
            companyNameTxtFld.setText("");
            managerFirstnameTxtFld.setText("");
            managerLastnameTxtFld.setText("");
            addressTxtFld.setText("");
            numberTxtFld.setText("");
            employeesShouldBeHere.getChildren().clear();
            numberOfEmployees = 0;
            showAddCompanyPane();

            System.out.println("company and manager added");
        }

        else if (!Main.getApp().isNumeric(numberTxtFld.getText())){
            Main.getApp().showPopupWithOk("number you entered is not in correct format");
        }
        else
            Main.getApp().showPopupWithOk("Make sure you filled all the needed information");
    }
/*****************************************Product Pane*******************************************/
    public void showProductsPane(){
        productsPane.toFront();
        productsShouldBeHere.getChildren().clear();
        for (Product product: Main.getApp().getProductFile().getProductArrayList()){
            showAllProduct(product);
        }
    }

    @FXML Pane productsShouldBeHere;
    public void showAllProduct(Product product){
        HBox hBox = new HBox();
        hBox.setPrefWidth(463);
        hBox.setPrefHeight(108);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setStyle("-fx-border-color: #006600;");
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(40);

        Label productNameLabel = new Label(product.getName());
        productNameLabel.setStyle("-fx-background-color: #E5FFCC;");


        VBox vBox = new VBox();
        vBox.setPrefWidth(235);
        vBox.setPrefHeight(108);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Button companyNameLabel = new Button(product.getCompany().getName());
        companyNameLabel.setOnAction(actionEvent -> showCompanyDetailPane(product.getCompany()));
        companyNameLabel.setPrefWidth(150);
        companyNameLabel.setStyle("-fx-border-color: #006600;");

        Button seeProductDetailsButton = new Button("Product's Details");
        seeProductDetailsButton.setOnAction(actionEvent -> showOneProductDetailPane(product));
        seeProductDetailsButton.setPrefWidth(150);
        seeProductDetailsButton.setStyle("-fx-border-color: #006600;");


        Button deleteProductButton = new Button("Delete");
        deleteProductButton.setStyle("-fx-border-color:  linear-gradient(#A80000,#B95050);");
        deleteProductButton.setOnAction(actionEvent -> deleteProduct(product));




        vBox.getChildren().addAll(companyNameLabel, seeProductDetailsButton);
        hBox.getChildren().addAll(productNameLabel, vBox , deleteProductButton);
        productsShouldBeHere.getChildren().add(hBox);
    }



    /*****************************************Add Product Pane*******************************************/

    @FXML TextField productName, productCode;
    @FXML MenuButton selectCompany;
    @FXML DatePicker selectExpireDate, selectProduceDate;
    public void showAddProductPane(){
        addProductPane.toFront();
        productName.setText("");
        productCode.setText("");
        selectCompany.setText("Company");
        selectExpireDate.setChronology(null);
        selectProduceDate.setChronology(null);
//        selectCompany.getItems().clear();
        Main.getApp().readAllFiles();
        ArrayList<Company> companies= Main.getApp().getCompanyFile().getCompanyArrayList();
        for(Company company: companies){
            MenuItem menuItem = new MenuItem(company.getName());
            menuItem.setOnAction(actionEvent -> selectCompany.setText(company.getName()));
            selectCompany.getItems().add(menuItem);
        }

    }

    public void addNewProduct() throws IOException {
        if(selectExpireDate.getValue() == null)
            Main.getApp().showPopupWithOk("Please Pick an expire date");
        else if(selectProduceDate.getValue() == null)
            Main.getApp().showPopupWithOk("Please Pick an produce date");
        else if(selectExpireDate.getValue().isAfter(selectProduceDate.getValue()))
            Main.getApp().showPopupWithOk("Expire date is bigger than produce date!!!");
        else if(!productName.getText().trim().equals("") && !selectCompany.getText().equals("Company")&&
                !productCode.getText().trim().equals("")&&
                selectExpireDate.getValue() != null && selectProduceDate.getValue() != null){
            Product product = new Product(productName.getText(), productCode.getText(),
                    selectExpireDate.getValue(), selectProduceDate.getValue());
            ArrayList<Company> allCompanies= Main.getApp().getCompanyFile().getCompanyArrayList();
            for (Company company: allCompanies){
                System.out.println("**************");
                if(company.getName().equals(selectCompany.getText())){
                    product.setCompany(company);
                    System.out.println("company added to the new product");
                    product.save();
                }
            }
        }
        else if (selectCompany.getText().equals("Company"))
            Main.getApp().showPopupWithOk("Select a company");
        else
            Main.getApp().showPopupWithOk("Fill all needed information");
    }


/********************************Search Pane *******************************/

    @FXML Pane searchBySelectPane;
    public void showSearchPane(){
        searchMenuButton.setText("Company");
        searchMenuButton.getItems().clear();
        searchedProducts.getChildren().clear();
        searchByTypePane.getChildren().clear();
        nameSearchTxtFld.setText("");
        searchPane.toFront();
        Main.getApp().readAllFiles();
        ArrayList<Company> companies= Main.getApp().getCompanyFile().getCompanyArrayList();
        for(Company company: companies){
            MenuItem menuItem = new MenuItem(company.getName());
            menuItem.setOnAction(actionEvent -> searchMenuButton.setText(company.getName()));
            searchMenuButton.getItems().add(menuItem);
        }

    }

    @FXML MenuButton searchMenuButton;
    @FXML Pane searchedProducts;
    public void searchBySelect() throws IOException {
        if(!searchMenuButton.getText().equals("Company")) {
            searchedProducts.getChildren().clear();
            Company company = null;
            for (Company tempCompany : Main.getApp().getCompanyFile().getCompanyArrayList()) {
                if (searchMenuButton.getText().equals(tempCompany.getName()))
                    company = tempCompany;
            }
            ArrayList<Product> allPoducts = Main.getApp().getProductFile().getProductArrayList();
            ArrayList<Product> productsOfThisCompany = new ArrayList<Product>();
            for (Product product : allPoducts) {
                if (product.getCompany().getName().equals(company.getName()))
                    productsOfThisCompany.add(product);
            }
            if (productsOfThisCompany.size() != 0) {
                for (Product product : productsOfThisCompany) {
                    showSearchedProducts(product, searchedProducts);
                }
            }
            else {
                nothingFound(searchedProducts);
            }
        }
        else
            Main.getApp().showPopupWithOk("Select a Company");
    }

    public void showSearchedProducts(Product product , Pane pane){
        HBox hBox = new HBox();
        hBox.setPrefHeight(51);
        hBox.setPrefWidth(205);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-border-color:  #006600");

        Button productButton = new Button(product.getName());
        productButton.setPrefWidth(150);
        productButton.setPrefHeight(37);
        productButton.setStyle("-fx-border-color:  #006600");
        productButton.setStyle("-fx-background-color:  #E5FFCC");

        hBox.getChildren().add(productButton);
        pane.getChildren().add(hBox);



    }



    @FXML TextField nameSearchTxtFld;
    @FXML Pane searchByTypePane;
    public void searchByType() throws IOException {
        if(!nameSearchTxtFld.getText().trim().equals("")) {
            searchByTypePane.getChildren().clear();
            String input = nameSearchTxtFld.getText();
            ArrayList<Product> allPoducts = Main.getApp().getProductFile().getProductArrayList();
            ArrayList<Product> machedProducts = new ArrayList<Product>();
            for (Product product  : allPoducts) {
                for(int i=0; i<product.getName().length(); i++){
                    for(int j= i+1; j<product.getName().length()+1;j++){
                        if(input.trim().toLowerCase().equals(product.getName().substring(i, j).toLowerCase())){
                            machedProducts.add(product);
                        }
                    }
                }
            }

            if (machedProducts.size() != 0) {
                for (Product product : machedProducts) {
                    showSearchedProducts(product, searchByTypePane);
                }
            }
            else {
                nothingFound(searchByTypePane);
            }
        }
        else
            Main.getApp().showPopupWithOk("Type something first!!");
    }


    public void nothingFound( Pane pane){
        HBox hBox = new HBox();
        hBox.setPrefHeight(51);
        hBox.setPrefWidth(205);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-border-color:  #006600");

        Button productButton = new Button("Nothing Found!");
        productButton.setPrefWidth(150);
        productButton.setPrefHeight(37);
        productButton.setStyle("-fx-border-color:  #006600");
        productButton.setStyle("-fx-background-color:  #989898");

        hBox.getChildren().add(productButton);
        pane.getChildren().add(hBox);



    }



    /*************************Detail Panes*********************************/
    @FXML Label companyNameLabel, managerNameLabel,addressLabel,phoneNumberLabel;
    public void showCompanyDetailPane(Company company){
        companyDetailPane.toFront();
        employeesOfThisComShouldBeHere.getChildren().clear();
        companyNameLabel.setText(company.getName());
        managerNameLabel.setText("Manager: "+company.getManager().getFirstName()+" "+
                company.getManager().getLastName());
        addressLabel.setText("Address: "+company.getAddress());
        phoneNumberLabel.setText("Number: "+company.getPhoneNumber());
        ArrayList<Employee> employeesOfThisCompany = new ArrayList<Employee>();
        ArrayList<Employee> allEmployees = Main.getApp().getEmployeeFile().getEmployeesArrayList();
        want2EditThisCompany = company;
        for(Employee employee: allEmployees){
            if(employee.getCompany() == company)
                employeesOfThisCompany.add(employee);
        }

        for(Employee employee: employeesOfThisCompany){
            showEmployees(employee);
        }
    }
    @FXML Pane employeesOfThisComShouldBeHere;
    public void showEmployees(Employee employee){
        HBox hBox = new HBox();
        hBox.setPrefWidth(463);
        hBox.setPrefHeight(108);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setStyle("-fx-border-color: #006600;");
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setSpacing(40);



        VBox vBox = new VBox();
        vBox.setPrefWidth(235);
        vBox.setPrefHeight(108);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Label employeesFirstName = new Label("Firstname: "+employee.getFirstName());
        employeesFirstName.setPrefWidth(150);
        employeesFirstName.setStyle("-fx-border-color: #006600;");

        Label employeesLastName = new Label("Lastname: "+employee.getLastName());
        employeesLastName.setPrefWidth(150);
        employeesLastName.setStyle("-fx-border-color: #006600;");


        Label employeesJob = new Label(employee.getJob());
        employeesJob.setStyle("-fx-background-color: #E5FFCC;");


        Button deleteEmployeeButton = new Button("Delete");
        deleteEmployeeButton.setStyle("-fx-border-color:  linear-gradient(#A80000,#B95050);");
        deleteEmployeeButton.setOnAction(actionEvent -> deleteEmployee(employee));

        vBox.getChildren().addAll(employeesFirstName, employeesLastName);
        hBox.getChildren().addAll(vBox, employeesJob , deleteEmployeeButton);
        employeesOfThisComShouldBeHere.getChildren().add(hBox);
    }




    @FXML Label productsOfCompanyLabel;
    public void showProductsOfOneComPane(Company company){
        productsOfOneComPane.toFront();
        productsOfThisComShouldBeHere.getChildren().clear();
        productsOfCompanyLabel.setText("Products of "+company.getName());
        ArrayList<Product> productsOfThisCompany = new ArrayList<Product>();
        ArrayList<Product> allProducts = Main.getApp().getProductFile().getProductArrayList();
        for(Product product: allProducts){
            if(product.getCompany() == company)
                productsOfThisCompany.add(product);
        }

        for(Product product: productsOfThisCompany){
            showProducts(product);
        }
    }

    @FXML Pane productsOfThisComShouldBeHere;
    public void showProducts(Product product){
        HBox hBox = new HBox();
        hBox.setPrefWidth(463);
        hBox.setPrefHeight(108);
        hBox.setMinWidth(Region.USE_PREF_SIZE);
        hBox.setMinHeight(Region.USE_PREF_SIZE);
        hBox.setStyle("-fx-border-color: #006600;");
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setSpacing(40);

        Label productNameLabel = new Label(product.getName());
        productNameLabel.setStyle("-fx-background-color: #E5FFCC;");


        Button seeProductDetailsButton = new Button("Product's Details");
        seeProductDetailsButton.setOnAction(actionEvent -> showOneProductDetailPane(product));
        seeProductDetailsButton.setPrefWidth(150);
        seeProductDetailsButton.setStyle("-fx-border-color: #006600;");


        Button deleteProductButton = new Button("Delete");
        deleteProductButton.setStyle("-fx-border-color:  linear-gradient(#A80000,#B95050);");
        deleteProductButton.setOnAction(actionEvent -> deleteProduct(product));



        hBox.getChildren().addAll(productNameLabel,seeProductDetailsButton, deleteProductButton);
        productsOfThisComShouldBeHere.getChildren().add(hBox);
    }




    @FXML Label productNameLabel, productCodeLabel, expireDateLabel, produceDateLabel;
    public void showOneProductDetailPane(Product product){
        oneProductsDetailPane.toFront();
        productNameLabel.setText(product.getName());
        productCodeLabel.setText("Code: "+product.getCode());
        expireDateLabel.setText("Expire Date: "+product.getExpireDate().toString());
        produceDateLabel.setText("Produce Date: "+product.getProduceDate().toString());
        want2EditThisProduct = product;
    }

/************************ Edit panes**********************************/

    @FXML TextField editProductName, editProductCode;
    @FXML MenuButton companyOfProductMEnuButton;
    @FXML DatePicker editExpireDate, editProduceDate;
    @FXML Pane editProductDetailPane;
    Product want2EditThisProduct;
    public void showProductEditPane(){
        editProductDetailPane.toFront();
        editProductName.setText(want2EditThisProduct.getName());
        editProductCode.setText(want2EditThisProduct.getCode());
        companyOfProductMEnuButton.getItems().clear();
        ArrayList<Company> companies= Main.getApp().getCompanyFile().getCompanyArrayList();
        for(Company company: companies){
            MenuItem menuItem = new MenuItem(company.getName());
            menuItem.setOnAction(actionEvent -> companyOfProductMEnuButton.setText(company.getName()));
            companyOfProductMEnuButton.getItems().add(menuItem);
        }
    }

    public void saveProductEdit() throws IOException {
        if(editExpireDate.getValue() == null)
            Main.getApp().showPopupWithOk("Please Pick an expire date");
        else if(editProduceDate.getValue() == null)
            Main.getApp().showPopupWithOk("Please Pick an produce date");
        else if(editExpireDate.getValue().isAfter(editProduceDate.getValue()))
            Main.getApp().showPopupWithOk("Expire date is bigger than produce date!!!");
        else if(!editProductName.getText().trim().equals("") && !companyOfProductMEnuButton
                .getText().equals("Company")&&
                !editProductCode.getText().trim().equals("")&&
                editExpireDate.getValue() != null && editProduceDate.getValue() != null){
            want2EditThisProduct.setName(editProductName.getText());
            want2EditThisProduct.setCode(editProductCode.getText());
            want2EditThisProduct.setExpireDate(editExpireDate.getValue());
            want2EditThisProduct.setProduceDate(editProduceDate.getValue());
            ArrayList<Company> allCompanies= Main.getApp().getCompanyFile().getCompanyArrayList();
            for (Company company: allCompanies){
                if(company.getName().equals(companyOfProductMEnuButton.getText())){
                    want2EditThisProduct.setCompany(company);
                    want2EditThisProduct.edit();
                    want2EditThisProduct = null;
                    System.out.println("Changes have been saved");
                    Main.getApp().showPopupWithOk("Changes have been saved...");
                    showProductsPane();

                }
            }
        }
        else if (companyOfProductMEnuButton.getText().equals("Company"))
            Main.getApp().showPopupWithOk("Select a company");
        else
            Main.getApp().showPopupWithOk("Fill all needed information");
    }


    @FXML TextField editCompanyName, editCompanyManagerFirstname,editCompanyManagerLastname,editCompanyAddress,editCompanyNumber;
    @FXML Pane editCompanyDetailPane;
    Company want2EditThisCompany;
    public void showCompanyEditPane(){
        editCompanyDetailPane.toFront();
        editCompanyName.setText(want2EditThisCompany.getName());
        editCompanyManagerFirstname.setText(want2EditThisCompany.getManager().getFirstName());
        editCompanyManagerLastname.setText(want2EditThisCompany.getManager().getLastName());
        editCompanyAddress.setText(want2EditThisCompany.getAddress());
        editCompanyNumber.setText(want2EditThisCompany.getPhoneNumber());
    }

    public void saveCompanyEdit() throws IOException {
        if(!editCompanyNumber.getText().equals("") && !Main.getApp().isNumeric(editCompanyNumber.getText())){
            Main.getApp().showPopupWithOk("Please enter a number for company's number!");
        }

        else if(!editCompanyName.getText().trim().equals("") && !editCompanyManagerFirstname.getText().trim().equals("")
                &&!editCompanyManagerLastname.getText().trim().equals("")
                && !editCompanyAddress.getText().trim().equals("") && !editCompanyNumber.getText().trim().equals("")){

            want2EditThisCompany.setName(editCompanyName.getText());

            want2EditThisCompany.setAddress(editCompanyAddress.getText());
            want2EditThisCompany.setPhoneNumber(editCompanyNumber.getText());

            Manager manager = want2EditThisCompany.getManager();
            manager.setFirstName(editCompanyManagerFirstname.getText().trim());
            manager.setLastName(editCompanyManagerLastname.getText().trim());

            want2EditThisCompany.edit();
            want2EditThisCompany = null;
            System.out.println("Changes have been saved");
            Main.getApp().showPopupWithOk("Changes have been saved...");
            showCompaniesPane();
        }
        else
            Main.getApp().showPopupWithOk("Fill all needed information");
    }
/*********************Delete Methods*************************************/
    public void deleteCompany(Company company){
        company.remove();
        showCompaniesPane();
    }

    public void deleteProduct(Product product){
        product.remove();
        Main.getApp().getProductFile().updateFile();
    }

    public void deleteEmployee(Employee employee){

    }
}
