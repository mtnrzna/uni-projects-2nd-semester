package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App {
    public App(Stage primaryStage) throws IOException {
        employeeFile= new EmployeeFile();
        managerFile = new ManagerFile();
        companyFile = new CompanyFile();
        productFile  = new ProductFile();

        warehouseManagerStage = primaryStage;
        warehouseManagerStage.setTitle("Warehouse Management");
        warehouseManagerRoot = FXMLLoader.load(getClass().getResource("warehouseManagerWindow.fxml"));


        warehouseManagerScene = new Scene(warehouseManagerRoot);

    }



    private EmployeeFile employeeFile;
    public EmployeeFile getEmployeeFile() {
        return employeeFile;
    }
    private ManagerFile managerFile;
    public ManagerFile getManagerFile() {
        return managerFile;
    }
    private CompanyFile companyFile;
    public CompanyFile getCompanyFile() {
        return companyFile;
    }
    private ProductFile productFile;
    public ProductFile getProductFile(){
        return productFile;
    }



    private Stage warehouseManagerStage;
    private Parent warehouseManagerRoot;
    private Scene warehouseManagerScene;
    private Stage incorrectInfoPopupStage;
    private Parent incorrectInfoPopupRoot;
    private Scene incorrectInfoPopupScene;





    public void showWarehouseManagerWindow() throws IOException {
        readAllFiles();
        warehouseManagerStage.setScene(warehouseManagerScene);
        warehouseManagerStage.show();
    }




    public void showPopupWithOk(String announcement) throws IOException {
        incorrectInfoPopupStage = new Stage();
        incorrectInfoPopupStage.setResizable(false);
        incorrectInfoPopupStage.setTitle("Popup");
        PopupWithOk.announcement = announcement;
        incorrectInfoPopupRoot = FXMLLoader.load(getClass().getResource("popupWithOk.fxml"));
        incorrectInfoPopupScene = new Scene(incorrectInfoPopupRoot);
        incorrectInfoPopupStage.setScene(incorrectInfoPopupScene);
        incorrectInfoPopupStage.initModality(Modality.APPLICATION_MODAL);
        incorrectInfoPopupStage.showAndWait();
    }


    public void readAllFiles(){
        System.out.println("reading files......");
        employeeFile.readFile();
        managerFile.readFile();
        companyFile.readFile();
        productFile.readFile();
        employeeFile.updateInfo();
        managerFile.updateInfo();
        companyFile.updateInfo();
        productFile.updateInfo();
    }


    public Stage getStage(ActionEvent e) throws IOException {
        return  (Stage) ((Node) e.getSource()).getScene().getWindow();
    }


    public boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
