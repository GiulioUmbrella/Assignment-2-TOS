////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.unipd.tos.business.*;
import com.unipd.tos.exception.TakeAwayBillException;


public class TakeAwayBillTest {	

 @Test
 public void testNullMenu() {
  TakeAwayBill tb = new TakeAwayBillImpl();
  try {
	assertEquals(0, tb.getOrderPrice(null) ,0);
} catch (TakeAwayBillException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
 
 
 
 
 
 

}