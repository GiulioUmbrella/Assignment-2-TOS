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
 
	
 @Test(expected=TakeAwayBillException.class)
public void testNullMenu() throws TakeAwayBillException {
 tb.getOrderPrice(null);
}
 
@Test 
public void testEmptyMenu() throws TakeAwayBillException {
 assertEquals(0.0,tb.getOrderPrice(mil),0);
}
 
@Test
public void testSingoloPaninoMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Panini,"Primavera", 10.0, 1);	
	assertEquals(10.0,tb.getOrderPrice(mil),0);
	
}
 
@Test
public void testSingoloFrittiMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Fritti,"Arancini", 10.0, 1);	
	assertEquals(10.0,tb.getOrderPrice(mil),0);
	
}

@Test
public void testSingolaBibitaMenu() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Bevande,"Chinotto", 10.0, 1);	
	assertEquals(10.0,tb.getOrderPrice(mil),0);
	
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

@Test
public void testScontoPaninoMenoDopoAltreOrdinazioni() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Fritti,  "Patatine",    1.0, 1);
 addElementsToMenu(ItemType.Bevande, "Chinotto",    1.0, 1);
 addElementsToMenu(ItemType.Panini,  "Vegetariano", 2.0, 1);  
 addElementsToMenu(ItemType.Panini,  "Primavera",   5.0, 4);
 assertEquals(23.0,tb.getOrderPrice(mil),0);
}

@Test
public void testScontoTotalePaniniFritti() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Fritti,  "Patatine",    20.0, 2);
 addElementsToMenu(ItemType.Panini,  "Vegetariano", 5.0, 4);  
 assertEquals(54.0,tb.getOrderPrice(mil),0);
}

@Test
public void testNoScontoTotalePaniniFritti() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Fritti,  "Patatine",    10.0, 2);
 addElementsToMenu(ItemType.Panini,  "Vegetariano", 5.0, 4);  
 assertEquals(40.0,tb.getOrderPrice(mil),0);
}


@Test
public void testPrezzoPienoBibite() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Bevande,  "Chinotto", 3.0, 4);
 assertEquals(12.0,tb.getOrderPrice(mil),0);
}

@Test
public void testScontoTotalePaniniFrittiBibiteInMezzo() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Bevande,  "Chinotto",    1.0,  1);
 addElementsToMenu(ItemType.Fritti,   "Patatine",    20.0, 2);
 addElementsToMenu(ItemType.Bevande,  "Chinotto",    1.0,  1);
 addElementsToMenu(ItemType.Panini,   "Vegetariano", 5.0,  4);
 addElementsToMenu(ItemType.Bevande,  "Chinotto",    1.0,  1);
 assertEquals(56.7,tb.getOrderPrice(mil),0);
}

@Test
public void testNessunoPaniniFrittiBibiteInMezzo() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Bevande,  "Chinotto",    1.0,  1);
 addElementsToMenu(ItemType.Fritti,   "Patatine",    10.0, 2);
 addElementsToMenu(ItemType.Bevande,  "Chinotto",    1.0,  1);
 addElementsToMenu(ItemType.Panini,   "Vegetariano", 5.0,  4);
 addElementsToMenu(ItemType.Bevande,  "Chinotto",    1.0,  1);
 assertEquals(43.0,tb.getOrderPrice(mil),0);
}

@Test
public void  testRincaroOrdinePiccolo() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini,   "Vegetariano", 4.0,  2);
 assertEquals(8.5,tb.getOrderPrice(mil),0);

}
@Test
public void testSingoloPaninoMenuRincaro() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Panini,"Primavera", 5.0, 1);	
	assertEquals(5.5,tb.getOrderPrice(mil),0);
}
 
@Test
public void testMenuCompletoRincaro() throws TakeAwayBillException {
	addElementsToMenu(ItemType.Panini, "Primavera", 3.0, 1);	
	addElementsToMenu(ItemType.Fritti, "Arancini",  3.0, 1);
	addElementsToMenu(ItemType.Bevande,"Chinotto",  3.0, 1);
	assertEquals(9.5,tb.getOrderPrice(mil),0);
}

@Test(expected=TakeAwayBillException.class)
public void testPrimoValoreOltreNumeroMassimoDiElementi() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Primavera", 3.0, 31); 
 tb.getOrderPrice(mil);	
}

@Test
public void testSogliaNumeroMassimoDiElementi() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Primavera", 3.0, 30); 
 tb.getOrderPrice(mil);	
}

@Test
public void testQuasiSogliaNumeroMassimoDiElementi() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Primavera", 3.0, 29); 
 tb.getOrderPrice(mil);	
}

@Test(expected=TakeAwayBillException.class)
public void testSecondoValoreOltreNumeroMassimoDiElementi() throws TakeAwayBillException {
 addElementsToMenu(ItemType.Panini, "Primavera", 3.0, 32); 
 tb.getOrderPrice(mil);	
}


}