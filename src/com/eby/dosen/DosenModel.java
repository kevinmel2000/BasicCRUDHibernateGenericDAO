/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.dosen;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Dosen;
import java.util.List;

/**
 *
 * @author eby
 */
public class DosenModel {

    //Membuat object baru
    private DosenViewController controller;
    private final GenericDAO dao;

    public DosenModel() {
        //inisialisasi nilai dati object
        dao = new GenericDAO();
    }

    public DosenViewController getController() {
        return controller;
    }

    public void setController(DosenViewController controller) {
        this.controller = controller;
    }

    //Membuat methode untuk proses tampil data
    public List<Dosen> list() {
        return dao.getAll(Dosen.class);
    }

    //Membuat methode untuk proses simpan data
    public void save(Dosen dosen) {
        dao.save(dosen);
    }

    //Membuat methode untuk proses update data
    public void update(Dosen dosen) {
        dao.update(dosen);
    }

    //Membuat methode untuk proses delete data
    public void delete(Dosen dosen) {
        dao.delete(dosen);
    }

    //Membuat methode untuk proses pencarian data
    public List<Dosen> find(String key) {
        return dao.findData("nama", key, Dosen.class);
    }

}
