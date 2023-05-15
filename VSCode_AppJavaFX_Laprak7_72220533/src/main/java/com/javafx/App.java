package com.javafx;

import java.io.IOException;
// error stuck gabisa jalan, infinity loop/endless loop
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//button delete dan close belum diimpementasikan
public class App extends Application {
    Text txtJudul = new Text("Data Mahasiswa");
    Label lblNim = new Label("Nim");
    Label lblNama = new Label("Nama");
    Label lblKota = new Label("Kota");
    Label lblGrup = new Label("Grup");
    Label lblKetNim = new Label("(diisi oleh sistem)");
    Label lblKetGrup = new Label("(diisi oleh sistem)");
    Label lblSpasi1 = new Label(" ");
    Label lblSpasi2 = new Label(" ");
    TextField txtNim = new TextField();
    TextField txtNama = new TextField();
    TextField txtKota = new TextField();
    TextField txtGrup = new TextField();
    Button btnTop = new Button("|<");
    Button btnBack = new Button("<");
    Button btnNext = new Button(">");
    Button btnEnd = new Button(">|");
    Button btnNew = new Button("New");
    Button btnEdit = new Button("Edit");
    Button btnSave = new Button("Save");
    Button btnUndo = new Button("Undo");
    Button btnDel = new Button("Delete");
    Button btnClose = new Button("Close");
    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    HBox hb3 = new HBox();

    boolean baru = false;
    Tabel dafTabel = new Tabel(20);

    void buka() {
        txtNama.setEditable(true);
        txtKota.setEditable(true);
        btnTop.setDisable(true);
        btnBack.setDisable(true);
        btnNext.setDisable(true);
        btnEnd.setDisable(true);
        btnNew.setDisable(true);
        btnEdit.setDisable(true);
        btnDel.setDisable(true);
        btnSave.setDisable(false);
        btnUndo.setDisable(false);
        txtNama.requestFocus();
    }

    void tutup() {
        txtNama.setEditable(false);
        txtKota.setEditable(false);
        btnTop.setDisable(false);
        btnBack.setDisable(false);
        btnNext.setDisable(false);
        btnEnd.setDisable(false);
        btnNew.setDisable(false);
        btnEdit.setDisable(false);
        btnDel.setDisable(false);
        btnSave.setDisable(true);
        btnUndo.setDisable(true);
    }

    void tampil() {
        Mahasiswa mhs = dafTabel.readMhs();
        txtNim.setText(mhs.nim);
        txtNama.setText(mhs.nama);
        txtKota.setText(mhs.kota);
        txtGrup.setText(mhs.grup);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        txtJudul.setFont(Font.font("Arial", 30));
        txtNim.setMaxWidth(80);
        txtNim.setEditable(true);
        txtNama.setMinWidth(300);
        txtKota.setMaxWidth(150);
        txtGrup.setMaxWidth(25);
        txtGrup.setEditable(false);
        hb1.getChildren().addAll(txtNim, lblKetNim);
        hb1.setSpacing(5);
        hb2.getChildren().addAll(txtGrup, lblKetGrup, btnSave, btnUndo);
        hb2.setSpacing(5);
        hb3.getChildren().addAll(btnTop, btnBack, btnNext, btnEnd, lblSpasi1,
                btnNew, btnEdit, btnDel, lblSpasi2, btnClose);
        hb3.setSpacing(5);
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 400, 200);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(10);
        grid.add(txtJudul, 0, 0, 2, 1);
        grid.add(lblNim, 0, 1);
        grid.add(hb1, 1, 1);
        grid.add(lblNama, 0, 2);
        grid.add(txtNama, 1, 2);
        grid.add(lblKota, 0, 3);
        grid.add(txtKota, 1, 3);
        grid.add(lblGrup, 0, 4);
        grid.add(hb2, 1, 4);
        grid.add(hb3, 0, 5, 2, 1);
        tutup();
        tampil();
        
        btnNew.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                baru = true;
                buka();
                txtNim.setText("");
                txtNama.setText("");
                txtKota.setText("");
                txtGrup.setText("");
            }
        });

        btnEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                baru = false;
                buka();
            }
        });

        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nim, nama, kota, grup;
                nim = txtNim.getText();
                nama = txtNama.getText();
                kota = txtKota.getText();
                grup = txtGrup.getText();
                Mahasiswa mhs = new Mahasiswa(nim, nama, kota, grup);
                if (baru){
                    try {
                        dafTabel.addMhs(mhs);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    dafTabel.updateMhs(mhs);
                }
                tutup();
                if(dafTabel.getCacah()>0){
                    tampil();
                }
                try {
                    dafTabel.saveToFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tampil();
                tutup();
            }
        });
        btnTop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dafTabel.moveFirst();
                tampil();
            }
        });
        btnBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dafTabel.movePrevious();
                tampil();
            }
        });
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dafTabel.moveNext();
                tampil();
            }
        });
        btnEnd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dafTabel.moveLast();
                tampil();
            }
        });

        btnDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                dafTabel.deleteMhs();
                try {
                    dafTabel.saveToFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                primaryStage.close();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Data Mahasiswa");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // <-- memulai antarmuka grafis
    }
}