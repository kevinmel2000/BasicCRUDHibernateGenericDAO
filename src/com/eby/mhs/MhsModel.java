/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.mhs;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Dosen;
import com.eby.orm.entity.Mahasiswa;
import java.util.List;

/**
 *
 * @author eby
 */
public class MhsModel {

    private MhsViewController controller;
    private GenericDAO dao;

    public MhsModel() {
        dao = new GenericDAO();
    }

    public MhsViewController getController() {
        return controller;
    }

    public void setController(MhsViewController controller) {
        this.controller = controller;
    }

    public List<Mahasiswa> list() {
        return dao.getAll(Mahasiswa.class);
    }

    public List<Dosen> listDosen() {
        return dao.getAll(Dosen.class);
    }

    public void save(Mahasiswa m) {
        dao.save(m);
    }

    public void update(Mahasiswa m) {
        dao.update(m);
    }

    public void delete(Mahasiswa m) {
        dao.delete(m);
    }

    public List<Mahasiswa> findData(String key) {
        return dao.findData("nama", key, Mahasiswa.class);
    }
}
