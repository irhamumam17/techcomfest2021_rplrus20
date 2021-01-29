package com.example.donacare.Model;

public class KelasModel {

    String id;
    String title_kelas;
    String subtitle_kelas;

    public KelasModel(String id, String title_kelas, String subtitle_kelas) {
        this.id = id;
        this.title_kelas = title_kelas;
        this.subtitle_kelas = subtitle_kelas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle_kelas() {
        return title_kelas;
    }

    public void setTitle_kelas(String title_kelas) {
        this.title_kelas = title_kelas;
    }

    public String getSubtitle_kelas() {
        return subtitle_kelas;
    }

    public void setSubtitle_kelas(String subtitle_kelas) {
        this.subtitle_kelas = subtitle_kelas;
    }






}
