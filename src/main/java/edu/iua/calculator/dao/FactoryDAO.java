package edu.iua.calculator.dao;

public class FactoryDAO{

	private static FactoryDAO instance = null;
	
	private FactoryDAO() {
		
	}
	
	public static FactoryDAO getInstance() {
		if (instance == null)
			instance = new FactoryDAO();
		
		return instance;
	}
	
	private static String dataBaseActive = "MYSQL";
	
	public static IGenericDAO getBillingsDAO() {
		if(dataBaseActive == "MYSQL") {
			return BillingsDAO.getInstance();
		}
		/*
		else{
			return BillingOracleDAO.getInstance();
		}
		 */
		return null;
	}
}
