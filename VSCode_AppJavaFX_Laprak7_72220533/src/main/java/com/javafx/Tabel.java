package com.javafx;

import java.io.IOException;

public class Tabel {
    private Mahasiswa[] daftar;
    private FileTeks file;
    private int cacah, pick_element;

    public Tabel(int size){
        this.daftar = new Mahasiswa[size];
        this.cacah = 0;
        this.file = new FileTeks("src\\main\\resources\\com\\listmahasiswa.csv");
        try {
            isiMahasiswa();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pick_element=-1;
    }
    
    public void isiMahasiswa() throws IOException{
        String[] array_csv = this.file.read();
        String[] row;
        String nim,nama,kota,grup;
        if(!array_csv[0].isEmpty()){
            for(int i=0; i<array_csv.length;i++){
                row = array_csv[i].split(",");
                nim = row[0];
                nama = row[1];
                kota = row[2];
                grup = row[3];
                this.addMhs(new Mahasiswa(nim, nama, kota, grup));
            }
        }
    }

    public void saveToFile() throws IOException{
        String save="";
        for (int i = 0; i < this.cacah; i++) {
            save += daftar[i].getMahasiswa() + "\n";            
        }
        this.file.write(save);
    }

    public int getCacah(){
        return this.cacah;
    }

    public void addMhs (Mahasiswa mhs) throws IOException{
        mhs.setNama(mhs.nama);
        mhs.setKota(mhs.kota);
        if(cacah<daftar.length){
            String grup;
            // if(cacah==0){
            //     nim = "72220001";
            // }else{
            //     nim = String.valueOf(Integer.parseInt(daftar[this.cacah-1].nim)+1);
            // }
            if(Integer.parseInt(mhs.nim)%2 == 1){
                grup = "A";
            }else{
                grup = "B";
            }
            mhs.setNim(mhs.nim);
            mhs.setGrup(grup);
            daftar[cacah++] = mhs;
            pick_element = cacah-1;
            selectionSort();
        }else{
            System.err.println("ARRAY SUDAH PENUH!");
        }
    }

    public Mahasiswa readMhs(){
        if(pick_element>=0 && pick_element<cacah){
            return this.daftar[pick_element];
        }else{
            return new Mahasiswa("", "", "", "");
        }
    }

    public void updateMhs(Mahasiswa mhs){
        daftar[pick_element] = mhs;
    }

    public void deleteMhs(){
        if(cacah>0){
            for(int i=pick_element;i<cacah;i++){
                daftar[i] = daftar[i+1];
            }
            cacah--;
            if(cacah>0){
                pick_element = 0;
            }else{
                pick_element = -1;
            }
        }else{
            System.err.println("TIDAK ADA MAHASISWA!");
        }
    }

    public void moveFirst(){
        pick_element = 0;
    }

    public void movePrevious(){
        if(pick_element>0){
            pick_element--;
        }else{
            System.err.println("Sudah paling awal.");
        }
    }

    public void moveNext(){
        if(pick_element<cacah-1){
            pick_element++;
        }else{
            System.err.println("Sudah paling akhir.");
        }
    }

    public void moveLast(){
        pick_element = cacah-1;
    }

    public void selectionSort(){
        int cari, pivot, min, byk=this.getCacah();
        Mahasiswa titip;
        for(pivot=0;pivot<byk;pivot++){
            min = pivot;
            for(cari=pivot+1;cari<byk;cari++){
                if(daftar[cari].getIntNim()<daftar[min].getIntNim()){
                    min = cari;
                }
            }
            titip = daftar[pivot];
            daftar[pivot] = daftar[min];
            daftar[min] = titip;
        }
    }
}