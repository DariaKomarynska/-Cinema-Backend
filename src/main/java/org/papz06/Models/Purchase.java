package org.papz06.Models;

import java.util.Date;

public class Purchase {
    int id, scheduleId;
    Date datetime;
    float amount;
    String paymentMethod, currency;

    public Purchase(int id, Date datetime, float amount, String paymentMethod, String currency, int scheduleId) {
        this.id = id;
        this.datetime = datetime;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.scheduleId = scheduleId;
    }

    public Purchase() {
    }

    @Override
    public String toString() {
        return id + " " + datetime  //.toString()
                + " " + amount
                + " " + paymentMethod
                + " " + currency
                + " " + scheduleId;
    }
}
