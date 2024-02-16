package org.code;

public class Manager {
    Warehouse warehouse;
    public Manager(Warehouse warehouse){
        this.warehouse = warehouse;
    }
    boolean shouldOrderItem(String itemName){
        int itemQty = warehouse.getItemQty(itemName);
        return itemQty<50;
    }

    boolean canStoreTruck(int incomingQty){
        int currentCapacity = 0;
        for(String item:warehouse.itemName){
            currentCapacity += warehouse.getItemQty(item);
        }

        return (currentCapacity+incomingQty<200);
    }
}
