/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.dosen;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutRightTransition;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Dosen;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class DosenViewController implements Initializable {

    @FXML
    private AnchorPane paneView;
    @FXML
    private GridPane gridPane;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtNIP;
    @FXML
    private TextField txtAjar;
    @FXML
    private TableView<Dosen> tableDosen;
    @FXML
    private Button btTambah;
    @FXML
    private Button btUbah;
    @FXML
    private Button btHapus;
    @FXML
    private TextField txtCari;
    @FXML
    private FontAwesomeIconView cariIcon;
    @FXML
    private FontAwesomeIconView plusIcon;
    @FXML
    private FontAwesomeIconView editIcon;
    @FXML
    private FontAwesomeIconView hapusIcon;
    @FXML
    private Text txtClose;
    
    private DosenTableModel tableModel;
    private DosenModel model;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        fadeIn();
        con = new Config();
    }

    @FXML
    private void tableClick(MouseEvent event) {
        //Mendeteksi index data yang di klik
        int index = tableDosen.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //table model membaca data yang diklik
            Dosen d = tableModel.getItem().get(index);
            //mengambil data dari class entity dan mengesetnya agar tampil di textfield
            txtNIP.setText(String.valueOf(d.getNip()));
            txtNama.setText(d.getNama());
            txtAlamat.setText(d.getAlamat());
            txtAjar.setText(d.getMataAjar());
        } else {
            System.out.println("index -1, pilih data");
        }
    }

    @FXML
    private void tambahAction(ActionEvent event) {
        //Membuat variabel untuk memudahkan melakukan kondisi
        String nip = txtNIP.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String ajar = txtAjar.getText();

        if (nip.equals("") || nama.equals("") || alamat.equals("") || ajar.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "data tidak boleh kosong !", null);
        } else {
            //mengambil data dari variabel nip, nama, alamat, dan ajar untuk disimpan 
            Dosen d = new Dosen(Integer.valueOf(nip), nama, alamat, ajar, null);
            //melakukan proses penyimpanan
            model.save(d);
            con.dialog(Alert.AlertType.INFORMATION, "Data sudah disimpan", null);
            reset();
            loadData();
        }
    }

    @FXML
    private void ubahAction(ActionEvent event) {
        String nip = txtNIP.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String ajar = txtAjar.getText();

        if (nip.equals("") || nama.equals("") || alamat.equals("") || ajar.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "data tidak boleh kosong !", null);
        } else {
            Dosen d = new Dosen(Integer.valueOf(nip), nama, alamat, ajar, null);
            //melakuakan proses update
            model.update(d);
            con.dialog(Alert.AlertType.INFORMATION, "Data sudah diupdate", null);
            reset();
            loadData();
        }
    }

    @FXML
    private void hapusAction(ActionEvent event) {
        //mendeteksi index pada tableDosen dengan melakukan klik pada cell
        int index = tableDosen.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //mengambil index tersebut dan memasukkan kedalam object
            Dosen d = tableModel.getItem().get(index);
            //Melakukan proses hapus object yang berisi index
            model.delete(d);
            con.dialog(Alert.AlertType.INFORMATION, "data berhasil dihapus", null);
            //reset ulang textfield
            reset();
            //menampilkan data terbaru setelah proses hapus
            loadData();
        } else {
            con.dialog(Alert.AlertType.WARNING, "pilih data", null);
        }

    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            //melakukan loadData jika keyword kosong/null/""
            loadData();
        } else {
            //melakukan pencarian ke database jika keyword tidak kosong/null/""
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableModel.getItem().addAll(model.find(keyword));
            tableDosen.setItems(tableModel.getItem());
        }
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

    //Animasi masuk melalui sisi kiri
    public void fadeIn() {
        new FadeInLeftTransition(gridPane).play();
        new FadeInLeftTransition(btTambah).play();
        new FadeInLeftTransition(btHapus).play();
        new FadeInLeftTransition(btUbah).play();
        new FadeInLeftTransition(plusIcon).play();
        new FadeInLeftTransition(editIcon).play();
        new FadeInLeftTransition(hapusIcon).play();
        new FadeInLeftTransition(txtCari).play();
        new FadeInLeftTransition(cariIcon).play();
        new FadeInLeftTransition(tableDosen).play();
        new FadeInLeftTransition(txtClose).play();
    }

    //Animasi keluar ke sisi kanan
    public void fadeOut() {
        new FadeOutRightTransition(paneView).play();
        new FadeOutRightTransition(gridPane).play();
        new FadeOutRightTransition(btTambah).play();
        new FadeOutRightTransition(btHapus).play();
        new FadeOutRightTransition(btUbah).play();
        new FadeOutRightTransition(plusIcon).play();
        new FadeOutRightTransition(editIcon).play();
        new FadeOutRightTransition(hapusIcon).play();
        new FadeOutRightTransition(txtCari).play();
        new FadeOutRightTransition(cariIcon).play();
        new FadeOutRightTransition(tableDosen).play();
        new FadeOutRightTransition(txtClose).play();
    }

    //Mengeset controller yang akan dipakai oleh model
    private void initModel() {
        model = new DosenModel();
        model.setController(this);
    }

    public void reset() {
        txtNIP.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtAjar.setText("");
    }

    //Menginisialisasi column header dan load data dari database
    public void initTable() {
        tableModel = new DosenTableModel();
        tableDosen.getColumns().addAll(tableModel.getAllColumn());
        tableDosen.setItems(tableModel.getItem());
        tableModel.getItem().addAll(model.list());
    }

    //Membuat methode meload data kembali/refresh setelah aksi CRUD
    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.list());
        tableDosen.setItems(tableModel.getItem());
    }

}
