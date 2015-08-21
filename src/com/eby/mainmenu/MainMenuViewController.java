/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.mainmenu;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutLeftTransition;
import com.eby.frameworkConfig.Config;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class MainMenuViewController implements Initializable {

    @FXML
    private Button btMenu;
    @FXML
    private FontAwesomeIconView iconMenu;
    @FXML
    private Button btMahasiswa;
    @FXML
    private Button btDosen;
    @FXML
    private FontAwesomeIconView dosenIcon;
    @FXML
    private FontAwesomeIconView mhsIcon;
    @FXML
    private AnchorPane paneView;

    private Config con;
    @FXML
    private FontAwesomeIconView hideIcon;
    @FXML
    private Button closeButton;
    @FXML
    private Button btAbout;
    @FXML
    private FontAwesomeIconView codeIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Config();
        this.visibleOff();
        this.inActive();
    }

    @FXML
    private void iconMenuAction(MouseEvent event) {
        show();
    }

    @FXML
    private void mhsAction(ActionEvent event) {
        con.loadAnchorPane(paneView, "/mhs/MhsView.fxml");
    }

    @FXML
    private void dosenAction(ActionEvent event) {
        con.loadAnchorPane(paneView, "/dosen/DosenView.fxml");
    }

    @FXML
    private void iconDosenAction(MouseEvent event) {
        con.loadAnchorPane(paneView, "/dosen/DosenView.fxml");
    }

    @FXML
    private void iconMhsAction(MouseEvent event) {
        con.loadAnchorPane(paneView, "/mhs/MhsView.fxml");
    }

    @FXML
    private void menuAction(MouseEvent event) {
        show();
    }

    //Menyembunyikan tombol-tombol menu dan membuatnya tidak aktif
    public void visibleOff() {
        btDosen.setVisible(false);
        btMahasiswa.setVisible(false);
        mhsIcon.setVisible(false);
        dosenIcon.setVisible(false);
        hideIcon.setVisible(false);
        btAbout.setVisible(false);
        codeIcon.setVisible(false);
        inActive();
    }

    //Menampilkan tombol-tombol menu
    public void visibleOn() {
        btDosen.setVisible(true);
        btMahasiswa.setVisible(true);
        mhsIcon.setVisible(true);
        dosenIcon.setVisible(true);
        hideIcon.setVisible(true);
        btAbout.setVisible(true);
        codeIcon.setVisible(true);
    }

    //Animasi tombol masuk dari sisi kiri
    public void show() {
        visibleOn();
        active();
        new FadeInLeftTransition(btMahasiswa).play();
        new FadeInLeftTransition(btDosen).play();
        new FadeInLeftTransition(mhsIcon).play();
        new FadeInLeftTransition(dosenIcon).play();
        new FadeInLeftTransition(hideIcon).play();
        new FadeInLeftTransition(btAbout).play();
        new FadeInLeftTransition(codeIcon).play();
    }

    //animasi tombol disembunyikan ke sisi kiri
    public void hide() {
        inActive();
        new FadeOutLeftTransition(btMahasiswa).play();
        new FadeOutLeftTransition(btDosen).play();
        new FadeOutLeftTransition(mhsIcon).play();
        new FadeOutLeftTransition(dosenIcon).play();
        new FadeOutLeftTransition(hideIcon).play();
        new FadeOutLeftTransition(btAbout).play();
        new FadeOutLeftTransition(codeIcon).play();
    }

    //membuat tombol ridak aktif setelah di hide
    public void inActive() {
        codeIcon.setDisable(true);
        btAbout.setDisable(true);
        btDosen.setDisable(true);
        btMahasiswa.setDisable(true);
        mhsIcon.setDisable(true);
        dosenIcon.setDisable(true);
        hideIcon.setDisable(true);
    }

    //membuat tombol aktif setelah di show
    public void active() {
        codeIcon.setDisable(false);
        btAbout.setDisable(false);
        btDosen.setDisable(false);
        btMahasiswa.setDisable(false);
        mhsIcon.setDisable(false);
        dosenIcon.setDisable(false);
        hideIcon.setDisable(false);
    }

    @FXML
    private void hideAction(MouseEvent event) {
        this.hide();
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void closeMouse(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void aboutAction(ActionEvent event) {
        //load tampilan About.fxml
        con.loadAnchorPane(paneView, "/about/About.fxml");
    }

    @FXML
    private void aboutMouse(MouseEvent event) {
        //load tampilan About.fxml
        con.loadAnchorPane(paneView, "/about/About.fxml");
    }

}
