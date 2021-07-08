/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ProfileData;

/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLMenuController implements Initializable {
    
    XStream xstream = new XStream(new StaxDriver());
    
    ProfileData prof = new ProfileData("", "");
    
    @FXML
    private Label labelNamaUKM;
    
    @FXML
    private Label dashboard;
    
    @FXML
    private ImageView imageProfile;
    
    @FXML
    private ImageView imageOrder;
    
    @FXML
    private ImageView imageProduct;
    
    @FXML
    private ImageView imageStatistic;
    
    @FXML
    private Button buttonProfile;
    
    @FXML
    private Button buttonMenu;
    
    @FXML
    private Button buttonAboutus;
    
    @FXML
    private Button buttonLogout;
    
    @FXML
    private Button buttonInput;
    
    @FXML
    private Button buttonTambah;
    
    @FXML
    private Button buttonStatistik;
    
    @FXML
    private Stage stage;
    
    @FXML
    private Scene scene;
    
    @FXML
    private Parent root;
    
    void bukaXML() {
        FileInputStream a = null;
        try {
            a = new FileInputStream("profile.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = a.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
                }    
            prof = (ProfileData) xstream.fromXML(stringnya);
            }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
            }
        finally{
            if(a != null){
                try{
                    a.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }    
        }
    }
    
    @FXML
    private void handleButtonProfile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLProfile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonAboutus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLAboutus.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonInput(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLOrder.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonTambah(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLProduct.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void handleButtonStatistik(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLStatistic.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     
    @FXML
    private void handleButtonLogout(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaXML();
        
        labelNamaUKM.setText(prof.getNama());
    }
    
}
