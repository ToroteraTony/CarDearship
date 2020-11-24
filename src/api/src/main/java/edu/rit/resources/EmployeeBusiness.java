package edu.rit.resources;

import companydata.DataLayer;
import companydata.Department;
import companydata.Employee;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EmployeeBusiness {
    public String deleteAllEmployees(String companyName) throws Exception {
        DataLayer dl = new DataLayer("production");
        List<Employee> employees = dl.getAllEmployee(companyName);

        if(employees.size() == 0){
            return new JSONObject().put("error", "No Employees in company " + companyName).toString(2);
        }

        String together = "";
        for (Employee emp: employees) {
            String timecardString = new TimecardBusiness().deleteAllTimecards(emp.getId());

            together += timecardString;

            dl.deleteEmployee(emp.getId());
        }
        together += new JSONObject().put("success", "All employees have been deleted from company " + companyName).toString(2);
        return together;

    }

    public String getAllEmployees(String companyName) throws Exception {
        DataLayer dl = new DataLayer("production");
        List<Employee> employees = dl.getAllEmployee(companyName);

        if(employees.size() == 0){
            return new JSONObject().put("error", "No Employees in company " + companyName).toString(2);
        }

        JSONArray employeeJSON = new JSONArray();
        for (Employee emp: employees) {
            JSONObject empJSON = new JSONObject();
            empJSON.put("emp_id",emp.getId());
            empJSON.put("emp_name",emp.getEmpName());
            empJSON.put("emp_no",emp.getEmpNo());
            empJSON.put("hire_date",emp.getHireDate());
            empJSON.put("job",emp.getJob());
            empJSON.put("salary",emp.getSalary());
            empJSON.put("dept_id",emp.getDeptId());
            empJSON.put("mng_id",emp.getMngId());
            JSONObject temp = new JSONObject().put("employee",empJSON);
            employeeJSON.put(temp);
        }

        return employeeJSON.toString(2);

    }

    public String getEmployee(int emp_id) throws Exception {
        DataLayer dl = new DataLayer("production");

        Employee employee = dl.getEmployee(emp_id);

        if(employee == null){
            return new JSONObject().put("error","No employee with id " + emp_id).toString(2);
        }

        JSONArray employeeJSON = new JSONArray();
        JSONObject empJSON = new JSONObject();
        empJSON.put("emp_id",employee.getId());
        empJSON.put("emp_name",employee.getEmpName());
        empJSON.put("emp_no",employee.getEmpNo());
        empJSON.put("hire_date",employee.getHireDate());
        empJSON.put("job",employee.getJob());
        empJSON.put("salary",employee.getSalary());
        empJSON.put("dept_id",employee.getDeptId());
        empJSON.put("mng_id",employee.getMngId());
        JSONObject tempObjectJSON = new JSONObject().put("employee",empJSON);

        employeeJSON.put(tempObjectJSON);

        return employeeJSON.toString(2);

    }

    public String insertEmployee(String emp_name, String emp_no, String hireDate, String job, double salary, int dept_id, int mng_id) throws Exception {
        DataLayer dl = new DataLayer("production");

        List<Employee> employees = dl.getAllEmployee("am1253");
        /* VALIDATION VARIABLES*/
        Date sqlDate = Date.valueOf(hireDate);
        LocalDate dateNow = LocalDate.now();
        LocalDate hireDateConverted = sqlDate.toLocalDate();


        /* VALIDATIONS */
        if(hireDateConverted.isAfter(dateNow)){
            return new JSONObject().put("error","Date needs to be before this transaction happened. So before " + dateNow).toString(2);
        }
        switch (hireDateConverted.getDayOfWeek().toString()){
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
                return new JSONObject().put("error","Hire date cannot be on a " + hireDateConverted.getDayOfWeek().toString()).toString(2);
            case "SUNDAY":
                return new JSONObject().put("error","Hire date cannot be on a " + hireDateConverted.getDayOfWeek().toString()).toString(2);
            default:
                return new JSONObject().put("error","There is an issue with a date converter. The date is: " + hireDateConverted.getDayOfWeek().toString()).toString(2);
        }

        Department department = dl.getDepartment("am1253",dept_id);

        if(department == null){
            return new JSONObject().put("error","There is no department with id of " + dept_id).toString(2);
        }

        if(mng_id != 0){
            Employee empl = dl.getEmployee(mng_id);

            if(empl == null){
                return new JSONObject().put("error","There is no manager with id of " + dept_id).toString(2);
            }
        }

        int id = 0;
        for (Employee emp: employees) {
            id = emp.getId();
            if(emp.getEmpNo().equals(emp_no)){
                return new JSONObject().put("error","There is already an employee with that emp_no " + emp_no).toString(2);
            }
        }
        id++;

        Employee employee = new Employee(id,emp_name,emp_no,sqlDate,job,salary,dept_id,mng_id);

        dl.insertEmployee(employee);

        JSONArray employeeJSON = new JSONArray();
        JSONObject empJSON = new JSONObject();
        empJSON.put("emp_id",employee.getId());
        empJSON.put("emp_name",employee.getEmpName());
        empJSON.put("emp_no",employee.getEmpNo());
        empJSON.put("hire_date",employee.getHireDate());
        empJSON.put("job",employee.getJob());
        empJSON.put("salary",employee.getSalary());
        empJSON.put("dept_id",employee.getDeptId());
        empJSON.put("mng_id",employee.getMngId());
        JSONObject tempObjectJSON = new JSONObject().put("employee",empJSON);

        employeeJSON.put(tempObjectJSON);

        return employeeJSON.toString(2);

    }

    public String updateEmployee(int emp_id, String emp_name, String emp_no, String hireDate, String job, double salary, int dept_id, int mng_id) throws Exception {
        /* DATALAYER STUFF */
        DataLayer dl = new DataLayer("production");
        List<Employee>employees = dl.getAllEmployee("am1253");


        Date sqlDate = Date.valueOf(hireDate);
        LocalDate dateNow = LocalDate.now();
        LocalDate hireDateConverted = sqlDate.toLocalDate();


        /* VALIDATIONS */
        if(hireDateConverted.isAfter(dateNow)){
            return new JSONObject().put("error","Date needs to be before this transaction happened. So before" + dateNow).toString(2);
        }
        switch (hireDateConverted.getDayOfWeek().toString()){
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
                return new JSONObject().put("error","Hire date cannot be on a " + hireDateConverted.getDayOfWeek().toString()).toString(2);
            case "SUNDAY":
                return new JSONObject().put("error","Hire date cannot be on a " + hireDateConverted.getDayOfWeek().toString()).toString(2);
            default:
                return new JSONObject().put("error","There is an issue with a date converter. The date is: " + hireDateConverted.getDayOfWeek().toString()).toString(2);
        }

        Department department = dl.getDepartment("am1253",dept_id);

        if(department == null){
            return new JSONObject().put("error","There is no department with id of " + dept_id).toString(2);
        }

        if(mng_id != 0){
            Employee empl = dl.getEmployee(mng_id);

            if(empl == null){
                return new JSONObject().put("error","There is no manager with id of " + mng_id).toString(2);
            }
        }

        for (Employee emp: employees) {
            if(emp.getEmpNo().equals(emp_no)){
                return new JSONObject().put("error","There is already an employee with that emp_no").toString(2);
            }
        }

        Employee emp = dl.getEmployee(emp_id);

        emp.setEmpName(emp_name);
        emp.setEmpNo(emp_no);
        emp.setHireDate(sqlDate);
        emp.setJob(job);
        emp.setSalary(salary);
        emp.setDeptId(dept_id);
        emp.setMngId(mng_id);
        dl.updateDepartment(department);

        JSONArray employeeJSON = new JSONArray();
        JSONObject empJSON = new JSONObject();
        empJSON.put("emp_id",emp.getId());
        empJSON.put("emp_name",emp_name);
        empJSON.put("emp_no",emp_no);
        empJSON.put("hire_date",sqlDate.toString());
        empJSON.put("job",job);
        empJSON.put("salary",salary);
        empJSON.put("dept_id",dept_id);
        empJSON.put("mng_id",mng_id);
        JSONObject tempObjectJSON = new JSONObject().put("employee",empJSON);

        employeeJSON.put(tempObjectJSON);

        return employeeJSON.toString(2);

    }

    public String deleteEmployee(int emp_id) throws Exception {
        /* DATALAYER STUFF */
        DataLayer dl = new DataLayer("production");
        Employee employee = dl.getEmployee(emp_id);

        if(employee == null){
            return new JSONObject().put("error","There is no employee with id of " + emp_id + " in this company").toString(2);
        }

        dl.deleteEmployee(emp_id);

        /* OUTPUT JSON STUFF */
        return new JSONObject().put("success","Employee " + emp_id + " deleted").toString(2);


    }
}
