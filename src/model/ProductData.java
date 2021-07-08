/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Glassolution
 */
public class ProductData {
    String Nama;
    String Jenis;
    int Berat;
    int Harga;
    
    public ProductData(String Nama, String Jenis, int Berat, int Harga) {
        this.Nama  = Nama;
        this.Jenis = Jenis;
        this.Berat = Berat;
        this.Harga = Harga;                   
    }
    
    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String Jenis) {
        this.Jenis = Jenis;
    }

    public int getBerat() {
        return Berat;
    }

    public void setBerat(int Berat) {
        this.Berat = Berat;
    }

    public int getHarga() {
        return Harga;
    }

    public void setHarga(int Harga) {
        this.Harga = Harga;
    }
    
}
