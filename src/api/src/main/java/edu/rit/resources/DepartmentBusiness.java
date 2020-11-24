package edu.rit.resources;

import companydata.DataLayer;
import companydata.Department;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class DepartmentBusiness {
    public String deleteAll(String companyName) throws Exception {
        DataLayer dl = new DataLayer("production");

        List<Department> departments = dl.getAllDepartment(companyName);

        if(departments == null && departments.isEmpty()){
            return new JSONObject().put("Error","No Department for company " + companyName).toString(2);
        }

        String employee = new EmployeeBusiness().deleteAllEmployees(companyName);

        for (Department dep: departments) {
            dl.deleteDepartment(companyName,dep.getId());
        }

        String together = employee + new JSONObject().put("success",companyName + " successfully deleted").toString(2);

        return together;


    }

    public String getAllDepartments(String companyName) throws Exception {
        /* DATA LAYER*/
        List<Department> departments = new DataLayer("production").getAllDepartment(companyName);

        if (departments.size() == 0) {
            return new JSONObject().put("error", "No Department for company " + companyName).toString(2);
        }

        JSONArray departmentsJson = new JSONArray();
        for (Department d : departments) {
            JSONObject depJson = new JSONObject();
            depJson.put("dept_id",d.getId());
            depJson.put("company",d.getCompany());
            depJson.put("dept_name", d.getDeptName());
            depJson.put("dept_no",d.getDeptNo());
            depJson.put("location",d.getLocation());
            JSONObject departmentJSON = new JSONObject().put("department",depJson);
            JSONObject arrayJSON = new JSONObject().put("success",departmentJSON);
            departmentsJson.put(arrayJSON);
        }

        return departmentsJson.toString(2);


    }

    public String getDepartment(String companyName, int dept_id) throws Exception {
        Department department = new DataLayer("production").getDepartment(companyName,dept_id);

        if(department == null){
            return new JSONObject().put("error","No department in company " + companyName + " with id " + dept_id).toString(2);
        }

        JSONArray departmentJSON = new JSONArray();
        JSONObject depJSON = new JSONObject();
        depJSON.put("dept_id",department.getId());
        depJSON.put("company",department.getCompany());
        depJSON.put("dept_name",department.getDeptName());
        depJSON.put("dept_no",department.getDeptNo());
        depJSON.put("location",department.getLocation());
        JSONObject tempObjectJSON = new JSONObject().put("department",depJSON);
        JSONObject temptempObjectJSON = new JSONObject().put("success",tempObjectJSON);

        departmentJSON.put(temptempObjectJSON);

        return departmentJSON.toString(2);
    }

    public String insertDepartment(String company, String dept_name, String dept_no, String location) throws Exception {
        DataLayer dl = new DataLayer("production");
        List<Department> departments = dl.getAllDepartment(company);

        for (Department dep: departments) {
            if(dep.getDeptNo().equals(dept_no)){
                return new JSONObject().put("error",dep.getCompany() + " already has the dept_no " + dep.getDeptNo()).toString(2);
            }
        }

        int id = 0;

        for (Department d : departments) {
            id = d.getId();
        }
        id++;



        Department department = new Department(id,company,dept_name,dept_no,location);


        dl.insertDepartment(department);

        JSONArray departmentJSON = new JSONArray();
        JSONObject depJSON = new JSONObject();
        depJSON.put("dept_id",department.getId());
        depJSON.put("company",department.getCompany());
        depJSON.put("dept_name",department.getDeptName());
        depJSON.put("dept_no",department.getDeptNo());
        depJSON.put("location",department.getLocation());
        JSONObject tempObjectJSON = new JSONObject().put("department",depJSON);
        JSONObject temptempObjectJSON = new JSONObject().put("success",tempObjectJSON);

        departmentJSON.put(temptempObjectJSON);

        return departmentJSON.toString(2);
    }

    public String updateDepartment(int dept_id,String companyName,String dept_name,String dept_no,String location) throws Exception {
        /* DATALAYER STUFF */
        DataLayer dl = new DataLayer("production");
        List<Department> departments = dl.getAllDepartment(companyName);

        /* VALIDATIONS  */
        int i = 0;
        Department department = new Department(dept_id,companyName,dept_name,dept_no,location);
        for (Department dep: departments) {
            if(dept_id == dep.getId()){
                i++;
            }
            if(dep.getDeptNo().equals(department.getDeptNo())){
                return new JSONObject().put("error",dep.getCompany() + " already has the dept_no " + dep.getDeptNo()).toString(2);
            }
        }

        if(i == 0){
            return new JSONObject().put("error","No dep_id of " + dept_id + " exists.").toString(2);
        }


        for (Department dep: departments) {
            if(dep.getId() == dept_id){
                dep.setCompany(companyName);
                dep.setDeptName(dept_name);
                dep.setDeptNo(dept_no);
                dep.setLocation(location);
                dl.updateDepartment(department);
            }
        }

        /* OUTPUT JSON STUFF */
        JSONArray departmentJSON = new JSONArray();
        JSONObject depJSON = new JSONObject();
        depJSON.put("dept_id",department.getId());
        depJSON.put("company",department.getCompany());
        depJSON.put("dept_name",department.getDeptName());
        depJSON.put("dept_no",department.getDeptNo());
        depJSON.put("location",department.getLocation());
        JSONObject tempObjectJSON = new JSONObject().put("department",depJSON);
        JSONObject temptempObjectJSON = new JSONObject().put("success",tempObjectJSON);

        departmentJSON.put(temptempObjectJSON);

        return departmentJSON.toString(2);
    }


    public String deleteDepartment(int dept_id, String companyName) throws Exception{
        /* DATALAYER STUFF */
        DataLayer dl = new DataLayer("production");
        Department department = dl.getDepartment(companyName,dept_id);

        if(department == null){
            return new JSONObject().put("error","There is no department with id of " + dept_id + " in this company").toString(2);
        }



        dl.deleteDepartment(companyName,dept_id);

        /* OUTPUT JSON STUFF */
        return new JSONObject().put("success","Department " + dept_id + " from " + companyName + " deleted").toString(2);

    }


}
