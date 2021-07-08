/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.OrderData;
import model.ProductData;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Glassolution
 */
public class FXMLOrderController implements Initializable {
    
    @FXML
    private Label lblTitlePage;
    
    @FXML
    private Label lblNama;
    
    @FXML
    private Label lblJumlah;
    
    @FXML
    private Label lblTanggal;
    
    @FXML
    private ChoiceBox cbNama;
    
    @FXML
    private TextField tfJumlah;
    
    @FXML
    private DatePicker dpTanggal;
    
    @FXML
    private ImageView imgOrder;
    
    @FXML
    private Button buttonPesan;
    
    @FXML
    private Button buttonHapus;
    
    @FXML
    private Button buttonBack;
    
    @FXML
    private TableView<OrderData> tvDataOrder;
    
    @FXML
    private TableColumn tcNama;
    
    @FXML
    private TableColumn tcHarga;   
        
    @FXML
    private TableColumn tcJumlah;
    
    @FXML
    private TableColumn tcTotalHarga;
    
    @FXML
    private TableColumn tcTanggal;
    
    @FXML
    private Stage stage;
    
    @FXML
    private Scene scene;
    
    @FXML
    private Parent root;
    
    @FXML
    private Button buttonSimpan;
    
    XStream xstream = new XStream(new StaxDriver());
    
    OrderData od = new OrderData("", 0, 0, 0, "");
    ProductData pd = new ProductData("", "", 0, 80000);
    
    ObservableList<OrderData> dtorder = FXCollections.observableArrayList(new OrderData("", 0, 0, 0, ""));
    ObservableList<ProductData> dtproduct = FXCollections.observableArrayList(new ProductData("", "", 0, 0));
       
    void bukaOrderanXML(){
        FileInputStream cobiaja = null;
        try {
            cobiaja = new FileInputStream("order.xml");
            
            int isi;
            char charnya;
            String stringnya = "";
            
            while ((isi = cobiaja.read()) != -1) {
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            // string isi file dikembalikan menjadi larik double
            dtorder = (ObservableList) xstream.fromXML(stringnya);
        }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
        }
        finally{
            if(cobiaja != null){
                try{
                    cobiaja.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    void bukaProdukXML(){
        FileInputStream cobiaja = null;
        try {
            cobiaja = new FileInputStream("produk.xml");
            
            int isi;
            char charnya;
            String stringnya = "";
            
            while ((isi = cobiaja.read()) != -1) {
                charnya = (char) isi;
                stringnya = stringnya + charnya;
            }
            // string isi file dikembalikan menjadi larik double
            dtproduct = (ObservableList) xstream.fromXML(stringnya);
        }
        catch (Exception e){
            System.err.println("test: "+e.getMessage());
        }
        finally{
            if(cobiaja != null){
                try{
                    cobiaja.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    void saveXML(){
        // larik double diubah menjadi string dengan format XML
        String xml = xstream.toXML(dtorder);

        FileOutputStream cobaaja = null;
        try {
            cobaaja = new FileOutputStream("order.xml");
            
            // mengubah karakter penyusun string xml sebagai
            // bytes (berbentuk nomor2 kode ASCII)
            byte[] bytes = xml.getBytes("UTF-8");
            
            // menyimpan file dari bytes
            cobaaja.write(bytes);
        }
        catch (Exception e) {
            System.err.println("Perhatian: " + e.getMessage());
        }
        finally {
            if (cobaaja != null) {
                try {
                    cobaaja.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @FXML
    private void handleButtonAdd() {
        if(cbNama.getValue() == null || tfJumlah.getText().isEmpty() || dpTanggal.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Tolong lengkapi semua isian!");
            alert.showAndWait();
        } 
        else{
            String Sementara1 = cbNama.getValue().toString();
            int Sementara2 = pd.getHarga();
            od.setHarga(Sementara2);

            int Sementara3 = Integer.parseInt(tfJumlah.getText());
            od.setJumlahBeli(Sementara3);
            od.HitungTotal();
            int Sementara4 = od.getTotalHarga();
            String Sementara5 = dpTanggal.getValue().toString();
            dtorder.add(new OrderData(Sementara1, Sementara2, Sementara3, Sementara4, Sementara5));
            cbNama.setValue(null);
            tfJumlah.setText("");
            dpTanggal.setValue(null);

            saveXML();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Pesanan berhasil ditambahkan");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleButtonDelete() {
        int picked = tvDataOrder.getSelectionModel().getSelectedIndex();
        if (picked >= 0) {
            tvDataOrder.getItems().remove(picked);
        }
        
        saveXML();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Pesanan berhasil dihapus");
        alert.showAndWait();
    }
    
    @FXML
    private void handleButtonBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void onEditNama(TableColumn.CellEditEvent<OrderData, String> namaCellEditEvent){
        od = tvDataOrder.getSelectionModel().getSelectedItem();
        od.setNamaProduk(namaCellEditEvent.getNewValue());
        
        saveXML();
    }
    
    @FXML
    private void onEditJumlah(TableColumn.CellEditEvent<OrderData, Integer> jumlahCellEditEvent){
        od = tvDataOrder.getSelectionModel().getSelectedItem();
        od.setJumlahBeli(jumlahCellEditEvent.getNewValue());
        
        saveXML();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bukaOrderanXML();
        bukaProdukXML();
        
        tcNama.setCellValueFactory(new PropertyValueFactory<OrderData, String>("NamaProduk"));
        tcHarga.setCellValueFactory(new PropertyValueFactory<OrderData, String>("Harga"));
        tcJumlah.setCellValueFactory(new PropertyValueFactory<OrderData, String>("JumlahBeli"));
        tcTotalHarga.setCellValueFactory(new PropertyValueFactory<OrderData, String>("TotalHarga"));
        tcTanggal.setCellValueFactory(new PropertyValueFactory<OrderData, String>("TanggalBeli"));
        tvDataOrder.setItems(dtorder);
        
        tcNama.setCellFactory(TextFieldTableCell.forTableColumn());
        tcJumlah.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tvDataOrder.setEditable(true);
        
        cbNama.getItems().addAll("Kopi Gayo", "Kopi Merapi", "Kopi Jogja");
    }    
    
}
