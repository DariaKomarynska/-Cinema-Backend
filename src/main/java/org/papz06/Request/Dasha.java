package org.papz06.Request;

import javafx.util.Pair;
import org.papz06.Controllers.CinemaController;
import org.json.JSONObject;
import java.util.Map;

public class CinemaServer {
    public Pair<Integer, String> CinemaList(){
                /*
         Returns list of cinemas managed by the user.
        */
        CinemaController cinControl = new CinemaController();
        Map<Integer, String> result = cinControl.getListOfCinemas();
        JSONObject jo_res = new JSONObject(result);

        return new Pair<>(200, new JSONObject(jo_res).toString());
    }
    public Pair<Integer, String> CinemaCreate(String requesBody){
        //
        return null;
    }
    public Pair<Integer, String> CinemaDetails(){
        //
        return null;
    }
    public Pair<Integer, String> CinemaUpdate(){
        //
        return null;
    }
    public void CinemaDelete(){
        //
    }
}
