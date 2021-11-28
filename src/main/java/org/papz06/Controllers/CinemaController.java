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

    public CinemaController() {
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

    public void displayCinemas() {
        for (Cinema ci : cinemasList)
            System.out.println(ci.toString());
    }

    public Map<Integer, String> getListOfCinemas() {
        Map<Integer, String> listCinemas = new HashMap<Integer, String>();
        for (Cinema cin : cinemasList) {
            listCinemas.put(cin.getId(), cin.getName());
        }
        return listCinemas;
    }

    public boolean isEmptyList() {
        if (cinemasList.size() == 0) {
            return true;
        }
        return false;
    }

    public void printIsEmpty() {
        // always full
        if (isEmptyList()) {
            System.out.println("emp");
        } else {
            System.out.println("full");
        }
    }

    public List<Object> createNewRowWithName(String newName) {
        // in the beginning of the table
        List<Object> row = new ArrayList<Object>();
        row.add(cinemasList.size() + 1);
        row.add(null);
        row.add(newName);
        return row;
    }

    public Map<Integer, String> insertNewRow(String newName) {
        List<Object> newRow = createNewRowWithName(newName);
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.insertQuery("cinemas", newRow);
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        Map<Integer, String> addedRow = new HashMap<Integer, String>();
        addedRow.put((Integer) newRow.get(0), (String) newRow.get(1));
        return addedRow;
    }

    public boolean sizeNewNameCinema(String newName) {
        if (newName.length() == 0) {
            return false;
        }
        return true;
    }
}
