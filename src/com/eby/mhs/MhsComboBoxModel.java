/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.mhs;

import com.eby.orm.entity.Dosen;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 3b1
 */
public class MhsComboBoxModel {

    private List<Dosen> list;
    private ObservableList<String> items;

    public MhsComboBoxModel() {
        list = new ArrayList<Dosen>();
        items = FXCollections.observableArrayList(new ArrayList<String>());
    }

    public void add(List<Dosen> get) {
        for (Dosen c : get) {
            list.add(c);
            items.add(c.getNama());
        }
    }

    public Dosen get(int i) {
        return list.get(i);
    }

    public synchronized Dosen remove(int index) {
        Dosen t = list.remove(index);
        return t;
    }
    
     public void removeAllElement(){
        int size = list.size();
        for (int i=0; i<size; i++){
            list.remove(i);
        }
//        this.initColumn();
        
    }
     
    public ObservableList<String> getItems() {
        return items;
    }

    
    

}
