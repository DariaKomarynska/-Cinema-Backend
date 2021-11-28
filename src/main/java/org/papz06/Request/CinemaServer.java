package org.papz06.Request;

import org.papz06.Controllers.CinemaController;
import org.json.JSONObject;
import org.papz06.KeyValue;

import java.util.Map;

public class CinemaServer {
    public KeyValue<Integer, String> CinemaList(){
                /*
         Returns list of cinemas managed by the user.
        */
        CinemaController cinControl = new CinemaController();
        Map<Integer, String> result = cinControl.getListOfCinemas();
        JSONObject jo_res = new JSONObject(result);

        return new KeyValue<>(200, new JSONObject(jo_res).toString());
    }
    public KeyValue<Integer, String> CinemaCreate(String requesBody){
        //
        return null;
    }
    public KeyValue<Integer, String> CinemaDetails(int id){
        //
        return null;
    }
    public KeyValue<Integer, String> CinemaUpdate(int id, String requesBody){
        //
        return null;
    }
    public KeyValue<Integer, String> CinemaDelete(int id){
        //
        return null;
    }
}
