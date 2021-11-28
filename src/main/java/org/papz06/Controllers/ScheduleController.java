package org.papz06.Controllers;

import org.papz06.Models.*;
import org.papz06.Function;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleController {
    ArrayList<Schedule> scheduleList = new ArrayList<>();

    public ScheduleController() {
        Function fc = new Function();
        ResultSet rs;
        try {
            rs = fc.executeQuery("select * from schedules");
            while (rs.next()) {
                scheduleList.add(
                        new Schedule(rs.getInt(1),
                                rs.getDate(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getInt(7)
                        )
                );
            }
            fc.closeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displaySchedulesList() {
        for (Schedule sch : scheduleList) {
            System.out.println(sch.toString());
        }
    }
}
