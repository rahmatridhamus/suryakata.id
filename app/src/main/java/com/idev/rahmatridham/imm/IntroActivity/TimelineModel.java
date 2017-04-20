package com.idev.rahmatridham.imm.IntroActivity;

/**
 * Created by wildan on 16/03/17.
 */

public class TimelineModel {
    private String judul, isi;

    public TimelineModel(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
