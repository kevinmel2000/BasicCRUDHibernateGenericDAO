/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.frameworkConfig;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Herudi
 */
public class Config {

    public Config() {
    }

    public static void dialog(Alert.AlertType alertType, String s, String h) {
        Alert alert = new Alert(alertType, s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(h);
        alert.showAndWait();
    }

    public void newStage(Stage stage, Label lb, String load, String judul, boolean resize, StageStyle style, boolean maximized) {
        try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            st.setScene(scene);
            st.show();
//            stage.close();
        } catch (Exception e) {
        }
    }

    public void newStage2(Stage stage, Button lb, String load, String judul, boolean resize, StageStyle style, boolean maximized) {
        try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            st.setScene(scene);
            st.show();
            stage.close();
        } catch (Exception e) {
        }
    }

    public void loadAnchorPane(AnchorPane ap, String a) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/com/eby" + a));
            ap.getChildren().setAll(p);
        } catch (IOException e) {
        }
    }

    public static void setModelColumn(TableColumn tb, String a) {
        tb.setCellValueFactory(new PropertyValueFactory(a));
    }

    public void loadAnchorPaneHelper(AnchorPane ap) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/com/eby/view/BukuAdd.fxml"));
            ap.getChildren().setAll(p);
        } catch (IOException e) {
        }
    }

}
