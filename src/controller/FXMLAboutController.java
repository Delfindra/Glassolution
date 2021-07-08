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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AboutData;

/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLAboutController implements Initializable {
    
    AboutData ab = new AboutData();
    
    @FXML
    private Label lblAboutus;
    
    @FXML
    private Label lblPlatform;
    
    @FXML
    private Label lblDeskripsi;
    
    @FXML
    private ImageView imageCoffee1;
    
    @FXML
    private Button Back; 
    
    @FXML
    private Stage stage;
    
    @FXML
    private Scene scene;
    
    @FXML
    private Parent root;
    
    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblPlatform.setText(ab.getNama());
        lblDeskripsi.setText(ab.getDeskripsi());
    }    
    
}
