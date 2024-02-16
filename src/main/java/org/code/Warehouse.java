package org.code;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    int itemId;
    ArrayList<String> itemName;
    public Warehouse(){
        itemId = 0;
        itemName=new ArrayList<>();
    }

    int getItemQty(String itemName){
        //db call to get item id for item name and itemqty
        return 5;
    }

    int getCurrentCapacity(){
        int itemQty = 0;
        for(String item:itemName){
            itemQty += getItemQty(item);
        }
        return itemQty;
    }
}
