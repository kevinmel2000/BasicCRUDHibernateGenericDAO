/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.mhs;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutRightTransition;
import com.eby.autocomplete.ComboBoxAuto;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Dosen;
import com.eby.orm.entity.Mahasiswa;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.LocalDateStringConverter;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class MhsViewController implements Initializable {
    
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField txtNIM;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtKelas;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private ComboBox<String> cbDosen;
    @FXML
    private DatePicker dateLahir;
    @FXML
    private TableView<Mahasiswa> tableMhs;
    @FXML
    private Button btTambah;
    @FXML
    private Button btUbah;
    @FXML
    private Button btHapus;
    @FXML
    private FontAwesomeIconView ubahIcon;
    @FXML
    private FontAwesomeIconView hapusIcon;
    @FXML
    private TextField txtCari;
    @FXML
    private FontAwesomeIconView cariIcon;
    @FXML
    private Text txtClose;
    @FXML
    private FontAwesomeIconView plusIcon;
    @FXML
    private AnchorPane paneView;
    private MhsModel model;
    private MhsTableModel tableModel;
    private MhsComboBoxModel comboBoxModel;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        initComboBox();
        fadeIn();
        con = new Config();
        Platform.runLater(() -> {
            cbDosen.setOnKeyReleased(KeyEvent -> {
                new ComboBoxAuto<>(cbDosen);
            });
        });
    }
    
    @FXML
    private void tableClicked(MouseEvent event) {
        int index = tableMhs.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Mahasiswa m = tableModel.getItem().get(index);
            txtNIM.setText(m.getNim());
            txtNama.setText(m.getNama());
            txtKelas.setText(m.getKelas());
            txtAlamat.setText(m.getAlamat());
            LocalDateStringConverter ld = new LocalDateStringConverter();
            dateLahir.setValue(ld.fromString(m.getTglLahir()));
            cbDosen.getSelectionModel().select(m.getDosen().getNama());
        } else {
            System.out.println("index -1, pilih data");
        }
    }
    
    @FXML
    private void tambahAction(ActionEvent event) {
        String nim = txtNIM.getText();
        String nama = txtNama.getText();
        String kelas = txtKelas.getText();
        String alamat = txtAlamat.getText();
        //Konversi localDate menjadi String
        LocalDateStringConverter ld = new LocalDateStringConverter();
        String tglLahir = ld.toString(dateLahir.getValue());
        int dosen = cbDosen.getSelectionModel().getSelectedIndex();
        
        if (nim.equals("") || nama.equals("") || kelas.equals("") || alamat.equals("") || tglLahir.isEmpty() || dosen == -1) {
            con.dialog(Alert.AlertType.WARNING, "data tidak boleh kosong", null);
        } else {
            //megambil index pada combo box
            int index = cbDosen.getSelectionModel().getSelectedIndex();
            //menyimpan index pada object
            Dosen d = comboBoxModel.get(index);
            Mahasiswa m = new Mahasiswa(nim, d, nama, kelas, alamat, tglLahir);
            model.save(m);
            con.dialog(Alert.AlertType.INFORMATION, "data berhasil disimpan", null);
            loadData();
            loadComboBox();
            reset();
        }
        
    }
    
    @FXML
    private void ubahAction(ActionEvent event) {
        String nim = txtNIM.getText();
        String nama = txtNama.getText();
        String kelas = txtKelas.getText();
        String alamat = txtAlamat.getText();
        LocalDateStringConverter ld = new LocalDateStringConverter();
        String tglLahir = ld.toString(dateLahir.getValue());
        int dosen = cbDosen.getSelectionModel().getSelectedIndex();
        
        if (nim.equals("") || nama.equals("") || kelas.equals("") || alamat.equals("") || tglLahir.isEmpty() || dosen == -1) {
            con.dialog(Alert.AlertType.WARNING, "data tidak boleh kosong", null);
        } else {
            int index = cbDosen.getSelectionModel().getSelectedIndex();
            Dosen d = comboBoxModel.get(index);
            Mahasiswa m = new Mahasiswa(nim, d, nama, kelas, alamat, tglLahir);
            model.update(m);
            con.dialog(Alert.AlertType.INFORMATION, "data berhasil diupdate", null);
            loadData();
            loadComboBox();
            reset();
        }
    }
    
    @FXML
    private void hapusAction(ActionEvent event) {
        int index = tableMhs.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Mahasiswa m = tableModel.getItem().get(index);
            model.delete(m);
            con.dialog(Alert.AlertType.WARNING, "data berhasil dihapus", null);
            loadData();
            loadComboBox();
            reset();
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data", null);
        }
    }
    
    @FXML
    private void onKeyReleased(KeyEvent event) {
        String key = txtCari.getText();
        if (key.equals("")) {
            loadData();
        } else {
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableMhs.setItems(tableModel.getItem());
            tableModel.getItem().addAll(model.findData(key));
        }
    }
    
    @FXML
    private void closeAction(MouseEvent event) {
        this.fadeOut();
    }
    
    public void fadeIn() {
        new FadeInLeftTransition(gridPane).play();
        new FadeInLeftTransition(btTambah).play();
        new FadeInLeftTransition(btUbah).play();
        new FadeInLeftTransition(btHapus).play();
        new FadeInLeftTransition(plusIcon).play();
        new FadeInLeftTransition(ubahIcon).play();
        new FadeInLeftTransition(hapusIcon).play();
        new FadeInLeftTransition(txtCari).play();
        new FadeInLeftTransition(cariIcon).play();
        new FadeInLeftTransition(tableMhs).play();
//        new FadeInLeftTransition(paneView).play();
        new FadeInLeftTransition(txtClose).play();
    }
    
    public void fadeOut() {
        
        new FadeOutRightTransition(gridPane).play();
        new FadeOutRightTransition(btTambah).play();
        new FadeOutRightTransition(btUbah).play();
        new FadeOutRightTransition(btHapus).play();
        new FadeOutRightTransition(plusIcon).play();
        new FadeOutRightTransition(ubahIcon).play();
        new FadeOutRightTransition(hapusIcon).play();
        new FadeOutRightTransition(txtCari).play();
        new FadeOutRightTransition(cariIcon).play();
        new FadeOutRightTransition(tableMhs).play();
        new FadeOutRightTransition(paneView).play();
        new FadeOutRightTransition(txtClose).play();
        
    }
    
    private void initModel() {
        model = new MhsModel();
        model.setController(this);
    }
    
    private void initTable() {
        tableModel = new MhsTableModel();
        tableMhs.getColumns().addAll(tableModel.getAllColumn());
        tableMhs.setItems(tableModel.getItem());
        tableModel.getItem().addAll(model.list());
    }
    
    public void initComboBox() {
        comboBoxModel = new MhsComboBoxModel();
        cbDosen.setItems(comboBoxModel.getItems());
        comboBoxModel.add(model.listDosen());
    }
    
    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableMhs.setItems(tableModel.getItem());
        tableModel.getItem().addAll(model.list());
    }
    
    public void loadComboBox() {
        comboBoxModel.getItems().remove(0, comboBoxModel.getItems().size());
        cbDosen.setItems(comboBoxModel.getItems());
        comboBoxModel.add(model.listDosen());
    }
    
    public void reset() {
        txtNIM.setText("");
        txtNama.setText("");
        txtKelas.setText("");
        txtAlamat.setText("");
        dateLahir.setValue(null);
        cbDosen.getSelectionModel().clearSelection();
    }
}
