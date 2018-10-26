package edu.iua.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.iua.calculator.business.TaxesCalculator;
import edu.iua.calculator.model.Billings;
import edu.iua.calculator.model.Clients;

public class test2 {
	
	@Test
	   void getAlgo() {
		   assertEquals(1, 1);
	   }
	
	static HashMap<String, String> hashMapExpected;
	   @BeforeAll
	   static void setUp(){
	       hashMapExpected = new HashMap<String, String>();
	       hashMapExpected.put("name_1", "Jose");
	       hashMapExpected.put("last_name_1", "Lopez");
	       hashMapExpected.put("name_2", "Juan");
	       hashMapExpected.put("last_name_2", "Jose");
	       hashMapExpected.put("name_3", "Jeremias");
	       hashMapExpected.put("last_name_3", "Leiton");
	       hashMapExpected.put("amount_1", "1500");
	   }
	   
	   @Test
	    void getClientsByIdentificationTypeSuccess() {
	        List<Clients> resultList = TaxesCalculator.getClientByIdentificationType(1);

	        assertEquals(resultList.get(0).getName(), hashMapExpected.get("name_1"));
	        assertEquals(resultList.get(0).getLastName(), hashMapExpected.get("last_name_1"));
	        assertEquals(resultList.get(1).getName(), hashMapExpected.get("name_2"));
	        assertEquals(resultList.get(1).getLastName(), hashMapExpected.get("last_name_2"));
	        assertEquals(resultList.get(2).getName(), hashMapExpected.get("name_3"));
	        assertEquals(resultList.get(2).getLastName(), hashMapExpected.get("last_name_3"));
	    }

	    @Test
	    void getClientsByIdentificationTypeFail() {
	        List<Clients> resultList = TaxesCalculator.getClientByIdentificationType(1);
	        
	        HashMap<String, String> hashMapExpected = new HashMap<String, String>();
	        hashMapExpected.put("name_1", "Pepe");
	        hashMapExpected.put("last_name_1", "Argento");

	        assertNotEquals(resultList.get(0).getName(), hashMapExpected.get("name_1"));
	        assertNotEquals(resultList.get(0).getLastName(), hashMapExpected.get("last_name_1"));

	    }
	    
	    @Test
	    void getBillingsBetweenSuccess() {
	    	List<Billings> resultList = TaxesCalculator.getBillingsBetween(500f, 1500f);
	    	
	    	//assert(resultList.get(0).getAmount(), );
	    }
	    
	    @Test
	    void getBillSuccess() {
	    	Billings resultBilling = TaxesCalculator.getBill(1);
	    	Float expectedFloat = Float.parseFloat("1500");
	    	
	    	assertEquals(resultBilling.getAmount(), expectedFloat);
	    }

}
