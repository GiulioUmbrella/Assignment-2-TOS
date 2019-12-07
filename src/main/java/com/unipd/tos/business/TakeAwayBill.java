////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos.business;

import java.util.List;

import com.unipd.tos.model.MenuItem;

public interface TakeAwayBill {
	double getOrderPrice(List<MenuItem> itemsOrdered);
}
