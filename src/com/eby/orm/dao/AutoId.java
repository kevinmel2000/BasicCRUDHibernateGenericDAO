/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.orm.dao;

import com.eby.orm.entity.Dosen;
import com.eby.orm.utils.HibernateUtils;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author eby
 */
public class AutoId {

    public void autoId(Dosen d) {
        try {
            Session session = HibernateUtils.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Criteria cr = session.createCriteria(Dosen.class);
            cr.setProjection(Projections.max("nip"));
            Object obj = cr.uniqueResult();
            String auto = "" + (Integer.parseInt(obj.toString().substring(2)) + 1);
            String nol = "";
            if (auto.length() == 1) {
                nol = "00";
            } else if (auto.length() == 2) {
                nol = "0";
            } else if (auto.length() == 3) {
                nol = "";
            }
            d.setNip(Integer.valueOf("1910" + nol + auto));
        } catch (NumberFormatException | HibernateException e) {
        }
    }
}
