package org.papz06.Models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PurchaseTest {

    Purchase purchase = new Purchase(5, 1642100369011L, 250, "BLIK", "pln", 14);

    private static final double DELTA = 1e-15;


    @Test
    public void testGetId() {
        int id = purchase.getId();
        assertEquals(id, 5);
    }

    @Test
    public void testGetDate() {
        long date = 1642100369011L;
        assertEquals(purchase.getDate(), date, DELTA);
    }

    @Test
    public void getAmount() {
        assertEquals(purchase.getAmount(), 250, DELTA);
    }

    @Test
    public void testPaymentMethod() {
        assertEquals(purchase.getPaymentMethod(), "BLIK");
    }

    @Test
    public void testCurrency() {
        assertEquals(purchase.getCurrency(), "pln");
    }

    @Test
    public void testScheduleId() {
        assertEquals(purchase.getScheduleId(), 14);
    }

    @Test
    public void testToString() {
        String purchaseStr = "5 1642100369011 250.0 BLIK pln 14";
        assertEquals(purchaseStr, purchase.toString());
    }

}
