package org.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManagerTest {
    Manager operationManager;

    @Mock
    Warehouse warehouse;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldOrderItemTest(){
        System.out.println("-----Test shouldOrderItem executed -------------");

        operationManager = new Manager(warehouse);
        when(warehouse.getItemQty("some name")).thenReturn(49);
        boolean shouldOrder = operationManager.shouldOrderItem("some name");
        assertTrue(shouldOrder);

        when(warehouse.getItemQty("some name")).thenReturn(51);
        shouldOrder = operationManager.shouldOrderItem("some name");
        assertFalse(shouldOrder);
    }

    @Test
    public void canStoreTruckTest(){
        System.out.println("------- Test canStoreTruck executed--------");
        warehouse.itemName = new ArrayList<>();
        warehouse.itemName.add("item 1");
        warehouse.itemName.add("item 2");
        warehouse.itemName.add("item 2");
        warehouse.itemName.add("item 3");
        warehouse.itemName.add("item 3");
        warehouse.itemName.add("item 3");
        operationManager = new Manager(warehouse);

        when(warehouse.getItemQty("item 1")).thenReturn(5);
        //returns same value every time
        when(warehouse.getItemQty("item 2")).thenReturn(8);
        //return 10 when called first time and 12 for every time after that
        when(warehouse.getItemQty("item 3")).thenReturn(10).thenReturn(12);

        boolean currentCapacity = operationManager.canStoreTruck(100);
        InOrder inOrder = Mockito.inOrder(warehouse);
        inOrder.verify(warehouse).getItemQty("item 1");
        inOrder.verify(warehouse,times(2)).getItemQty("item 2");
        inOrder.verify(warehouse,times(3)).getItemQty("item 3");

        assertTrue(currentCapacity);
    }
}
