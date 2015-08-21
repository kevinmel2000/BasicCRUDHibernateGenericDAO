/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.about;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutRightTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class AboutController implements Initializable {
    
    @FXML
    private AnchorPane paneView;
    @FXML
    private Text txtThank;
    @FXML
    private Text txtFind;
    @FXML
    private Button btFb;
    @FXML
    private FontAwesomeIconView fbIcon;
    @FXML
    private Button btGit;
    @FXML
    private FontAwesomeIconView gitIcon;
    @FXML
    private Text txtVisit;
    @FXML
    private Button btWpe;
    @FXML
    private FontAwesomeIconView wpeIcon;
    @FXML
    private Button btWpl;
    @FXML
    private FontAwesomeIconView wplIcon;
    @FXML
    private Text txtClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeIn();
    }
    
    @FXML
    private void fbAction(ActionEvent event) {
        //Memanggil methode static dan memasukkan link yang ingin dibuka ketika button di klik
        About.browse("https://www.facebook.com/eby.j.barcelonista.bee.vhaa");
    }
    
    @FXML
    private void gitAction(ActionEvent event) {
        //Memanggil methode static dan memasukkan link yang ingin dibuka ketika button di klik
        About.browse("https://github.com/eby73");
    }
    
    @FXML
    private void wpeAction(ActionEvent event) {
        //Memanggil methode static dan memasukkan link yang ingin dibuka ketika button di klik
        About.browse("https://eby007.wordpress.com/");
    }
    
    @FXML
    private void wplAction(ActionEvent event) {
        //Memanggil methode static dan memasukkan link yang ingin dibuka ketika button di klik
        About.browse("http://lifecode.co.id/");
    }
    
    public void fadeIn() {
        //Animasi file masuk melalui sisi kiri
        new FadeInLeftTransition(btFb).play();
        new FadeInLeftTransition(fbIcon).play();
        new FadeInLeftTransition(txtThank).play();
        new FadeInLeftTransition(txtFind).play();
        new FadeInLeftTransition(txtVisit).play();
        new FadeInLeftTransition(btGit).play();
        new FadeInLeftTransition(gitIcon).play();
        new FadeInLeftTransition(btWpe).play();
        new FadeInLeftTransition(wpeIcon).play();
        new FadeInLeftTransition(btWpl).play();
        new FadeInLeftTransition(wplIcon).play();
        new FadeInLeftTransition(txtClose).play();
    }
    
    public void fadeOut() {
        //Animasi file keluar melalui sisi kanan
        new FadeOutRightTransition(btFb).play();
        new FadeOutRightTransition(fbIcon).play();
        new FadeOutRightTransition(txtThank).play();
        new FadeOutRightTransition(txtFind).play();
        new FadeOutRightTransition(txtVisit).play();
        new FadeOutRightTransition(btGit).play();
        new FadeOutRightTransition(gitIcon).play();
        new FadeOutRightTransition(btWpe).play();
        new FadeOutRightTransition(wpeIcon).play();
        new FadeOutRightTransition(btWpl).play();
        new FadeOutRightTransition(wplIcon).play();
        new FadeOutRightTransition(paneView).play();
        new FadeOutRightTransition(txtClose).play();
    }
    
    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }
    
}
