package org.papz06.Request;

import org.papz06.Controllers.CinemaController;
import org.json.JSONObject;
import org.papz06.KeyValue;

import java.util.HashMap;
import java.util.Map;

public class CinemaServer {
    public KeyValue<Integer, String> CinemaList() {
                /*
         Returns list of cinemas managed by the user.
        */
        CinemaController cinControl = new CinemaController();
        Map<Integer, String> result = new HashMap<Integer, String>();
        Map<Integer, String> data = cinControl.getListOfCinemas();
        if (cinControl.isEmptyList()) {
            result.put(0, "Permission denied...Oops");
            return new KeyValue<Integer, String>(403, new JSONObject(result).toString());
        }
        result = data;
        return new KeyValue<>(200, new JSONObject(result).toString());
    }

    public KeyValue<Integer, String> CinemaCreate(String requestBody) {
        CinemaController cinControl = new CinemaController();
        Map<Integer, String> result = new HashMap<Integer, String>();

        if (!cinControl.sizeNewNameCinema(requestBody)) {
            // size of cinema name == 0
            result.put(0, "Empty name, change it!");
            return new KeyValue<Integer, String>(400, new JSONObject(result).toString());
        }
        // else if (permission denied)
        result = cinControl.insertNewRow(requestBody);
        return new KeyValue<Integer, String>(200, new JSONObject(result).toString());
    }

    public KeyValue<Integer, String> CinemaDetails() {
        //
        return null;
    }

    public KeyValue<Integer, String> CinemaUpdate() {
        //
        return null;
    }

    public void CinemaDelete() {
        //
    }
}
