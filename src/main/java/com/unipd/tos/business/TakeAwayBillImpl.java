////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos.business;

import java.util.List;

import com.unipd.tos.exception.TakeAwayBillException;
import com.unipd.tos.model.MenuItem;

public class TakeAwayBillImpl implements TakeAwayBill {
  
  private double orderPrice = 0;	
	
	
  @Override
  public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
  
  if (itemsOrdered == null){
   throw new TakeAwayBillException("Hai inserito una lista nulla");
  }  
	  
  
  if(itemsOrdered.size() == 0)
	  return 0;
  
  
  for(int i = 0; i < itemsOrdered.size(); i++ )
	  orderPrice += itemsOrdered.get(i).getPrice();
  
  
 return orderPrice;
 }
}