package com.example.sushigo;

public class Data {
    private String nama, telepon, alamat;

    public Data() {
    }

    public Data(String id, String nama, String telepon, String alamat){
        this.nama = nama;
        this.telepon = telepon;
        this.alamat = alamat;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.telepon = alamat;
    }
}

