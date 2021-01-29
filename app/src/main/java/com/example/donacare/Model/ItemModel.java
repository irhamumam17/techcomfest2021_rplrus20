package com.example.donacare.Model;

public class ItemModel {
    private String jenis,jumlah,berat,alamat,imageUrl;

    public ItemModel(String jenis, String jumlah, String berat, String alamat, String imageUrl) {
        this.jenis = jenis;
        this.jumlah = jumlah;
        this.berat = berat;
        this.alamat = alamat;
        this.imageUrl = imageUrl;
    }


    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
