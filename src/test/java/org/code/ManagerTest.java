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
        operationManager = new Manager(warehouse);
        when(warehouse.getCurrentCapacity()).thenReturn(500);
        boolean currentCapacity = operationManager.canStoreTruck(100);
        assertFalse(currentCapacity);

        when(warehouse.getCurrentCapacity()).thenReturn(100);
         currentCapacity = operationManager.canStoreTruck(99);
        assertTrue(currentCapacity);
    }
}
