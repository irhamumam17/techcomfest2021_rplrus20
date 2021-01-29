package com.example.donacare.Model;

public class DanaModel {
    private String nominal,noRek,atasNama;

    public DanaModel(String nominal, String noRek, String atasNama) {
        this.nominal = nominal;
        this.noRek = noRek;
        this.atasNama = atasNama;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getAtasNama() {
        return atasNama;
    }

    public void setAtasNama(String atasNama) {
        this.atasNama = atasNama;
    }
}
