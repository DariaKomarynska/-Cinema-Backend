package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PurchaseController {
    List<Purchase> purchasesList = new ArrayList<>();

    public PurchaseController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery(("purchases"));
            while (rs.next()) {
                purchasesList.add(
                        new Purchase(rs.getInt(1),
                                rs.getDate(2),
                                rs.getFloat(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getInt(6))
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayPurchasesList() {
        for (Purchase pch : purchasesList) {
            System.out.println(pch.toString());
        }
    }
}


