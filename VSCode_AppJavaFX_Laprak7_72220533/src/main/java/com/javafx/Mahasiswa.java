package com.javafx;

import java.util.StringTokenizer;

public class Mahasiswa {
    String nim, nama, kota, grup;

    public Mahasiswa(String nim, String nama, String kota, String grup) {
        this.nim = nim;
        this.nama = nama;
        this.kota = kota;
        this.grup = grup;
    }

    public String getNim(){
        return this.nim;
    }
    
    public int getIntNim() {
        return Integer.parseInt(this.nim);
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String temp_nama = "";
        String capitalized = "";
        StringTokenizer st = new StringTokenizer(nama, " ");
        for(int i = 0; i<st.countTokens()+1;i++){
            temp_nama = st.nextToken();
            capitalized += temp_nama.substring(0,1).toUpperCase() + temp_nama.substring(1);
            if(i<st.countTokens()){
                capitalized += " ";
            }
        }
        this.nama = capitalized;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota.toUpperCase();
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getMahasiswa(){
        return getNim() + "," + getNama() + "," + getKota() + "," + getGrup();
    }
}
