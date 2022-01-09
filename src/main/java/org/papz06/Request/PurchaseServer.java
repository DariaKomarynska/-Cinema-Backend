package org.papz06.Request;

import org.json.JSONArray;
import org.papz06.KeyValue;

public class PurchaseServer {
    public static KeyValue<Integer, String> PurchaseCreate(String requesBody) {
        JSONArray result = null;
        return new KeyValue<>(200, result.toString());
    }

    public static KeyValue<Integer, String> PaymentAccepted(int id, String requesBody) {
        JSONArray result = null;
        return new KeyValue<>(200, result.toString());
    }
}
