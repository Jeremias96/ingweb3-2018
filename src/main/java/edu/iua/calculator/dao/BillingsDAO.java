package edu.iua.calculator.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.iua.calculator.model.Billings;
import edu.iua.calculator.model.Clients;
import edu.iua.calculator.model.HibernateUtil;

public class BillingsDAO implements IGenericDAO<Billings, Integer>{

	private static BillingsDAO instance = null;
	
	private BillingsDAO() {
		
	}
	
	public static BillingsDAO getInstance() {
		if (instance == null)
			instance = new BillingsDAO();
		
		return instance;		
	}
	
	public int save(Billings bill) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Integer billId = null;

        try {
            tx = session.beginTransaction();
            
            billId = (Integer) session.save(bill);

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return billId;
	}

	public Billings findById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        Billings billing = null;


        try {

            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Billings> query = builder.createQuery(Billings.class);
            Root<Billings> from = query.from(Billings.class);
            query.select(from).where(builder.equal(from.get("id"), id));
            billing = session.createQuery(query).getSingleResult();
            
            /*
            
            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();


            CriteriaQuery<Clients> query = builder.createQuery(Clients.class);
            Root<Clients> from = query.from(Clients.class);

            query.select(from).where(builder.equal(from.get("name"), name));
            List<Clients> resultListClients = session.createQuery(query).getResultList();
            System.out.println("CLIENTS LIST : " + resultListClients.toString());
            
             */

            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }		
		
		return billing;
	}

	public List<Billings> findAll() {
		return null;
	}

}
