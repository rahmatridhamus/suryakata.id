package com.idev.rahmatridham.imm.model;

/**
 * Created by rahmatridham on 8/17/2016.
 */
public class KabinetModel {
    String urlPhoto,nama,nim,jabatan,divisi;

    public KabinetModel(String urlPhoto, String nama, String nim, String jabatan, String divisi) {
        this.urlPhoto = urlPhoto;
        this.nama = nama;
        this.nim = nim;
        this.jabatan = jabatan;
        this.divisi = divisi;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }
}
