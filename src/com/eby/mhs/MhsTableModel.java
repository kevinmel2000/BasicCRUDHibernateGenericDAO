/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.mhs;

import com.eby.orm.entity.Dosen;
import com.eby.orm.entity.Mahasiswa;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 3b1
 */
public final class MhsTableModel {
    
    private final List<Mahasiswa> list;
    private final ObservableList<Mahasiswa> row;
    private final Collection<TableColumn<Mahasiswa, String>> column;
    
    public MhsTableModel() {
        list = new ArrayList<>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<>();
        this.initColumn();
        
    }
    
    public void initColumn() {
        column.add(this.addTableColumn1("NIM", "nim"));
        column.add(this.addTableColumn2("NAMA", "nama"));
        column.add(this.addTableColumn3("KELAS", "kelas"));
        column.add(this.addTableColumn4("ALAMAT", "alamat"));
        column.add(this.addTableColumn5("Tgl. LAHIR", "tglLahir"));
        column.add(this.getDosen());

//        column.add(this.getMahasiswa());
    }
    
    public void removeAllColumn() {
        while (column.iterator().hasNext()) {
            column.remove(column.iterator().next());
        }
    }
    
    private TableColumn addTableColumn1(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(100);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumn2(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(125);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumn3(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(60);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumn4(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(100);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumn5(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(100);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    
    private TableColumn getDosen() {
        TableColumn t = this.addTableColumn2("DOSEN", "dosen");
        t.setCellFactory(new Callback< TableColumn<Dosen, Dosen>, TableCell<Dosen, Dosen>>() {
            
            @Override
            public TableCell<Dosen, Dosen> call(TableColumn<Dosen, Dosen> param) {
                TableCell<Dosen, Dosen> parentCell = new TableCell<Dosen, Dosen>() {
                    
                    @Override
                    protected void updateItem(Dosen cat, boolean empty) {
                        super.updateItem(cat, empty);
                        if (cat != null) {
                            Label label = new Label(cat.getNama());
                            setGraphic(label);
                        }else{
                            setGraphic(null);
                        }
                    }
                };
                return parentCell;
            }
        ;
        });
         
         return t;
    }
    
    public Collection<TableColumn<Mahasiswa, String>> getAllColumn() {
        return column;
    }
    
    public ObservableList<Mahasiswa> getItem() {
        return row;
    }
    
    public void remove(int index) {
        row.remove(index);
        this.removeAllColumn();
        this.initColumn();
    }
    
    public void removeAllElement() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.remove(i);
        }
        
        this.removeAllColumn();
    }
    
}
