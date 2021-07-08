/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AboutData;

/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLLoginController implements Initializable {
    
    AboutData ab = new AboutData();
    
    String username = "user";
    String password1 = "12345";
    
    
    @FXML
    private Stage stage;
    
    @FXML
    private Scene scene;
    
    @FXML
    private Parent root;
    
    @FXML
    private Button login;
    
    @FXML
    private Label lblPlatform;
    
    @FXML
    private TextField user; 
    
    @FXML
    private PasswordField pass; 
    
    @FXML
    private void buttonLogin(ActionEvent event) throws IOException {
        String name = user.getText();
        String password = pass.getText();
        if(name.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wajib diisi!!");
            alert.showAndWait();
        }
        else{
            if(name.equals(username) && password.equals(password1)){
                Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMenu.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Maaf!! Anda Tidak Terdaftar");
                alert.showAndWait();
                }   
            }       
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblPlatform.setText(ab.getNama());
    }     
}
