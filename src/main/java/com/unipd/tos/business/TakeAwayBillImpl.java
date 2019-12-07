////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos.business;

import java.util.List;

import com.unipd.tos.exception.TakeAwayBillException;
import com.unipd.tos.model.MenuItem;

public class TakeAwayBillImpl implements TakeAwayBill {
  @Override
  public double getOrderPrice(List<MenuItem> itemsOrdered) throws TakeAwayBillException {
  // TODO Auto-generated method stub
  if (itemsOrdered == null){
   throw new TakeAwayBillException("Hai inserito una lista nulla");
  } 
	  
	  
	  
 return 0;
 }
}