////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos.business;

import java.util.List;

import com.unipd.tos.exception.TakeAwayBillException;
import com.unipd.tos.model.MenuItem;

import com.unipd.tos.model.ItemType;

public class TakeAwayBillImpl implements TakeAwayBill {
  
  private double orderPrice = 0;	
  private double orderDiscount = 0;	
	
  @Override
  public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
  
  if (itemsOrdered == null){
   throw new TakeAwayBillException("Hai inserito una lista nulla");
  }  	  
  
  if(itemsOrdered.size() == 0)
	  return 0;
  
  if(itemsOrdered.size() > 30)
   throw new TakeAwayBillException("Troppi elementi nel menu");
  
  
  for(int i = 0; i < itemsOrdered.size(); i++ )
	  orderPrice += itemsOrdered.get(i).getPrice();
    
  if(menuHaAlmenoNPanini(itemsOrdered , 5)) 
 	 orderDiscount += calcolaScontoPaninoMenoCostoso(itemsOrdered);
  
  if(importoPaniniFrittiSuperaSoglia(itemsOrdered,50.0)) 
    orderDiscount += orderPrice*0.10;
  
  if(orderPrice < 10)
   orderPrice += 0.5;
  
  return orderPrice-orderDiscount;
 }

private boolean importoPaniniFrittiSuperaSoglia(List<MenuItem> itemsOrdered, double soglia) {
 double importoPaniniFritti = 0;
 for(MenuItem mi : itemsOrdered ) {
  if(mi.getType() != ItemType.Bevande)
   importoPaniniFritti += mi.getPrice();	
  }

return (importoPaniniFritti > soglia) ? true : false;
}

 private boolean menuHaAlmenoNPanini(List<MenuItem> itemsOrdered, int numPanini) {

   int i=0, contatorePanini = 0;	
   boolean almenoNPanini = false;

   while( !almenoNPanini && i < itemsOrdered.size() ) {
	
    if(itemsOrdered.get(i).getType() == ItemType.Panini)
     contatorePanini++;
	
    if(contatorePanini == numPanini)
     almenoNPanini = true;
		
    i++;	 
   }

  return almenoNPanini;
 }
  
 private double calcolaScontoPaninoMenoCostoso(List<MenuItem> itemsOrdered) {
  boolean trovatoPanino = false;

  int i = 0;
  while(!trovatoPanino) {
   if(itemsOrdered.get(i).getType()==ItemType.Panini)
    trovatoPanino = true;
   else
    i++;
  }

  double valueMin = itemsOrdered.get(i).getPrice();
	
  for(; i < itemsOrdered.size(); i++) {
   ItemType itype = itemsOrdered.get(i).getType();
   double price = itemsOrdered.get(i).getPrice();

   if(itype ==ItemType.Panini && price < valueMin)
    valueMin = price;
  }
	
  return valueMin/2;

 }


}