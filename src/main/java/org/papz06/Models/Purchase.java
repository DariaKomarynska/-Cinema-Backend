package org.papz06.Models;

import java.util.Date;

public class Purchase {
    int id, scheduleId, amount;
    Date datetime;
    String paymentMethod, currency;
    long date;

    public Purchase(int id, Date datetime, int amount, String paymentMethod, String currency, int scheduleId) {
        this.id = id;
        this.datetime = datetime;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.scheduleId = scheduleId;
    }

    public Purchase(int id, long date, int amount, String paymentMethod, String currency, int scheduleId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.scheduleId = scheduleId;
    }


    public int getId() {
        return id;
    }

    public long getDate() {
        return date;
    }

    public int getAmount(){
        return amount;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }

    public String getCurrency(){
        return currency;
    }

    public int getScheduleId(){
        return scheduleId;
    }

    @Override
    public String toString() {
        return id + " " + date  //.toString()
                + " " + amount
                + " " + paymentMethod
                + " " + currency
                + " " + scheduleId;
    }
}
