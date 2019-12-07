////////////////////////////////////////////////////////////////////
// Giulio Umbrella 1170486
////////////////////////////////////////////////////////////////////
package com.unipd.tos.model;

import com.unipd.tos.model.ItemType;
import com.unipd.tos.model.MenuItem;

public class MenuItemImpl implements MenuItem {

private ItemType type;

private String name;

private double price;

public MenuItemImpl(ItemType type, String name, double price){
this.type = type;
this.name = name;
this.price = price;
}

@Override
public double getPrice() {
return price;
}


@Override
public ItemType getType() {
// TODO Auto-generated method stub
return type;
}

}