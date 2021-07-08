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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLStatisticController implements Initializable {
    
    XYChart.Series<String, Integer> LCStatistik = new XYChart.Series<>();
    
    @FXML
    private Label Title;
    
    @FXML
    private LineChart Statistik;
           
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
        LCStatistik.getData().add(new XYChart.Data<>("Januari", 10));
        LCStatistik.getData().add(new XYChart.Data<>("Februari", 30));
        LCStatistik.getData().add(new XYChart.Data<>("Maret", 20));
        LCStatistik.getData().add(new XYChart.Data<>("April", 50));
               
        Statistik.getData().add(LCStatistik);
    }    
    
}
