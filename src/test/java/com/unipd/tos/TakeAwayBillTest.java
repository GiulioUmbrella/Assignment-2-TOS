////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.unipd.tos.business.*;
import com.unipd.tos.exception.TakeAwayBillException;
import com.unipd.tos.model.MenuItem;
import com.unipd.tos.model.ItemType;
import com.unipd.tos.model.MenuItemImpl;


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
 
@Test 
public void testEmptyMenu() throws TakeAwayBillException {
 TakeAwayBill tb = new TakeAwayBillImpl();
 List<MenuItem> mil = new ArrayList<MenuItem>();
 assertEquals(0.0,tb.getOrderPrice(mil),0);
}
 
@Test
public void testSingoloPaninoMenu() throws TakeAwayBillException {
	TakeAwayBill tb = new TakeAwayBillImpl();
	MenuItem mi = new MenuItemImpl(ItemType.Panini,"Primavera", 5.0);
	List<MenuItem> mil = new ArrayList<MenuItem>(); 
	mil.add(mi);	
	assertEquals(5,tb.getOrderPrice(mil),0);
	
}
 
 
 

}