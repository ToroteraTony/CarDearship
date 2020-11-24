package edu.rit.resources;

import companydata.DataLayer;
import companydata.Employee;
import companydata.Timecard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimecardBusiness {
    public String deleteAllTimecards(int emp_id) throws Exception {
        DataLayer dl = new DataLayer("production");
        List<Timecard> timecards = dl.getAllTimecard(emp_id);

        if(timecards.size() == 0){
            return new JSONObject().put("error","No timecards by employee " + emp_id).toString(2);
        }

        for (Timecard tim: timecards) {
            dl.deleteTimecard(tim.getId());
        }

        return new JSONObject().put("success","All timecards by employee " + emp_id + " have been deleted.").toString(2);


    }

    public String getAllTimecards(int emp_id) throws Exception {
        /* DATALAYER STUFF */
        DataLayer dl = new DataLayer("production");
        List<Timecard> timecards = dl.getAllTimecard(emp_id);

        if(timecards.size() == 0){
            return new JSONObject().put("error", "No timecards by employee " + emp_id).toString(2);
        }

        JSONArray employeeJSON = new JSONArray();
        for (Timecard tim: timecards) {
            JSONObject empJSON = new JSONObject();
            empJSON.put("timecard_id",tim.getId());
            empJSON.put("start_time",tim.getStartTime());
            empJSON.put("end_time",tim.getEndTime());
            empJSON.put("emp_id",tim.getEmpId());
            JSONObject tempObjectJSON = new JSONObject().put("timecard",empJSON);
            employeeJSON.put(tempObjectJSON);

        }

        return employeeJSON.toString(2);

    }

    public String getTimecard(int time_id) throws Exception {
        /* DATALAYER STUFF*/
        DataLayer dl = new DataLayer("production");
        Timecard timecard = dl.getTimecard(time_id);

        if(timecard == null){
            return new JSONObject().put("error", "No timecards with id " + time_id).toString(2);
        }

        JSONArray employeeJSON = new JSONArray();
        JSONObject empJSON = new JSONObject();
        empJSON.put("timecard_id",timecard.getId());
        empJSON.put("start_time",timecard.getStartTime());
        empJSON.put("end_time",timecard.getEndTime());
        empJSON.put("emp_id",timecard.getEmpId());
        JSONObject tempObjectJSON = new JSONObject().put("timecard",empJSON);
        employeeJSON.put(tempObjectJSON);


        return employeeJSON.toString(2);

    }

    public String insertTimecard(int emp_id, String start_date, String end_date) throws Exception {
        DataLayer dl = new DataLayer("production");
        List<Timecard> timecards = dl.getAllTimecard(emp_id);

        Employee employee = dl.getEmployee(emp_id);

        if(employee == null){
            return new JSONObject().put("error","There is no employee with id of " + emp_id).toString(2);
        }

        /* VALIDATION VARIABLES*/
        LocalDate dateNow = LocalDate.now();
        LocalDate localStartDate = LocalDate.parse(start_date, DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss"));
        LocalDate localEndDate = LocalDate.parse(end_date,DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss"));
        int id = 0;

        /* VALIDATIONS */
        if(localStartDate.isAfter(dateNow)){
            return new JSONObject().put("error","The start date needs to be before this transaction happened. So before " + dateNow).toString(2);
        }
        if(localEndDate.isAfter(dateNow)){
            return new JSONObject().put("error","The end date needs to be before this transaction happened. So before " + dateNow).toString(2);
        }
        if (localEndDate.isBefore(localStartDate)) {
            return new JSONObject().put("error","The end date needs to be after the start date. So before " + dateNow).toString(2);
        }
        if(!localStartDate.isEqual(dateNow)){
            if(dateNow.minusDays(7).isBefore(localStartDate)){
                return new JSONObject().put("error","The start date needs to be set on today's date or anywhere from a week from now.").toString(2);
            }
        }
        /*
        if(localEndDate.minus(1, ChronoUnit.HOURS).isBefore(localStartDate)){
            return new JSONObject().put("error","The end date needs to be at least an hour after the start date happened").toString(2);
        }
        */
        if(localEndDate.getDayOfWeek() != localStartDate.getDayOfWeek() || localEndDate.getDayOfMonth() != localStartDate.getDayOfMonth() || localEndDate.getDayOfYear() != localStartDate.getDayOfYear()){
            return new JSONObject().put("error","The end date needs to be on the same day as the start date. So the date should be " + localStartDate).toString();
        }

        switch (localStartDate.getDayOfWeek().toString()){
            case "MONDAY":
                break;
            case "TUESDAY":
                break;
            case "WEDNESDAY":
                break;
            case "THURSDAY":
                break;
            case "FRIDAY":
                break;
            case "SATURDAY":
                return new JSONObject().put("error","Start date cannot be on a " + localStartDate.getDayOfWeek().toString()).toString(2);
            case "SUNDAY":
                return new JSONObject().put("error","Start date cannot be on a " + localStartDate.getDayOfWeek().toString()).toString(2);
            default:
                return new JSONObject().put("error","There is an issue with a start date converter. The date is: " + localStartDate.getDayOfWeek().toString()).toString();
        }

        switch (localEndDate.getDayOfWeek().toString()){
            case "MONDAY":
                break;
            case "TUESDAY":
                break;
            case "WEDNESDAY":
                break;
            case "THURSDAY":
                break;
            case "FRIDAY":
                break;
            case "SATURDAY":
                return new JSONObject().put("error","End date cannot be on a " + localEndDate.getDayOfWeek().toString()).toString(2);
            case "SUNDAY":
                return new JSONObject().put("error","End date cannot be on a " + localEndDate.getDayOfWeek().toString()).toString(2);
            default:
                return new JSONObject().put("error","There is an issue with a end date converter. The date is: " + localEndDate.getDayOfWeek().toString()).toString(2);
        }

        Timestamp startTimestamp = Timestamp.valueOf(start_date);
        Timestamp endTimestamp = Timestamp.valueOf(end_date);

        for (Timecard time: timecards) {
            if(localStartDate.getDayOfWeek() == time.getStartTime().toLocalDateTime().getDayOfWeek()){
                if(localStartDate.getDayOfMonth() == time.getStartTime().toLocalDateTime().getDayOfYear()){
                    if(localStartDate.getDayOfYear() == time.getStartTime().toLocalDateTime().getDayOfYear()){
                        return new JSONObject().put("error","No timestamps can be done on the same day").toString(2);
                    }
                }
            }

            if(localEndDate.getDayOfWeek() == time.getEndTime().toLocalDateTime().getDayOfWeek()){
                if(localEndDate.getDayOfMonth() == time.getEndTime().toLocalDateTime().getDayOfYear()){
                    if(localEndDate.getDayOfYear() == time.getEndTime().toLocalDateTime().getDayOfYear()){
                        return new JSONObject().put("error","No timestamps can be done on the same day").toString(2);
                    }
                }
            }

        }

        for (Timecard tim: timecards) {
            id = tim.getId();
        }
        id++;

        Timecard timecard = new Timecard(id,startTimestamp,endTimestamp,emp_id);

        dl.insertTimecard(timecard);

        JSONArray employeeJSON = new JSONArray();
        JSONObject empJSON = new JSONObject();
        empJSON.put("timecard_id",timecard.getId());
        empJSON.put("start_time",timecard.getStartTime());
        empJSON.put("end_time",timecard.getEndTime());
        empJSON.put("emp_id",timecard.getEmpId());
        JSONObject tempObjectJSON = new JSONObject().put("timecard",empJSON);
        employeeJSON.put(tempObjectJSON);


        return employeeJSON.toString(2);

    }


    public String updateTimecard(int time_id, int emp_id, String start_date, String end_date) throws Exception {
        DataLayer dl = new DataLayer("production");
        List<Timecard> timecards = dl.getAllTimecard(emp_id);

        Employee employee = dl.getEmployee(emp_id);

        if(employee == null){
            return new JSONObject().put("error","There is no employee with id of " + emp_id).toString(2);
        }

        /* VALIDATION VARIABLES*/
        LocalDate dateNow = LocalDate.now();
        LocalDate localStartDate = LocalDate.parse(start_date, DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss"));
        LocalDate localEndDate = LocalDate.parse(end_date,DateTimeFormatter.ofPattern ( "yyyy-MM-dd HH:mm:ss"));

        /* VALIDATIONS */
        if(localStartDate.isAfter(dateNow)){
            return new JSONObject().put("error","The start date needs to be before this transaction happened. So before " + dateNow).toString(2);
        }
        if(localEndDate.isAfter(dateNow)){
            return new JSONObject().put("error","The end date needs to be before this transaction happened. So before " + dateNow).toString(2);
        }
        if (localEndDate.isBefore(localStartDate)) {
            return new JSONObject().put("error","The end date needs to be after the start date. So before " + dateNow).toString(2);
        }
        if(!localStartDate.isEqual(dateNow)){
            if(dateNow.minusDays(7).isBefore(localStartDate)){
                return new JSONObject().put("error","The start date needs to be set on today's date or anywhere from a week from now.").toString(2);
            }
        }
        /*
        if(localEndDate.minus(1, ChronoUnit.HOURS).isBefore(localStartDate)){
            return new JSONObject().put("error","The end date needs to be at least an hour after the start date happened").toString();
        }
         */
        if(localEndDate.getDayOfWeek() != localStartDate.getDayOfWeek() || localEndDate.getDayOfMonth() != localStartDate.getDayOfMonth() || localEndDate.getDayOfYear() != localStartDate.getDayOfYear()){
            return new JSONObject().put("error","The end date needs to be on the same day as the start date. So the date should be " + localStartDate).toString();
        }

        switch (localStartDate.getDayOfWeek().toString()){
            case "MONDAY":
                break;
            case "TUESDAY":
                break;
            case "WEDNESDAY":
                break;
            case "THURSDAY":
                break;
            case "FRIDAY":
                break;
            case "SATURDAY":
                return new JSONObject().put("error","Start date cannot be on a " + localStartDate.getDayOfWeek().toString()).toString(2);
            case "SUNDAY":
                return new JSONObject().put("error","Start date cannot be on a " + localStartDate.getDayOfWeek().toString()).toString(2);
            default:
                return new JSONObject().put("error","There is an issue with a start date converter. The date is: " + localStartDate.getDayOfWeek().toString()).toString(2);
        }

        switch (localEndDate.getDayOfWeek().toString()){
            case "MONDAY":
                break;
            case "TUESDAY":
                break;
            case "WEDNESDAY":
                break;
            case "THURSDAY":
                break;
            case "FRIDAY":
                break;
            case "SATURDAY":
                return new JSONObject().put("error","End date cannot be on a " + localEndDate.getDayOfWeek().toString()).toString(2);
            case "SUNDAY":
                return new JSONObject().put("error","End date cannot be on a " + localEndDate.getDayOfWeek().toString()).toString(2);
            default:
                return new JSONObject().put("error","There is an issue with a end date converter. The date is: " + localEndDate.getDayOfWeek().toString()).toString();
        }

        for (Timecard time: timecards) {
            if(localStartDate.getDayOfWeek() == time.getStartTime().toLocalDateTime().getDayOfWeek()){
                if(localStartDate.getDayOfMonth() == time.getStartTime().toLocalDateTime().getDayOfYear()){
                    if(localStartDate.getDayOfYear() == time.getStartTime().toLocalDateTime().getDayOfYear()){
                        return new JSONObject().put("error","No timestamps can be done on the same day").toString(2);
                    }
                }
            }

            if(localEndDate.getDayOfWeek() == time.getEndTime().toLocalDateTime().getDayOfWeek()){
                if(localEndDate.getDayOfMonth() == time.getEndTime().toLocalDateTime().getDayOfYear()){
                    if(localEndDate.getDayOfYear() == time.getEndTime().toLocalDateTime().getDayOfYear()){
                        return new JSONObject().put("error","No timestamps can be done on the same day").toString(2);
                    }
                }
            }

        }

        Timecard timecard = dl.getTimecard(time_id);

        if(timecard == null){
            return new JSONObject().put("error","There is no timecard with id of " + time_id).toString(2);
        }

        Timestamp startTimestamp = Timestamp.valueOf(start_date);
        Timestamp endTimestamp = Timestamp.valueOf(end_date);

        timecard.setStartTime(startTimestamp);
        timecard.setEndTime(endTimestamp);
        timecard.setEmpId(emp_id);

        dl.updateTimecard(timecard);

        JSONArray employeeJSON = new JSONArray();
        JSONObject empJSON = new JSONObject();
        empJSON.put("timecard_id",timecard.getId());
        empJSON.put("start_time",timecard.getStartTime());
        empJSON.put("end_time",timecard.getEndTime());
        empJSON.put("emp_id",timecard.getEmpId());
        JSONObject tempObjectJSON = new JSONObject().put("timecard",empJSON);
        employeeJSON.put(tempObjectJSON);


        return employeeJSON.toString(2);

    }

    public String deleteTimecard(int time_id) throws Exception {
        /* DATALAYER STUFF */
        DataLayer dl = new DataLayer("production");
        Timecard timecard = dl.getTimecard(time_id);

        if(timecard == null){
            return new JSONObject().put("error","There is no timecard with id of " + time_id).toString(2);
        }

        dl.deleteTimecard(time_id);

        /* OUTPUT JSON STUFF */
        return new JSONObject().put("success","Timecard " + time_id + " deleted").toString(2);



    }
}
