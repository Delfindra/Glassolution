/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.ProductData;

/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLProductController implements Initializable { 
    XStream xstream = new XStream(new StaxDriver());
    
    void bukaXML() {
        FileInputStream input = null;
        try {
            input = new FileInputStream("produk.xml");
            int isi;
            char charnya;
            String stringnya;
            stringnya ="";
            while ((isi = input.read()) != -1) {
                charnya= (char) isi;
                stringnya = stringnya + charnya;
                }    
            data = (ObservableList) xstream.fromXML(stringnya);
            }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
            }
        finally{
            if(input != null){
                try{
                    input.close();
                    }
                catch (IOException e) {
                    e.printStackTrace();
                    }
                }    
            }
        }
    
    ObservableList data = observableArrayList(
            new ProductData("Gayo Aceh", "Arabika", 500, 150000),
            new ProductData("Kopi Luwak", "Luwak", 300, 176000), 
            new ProductData("Bali Kintamani", "Arabika", 200, 86000), 
            new ProductData("Kopi Merapi", "Arabika", 200, 9000), 
            new ProductData("Temanggung Honey", "Arabika", 200, 86000) 
            );
    
    @FXML
    private Label Title;
    
    @FXML
    private ImageView kopi;
    
    @FXML
    private Label np;
    
    @FXML
    private Label jn;
        
    @FXML
    private Label br;    

    @FXML
    private Label hg;
    
    @FXML
    private TextField tfn;
    
    @FXML
    private TextField tfj;
        
    @FXML
    private TextField tfb;    
        
    @FXML
    private TextField tfh;  
    
    @FXML
    private Button Add;
        
    @FXML
    private Button Delete;  
      
    @FXML
    private Button Back;  
        
    @FXML
    private TableView<ProductData> dataProduk; 
    
    @FXML
    private TableColumn Nama;
    
    @FXML
    private TableColumn Jenis;   
        
    @FXML
    private TableColumn Berat;
    
    @FXML
    private TableColumn Harga;
    
    @FXML
    private Stage stage;
    
    @FXML
    private Scene scene;
    
    @FXML
    private Parent root;
    
    @FXML
    private void handleAdd(ActionEvent event) throws IOException {
        if(tfn.getText().isEmpty() || tfj.getText().isEmpty() || 
                tfb.getText().isEmpty() || tfh.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Wajib diisi!!");
                alert.showAndWait();
            } 
        else{
            String Sementara1 = tfn.getText();
            String Sementara2 = tfj.getText();
            int Sementara3 = Integer.parseInt(tfb.getText());
            int Sementara4 = Integer.parseInt(tfh.getText());

            data.add(new ProductData(Sementara1, Sementara2, Sementara3, Sementara4));
            
            tfn.setText("");
            tfj.setText("");
            tfb.setText("");
            tfh.setText("");
        
            String xml = xstream.toXML(data);
            FileOutputStream output = null;
            try {
                output = new FileOutputStream("produk.xml");
                byte[] bytes = xml.getBytes("UTF-8");
                output.write(bytes);
                } 
            catch (Exception e) {
                System.err.println("Perhatian: " + e.getMessage());
                } 
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data berhasil disimpan");
            alert.showAndWait();
        }
    }
                                         
    @FXML
    private void handleDelete(ActionEvent event) throws IOException {
        int picked = dataProduk.getSelectionModel().getSelectedIndex();
        
        if (picked >= 0) {
            dataProduk.getItems().remove(picked);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data berhasil dihapus");
            alert.showAndWait();
            
            String xml = xstream.toXML(data);
            FileOutputStream input = null;
            try {
                input = new FileOutputStream("produk.xml");
                byte[] bytes = xml.getBytes();
                input.write(bytes);
            } 
            catch (Exception e) {
                System.err.println("Perhatian: " + e.getMessage());
            } 
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pilih produk yang mau dihapus");
            alert.showAndWait();
            } 
    }
    
    @FXML
    private void editNama(TableColumn.CellEditEvent<ProductData, String> editNama) {
        ProductData produk = dataProduk.getSelectionModel().getSelectedItem();  
        produk.setNama(editNama.getNewValue());
        
        String xml = xstream.toXML(data);
        FileOutputStream input = null;
        try {
            input = new FileOutputStream("produk.xml");
            byte[] bytes = xml.getBytes();
            input.write(bytes);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void editJenis(TableColumn.CellEditEvent<ProductData, String> editJenis) {
        ProductData produk = dataProduk.getSelectionModel().getSelectedItem();  
        produk.setJenis(editJenis.getNewValue());
        
        String xml = xstream.toXML(data);
        FileOutputStream input = null;
        try {
            input = new FileOutputStream("produk.xml");
            byte[] bytes = xml.getBytes();
            input.write(bytes);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void editBerat(TableColumn.CellEditEvent<ProductData, Integer> editBerat) {
        ProductData produk = dataProduk.getSelectionModel().getSelectedItem();  
        produk.setBerat(editBerat.getNewValue());
        
        String xml = xstream.toXML(data);
        FileOutputStream input = null;
        try {
            input = new FileOutputStream("produk.xml");
            byte[] bytes = xml.getBytes();
            input.write(bytes);
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @FXML
    private void editHarga(TableColumn.CellEditEvent<ProductData, Integer> editHarga) {
        ProductData produk = dataProduk.getSelectionModel().getSelectedItem();  
        produk.setHarga(editHarga.getNewValue());
        
        String xml = xstream.toXML(data);
        FileOutputStream input = null;
        try {
            input = new FileOutputStream("produk.xml");
            byte[] bytes = xml.getBytes();
            input.write(bytes);
        } 
        catch (Exception e) {
            e.printStackTrace();
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
        // buat buka file xml
        bukaXML();
        Nama.setCellValueFactory(new PropertyValueFactory<ProductData, String>("Nama"));
        Jenis.setCellValueFactory(new PropertyValueFactory<ProductData, String>("Jenis"));
        Berat.setCellValueFactory(new PropertyValueFactory<ProductData, String>("Berat"));
        Harga.setCellValueFactory(new PropertyValueFactory<ProductData, String>("Harga"));
        dataProduk.setItems(data);
        
        // buat edit data
        dataProduk.setEditable(true);
        Nama.setCellFactory(TextFieldTableCell.forTableColumn());
        Jenis.setCellFactory(TextFieldTableCell.forTableColumn());
        Berat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Harga.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));      
        
    }    
}

