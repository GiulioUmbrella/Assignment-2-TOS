////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.unipd.tos.business.*;
import com.unipd.tos.exception.TakeAwayBillException;
import com.unipd.tos.model.MenuItem;
import com.unipd.tos.model.ItemType;
import com.unipd.tos.model.MenuItemImpl;



public class TakeAwayBillTest {	


 TakeAwayBill tb = new TakeAwayBillImpl();
 List<MenuItem> mil = new ArrayList<MenuItem>(); 

 private void addElementsToMenu(ItemType item, String name, double price, int n) {
  for(int i = 0; i < n; i++)
  mil.add(new MenuItemImpl(item,name, price));
 }
  
 @Before 
 public void setUp() {
  mil = new ArrayList<MenuItem>();
  tb = new TakeAwayBillImpl();
 }
 
	
 @Test
public void testNullMenu() {
 try {
 assertEquals(0, tb.getOrderPrice(null) ,0);
 } catch (TakeAwayBillException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }
}
 
@Test 
public void testEmptyMenu() throws TakeAwayBillException {
 assertEquals(0.0,tb.getOrderPrice(mil),0);
}
 
@Test
public void testSingoloPaninoMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Panini,"Primavera", 5.0, 1);	
	assertEquals(5,tb.getOrderPrice(mil),0);
	
}
 
@Test
public void testSingoloFrittiMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Fritti,"Arancini", 5.0, 1);	
	assertEquals(5,tb.getOrderPrice(mil),0);
	
}

@Test
public void testSingolaBibitaMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Bevande,"Chinotto", 5.0, 1);	
	assertEquals(5,tb.getOrderPrice(mil),0);
	
}

@Test
public void testDuePaniniMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Panini,"Primavera", 5.0, 2);	
	assertEquals(10,tb.getOrderPrice(mil),0);
	
}

@Test
public void testMenuCompleto() throws TakeAwayBillException{
	
 addElementsToMenu(ItemType.Panini, "Primavera", 5.0, 1);
 addElementsToMenu(ItemType.Fritti, "Arancini",  4.5, 1);
 addElementsToMenu(ItemType.Bevande,"Chinotto",  2.0, 1);
				
 assertEquals(11.5,tb.getOrderPrice(mil),0);
}

@Test
public void testMenuCompletoOrdineInverso() throws TakeAwayBillException{
	
 addElementsToMenu(ItemType.Bevande,"Chinotto",  2.0, 1);
 addElementsToMenu(ItemType.Fritti, "Arancini",  4.5, 1);
 addElementsToMenu(ItemType.Panini, "Primavera", 5.0, 1);
 				
 assertEquals(11.5,tb.getOrderPrice(mil),0);
}

@Test
public void testScontoPaninoMenoCaro() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Primavera",   5.0, 4);
 addElementsToMenu(ItemType.Panini, "Vegetariano", 2.0, 1); 
 
 assertEquals(21.0,tb.getOrderPrice(mil),0);
}

@Test
public void testScontoPaninoMenoCaroPrimo() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Vegetariano", 2.0, 1);  
 addElementsToMenu(ItemType.Panini, "Primavera",   5.0, 4);
	   
 assertEquals(21.0,tb.getOrderPrice(mil),0);
}

@Test
public void testScontoPaninoMenoCaroInMezzo() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Primavera",   5.0, 2);
 addElementsToMenu(ItemType.Panini, "Vegetariano", 2.0, 1);
 addElementsToMenu(ItemType.Panini, "Primavera",   5.0, 2);
  
 assertEquals(21.0,tb.getOrderPrice(mil),0);
}

}