/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import model.ProfileData;


/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLProfileController implements Initializable {
    
    XStream xstream = new XStream(new StaxDriver());
    
    ProfileData prof = new ProfileData("", "");
    
    @FXML
    private Label labelProfile;
    
    @FXML
    private Label labelNamaUKM;
    
    @FXML
    private TextField tfNama;
    
    @FXML
    private TextArea taDeskripsi;
    
    @FXML
    private TextArea taDeskripsiUKM;
    
    @FXML
    private ImageView imageLogoUKM;
    
    @FXML
    private Button buttonBack;
    
    @FXML
    private Button buttonEditProfile;
    
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
    private void handleEditProfile(ActionEvent event) throws IOException {
        String nm = tfNama.getText();
        prof.setNama(nm);
        labelNamaUKM.setText(nm);
        String desk = taDeskripsi.getText();
        prof.setDeskripsi(desk);
        taDeskripsiUKM.setText(desk);
        
        tfNama.setText("");
        taDeskripsi.setText("");
        
        String xml = xstream.toXML(prof);
 
        FileOutputStream b = null;
        try {
            b = new FileOutputStream("profile.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            b.write(bytes);
        } 
        catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        } 
        finally {
            if (b != null) {
                try {
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
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
        bukaXML();
        
        labelNamaUKM.setText(prof.getNama());
        taDeskripsiUKM.setText(prof.getDeskripsi());
    }    
    
}
