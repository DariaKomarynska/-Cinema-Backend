package org.papz06.Controllers;

import org.papz06.Function;
import org.papz06.Models.Cinema;

import java.net.Inet4Address;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaController {
    List<Cinema> cinemasList = new ArrayList<>();
    public CinemaController(){
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("cinemas");
            while (rs.next()) {
                cinemasList.add(
                        new Cinema(rs.getInt(1),
                                rs.getInt(2),
                                rs.getString(3)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void displayCinemas(){
        for (Cinema ci:cinemasList)
            System.out.println(ci.toString());
    }

    public Map<Integer, String> getListOfCinemas(){
        Map<Integer, String> listCinemas = new HashMap<Integer, String>();
        for (Cinema cin: cinemasList){
            listCinemas.put(cin.getId(), cin.getName());
        }
        return listCinemas;
    }

    public boolean isEmptyList(){
        return cinemasList.isEmpty();
    }
    public void printIsEmpty(){
        if (!isEmptyList()) {
            System.out.println("full");
        } else {
            System.out.println("empty");
        }
    }
}
