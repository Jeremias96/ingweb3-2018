package edu.iua.calculator.business;

import edu.iua.calculator.dao.FactoryDAO;
import edu.iua.calculator.model.*;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 * Taxes Calculator returns a calculated total amount
 * based on taxes percentages
 */
public class TaxesCalculator
{

    //log4j
    final static Logger logger = LogManager.getLogger("TaxesCalculator.class");

    //log4j-core
    //final static Logger logger2 = (Logger) LogManager.getLogger(TaxesCalculator.class);


    public static void main( String[] args )
    {



        try {

            System.out.print( "Enter amount value: " );

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader (isr);

            /*float amount = Float.parseFloat(br.readLine());
            HashMap<String, Float> calculatedTax = calculate(amount);

            Integer createdBill = saveBill(amount);
            if (createdBill != null ){
                System.out.println( "Bill with amount: "+ amount + " and id: " + createdBill + " has been created" );
                logger.info("Bill with amount: "+ amount + " and id: " + createdBill + " has been created");
            }*/

            /*System.out.print( "Amount needs to be updated because it doesn't contains taxes." );
            System.out.print( "Check value in database and then press Enter." );

            br.readLine();


            float totalAmount = amount;
            for (String tax: calculatedTax.keySet()){
                totalAmount += calculatedTax.get(tax);
            }

            Billings updatedBilling = updateBill(createdBill, totalAmount);
            if (updatedBilling != null ){
                System.out.println( "Billing with id: "+ createdBill + " has been updated to set this new amount: " + totalAmount );
                logger.info("Billing with id: "+ createdBill + " has been updated to set this new amount: " + totalAmount);
            }

            System.out.print( "Check value in database and then press Enter." );

            br.readLine();*/

            System.out.println( "Get Bill!");
            getBill(10);


            /*System.out.print( "Deleting billing..." );
            deleteBill(createdBill);
            System.out.print( "- Billing has been deleted." );*/



            //Billings billingInfo = new Billings();
            //int clientId = billingInfo.saveClient();
            //System.out.println( "Client has been created with id: "+ clientId );


            getClients();

            getClientByName("Pablo");

            /*System.out.print( "Enter low end to search: " );
            float min = Float.parseFloat(br.readLine());
            
            System.out.print( "Enter high end to search: " );
            float max = Float.parseFloat(br.readLine());
            
            getBillingsBetween(min, max);
            
            getClientByIdentificationType(1);*/
            
            isr.close();

        } catch (IOException e) {
            System.out.println( "Sorry, an error has occurred. Please try again!" );
            logger.error("Sorry, an error has occurred. Please try again!", e);
        } catch (NumberFormatException e) {
            System.out.println( "Invalid amount value. Please try again!" );
            logger.error("Invalid amount value. Please try again!", e);
        }

    }


    public static HashMap<String, Float> calculate(float amount) {

        HashMap<String, Float> calculatedTaxesAmount = new HashMap<String, Float>();

        Taxes taxes = new Taxes();
        HashMap<String, Float> taxesPercentages = getTaxesPercentage();

        System.out.println("Applicable taxes:");
        float totalAmount = amount;
        for (String tax : taxesPercentages.keySet()) {
            float taxValue = taxesPercentages.get(tax);
            float applicableTax = amount * taxValue;
            calculatedTaxesAmount.put(tax, applicableTax);
            System.out.println("TAX: " + tax + " = " + applicableTax);
            totalAmount = totalAmount + applicableTax;
        }

        System.out.println("\nTOTAL Amount: " + totalAmount);


        return calculatedTaxesAmount;
    }


    //SELECT
    private static HashMap<String, Float> getTaxesPercentage() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        HashMap<String, Float> taxesPercentage = new HashMap<String, Float>();


        try {
            tx = session.beginTransaction();
            List taxes = session.createQuery("FROM Taxes").list();
            for (Iterator iterator = taxes.iterator(); iterator.hasNext();){
                Taxes tax = (Taxes) iterator.next();
                taxesPercentage.put(tax.getTaxName(), tax.getTaxPercentage());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return taxesPercentage;

    }

    //SAVE
    private static int saveBill(float amount){
        //TODO
    	return 0;
    }

    //UPDATE
    private static Billings updateBill(int billingId, float totalAmount){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        Billings bill = null;
        try {
            tx = session.beginTransaction();

            bill = session.get(Billings.class, billingId);
            bill.setAmount(totalAmount);
            session.update(bill);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return bill;
    }

    //DELETE
    private static void deleteBill(int billingId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Billings bill = session.get(Billings.class, billingId);
            session.delete(bill);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //findById
    public static Billings getBill(int billingId){    	
    	Billings billing = (Billings) FactoryDAO.getInstance().getBillingsDAO().findById(billingId);
    	
    	System.out.println("BILLING: " + billing.getId() + " AMOUNT: " + billing.getAmount());
    	
    	return billing;
    }



    private static void getClients(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;

        try {

            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();


            CriteriaQuery<Clients> query = builder.createQuery(Clients.class);
            Root<Clients> from = query.from(Clients.class);

            query.select(from);
            List<Clients> resultListClients = session.createQuery(query).getResultList();
            System.out.println("CLIENTS LIST : " + resultListClients.toString());


            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void getClientByName(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;

        try {

            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();


            CriteriaQuery<Clients> query = builder.createQuery(Clients.class);
            Root<Clients> from = query.from(Clients.class);

            query.select(from).where(builder.equal(from.get("name"), name));
            List<Clients> resultListClients = session.createQuery(query).getResultList();
            System.out.println("CLIENTS LIST : " + resultListClients.toString());


            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    public static List<Billings> getBillingsBetween(Float min, Float max) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        List<Billings> resultListBillings = null;

        try {

            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Billings> query = builder.createQuery(Billings.class);
            Root<Billings> from = query.from(Billings.class);

            query.select(from).where(builder.between(from.get("amount").as(Float.class), min, max));
            resultListBillings = session.createQuery(query).getResultList();
            System.out.println("BILLINGS LIST : " + resultListBillings.toString());


            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultListBillings;
    }
    
    
    public static List<Clients> getClientByIdentificationType(int identId) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx;
        List<Clients> resultListClients = null;

        try {

            tx = session.beginTransaction();

            session.flush();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Clients> query = builder.createQuery(Clients.class);
            Root<Clients> client = query.from(Clients.class);
            
            Join<Clients, IdentificationType> identType = client.join("identificationType", JoinType.INNER);
            query.select(client).where(builder.equal(identType.get("identificationId"), identId));
            
            resultListClients = session.createQuery(query).getResultList();
            System.out.println("CLIENTS LIST : " + resultListClients.toString());

            tx.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return resultListClients;
    }
}