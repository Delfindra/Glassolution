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
public class OrderData{
    String namaProduk, tanggalBeli;
    int harga, jumlahBeli, totalHarga;
    
    public OrderData(String nmp, int hrg, int jbl, int ttl, String tgl) {
        this.namaProduk = nmp;
        this.harga = hrg;
        this.jumlahBeli = jbl;
        this.totalHarga = ttl;
        this.tanggalBeli = tgl;
    }
    
    public OrderData(String nmp) {
        this.namaProduk = nmp;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }
    
    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public void setJumlahBeli(int jumlahBeli) {
        this.jumlahBeli = jumlahBeli;
    }
    
    public String getNamaProduk() {
        return namaProduk;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public int getTotalHarga() {
        return totalHarga;
    }
    
    public String getTanggalBeli() {
        return tanggalBeli;
    }
    
    public void HitungTotal(){
        this.totalHarga = this.harga * this.jumlahBeli;
    }
    
}
