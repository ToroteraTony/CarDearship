package edu.rit.resources;

import cardealership.DataLayer;
import cardealership.Users;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class UsersBusiness {

    public String getAllUsers(){

        List<Users> users = new DataLayer().getAllUsers();

        if(users.size() == 0){
            return new JSONObject().put("error","No users have been made yet.").toString();
        }

        JSONArray jsonArray = new JSONArray();
        for(Users user : users){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",user.getId());
            jsonObject.put("username",user.getUsername());
            jsonObject.put("fullname",user.getFullName());
            jsonObject.put("email",user.getEmail());
            jsonObject.put("phonenumber",user.getPhoneNumber());
            jsonObject.put("type",user.getType());
            JSONObject carJson = new JSONObject().put("user",jsonObject);
            JSONObject successJson = new JSONObject().put("success",carJson);
            jsonArray.put(successJson);
        }

        return jsonArray.toString(2);

    }

    public String getUser(int id){

        Users user = new DataLayer().getUser(id);

        if(user == null){
            return new JSONObject().put("error","No user with id " + id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("username",user.getUsername());
        jsonObject.put("fullname",user.getFullName());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phonenumber",user.getPhoneNumber());
        jsonObject.put("type",user.getType());
        JSONObject carJson = new JSONObject().put("user",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);
    }

    public String insertUser(String username, String password, String fullname, String email, String phonenumber, int type){

        Users user = new DataLayer().insertUsers(username,password,fullname,email,phonenumber,type);

        if(user == null){
            return new JSONObject().put("error","Insert user has an issue").toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("username",user.getUsername());
        jsonObject.put("password",user.getPassword());
        jsonObject.put("fullname",user.getFullName());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phonenumber",user.getPhoneNumber());
        jsonObject.put("type",user.getType());
        JSONObject carJson = new JSONObject().put("user",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }

    public String updateUser(int id,String username, String password, String fullname, String email, String phonenumber, int type){

        Users user = new DataLayer().updateUsers(id,password, username, fullname, email, phonenumber, type);

        //TODO
        //VALIDATION

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("username",user.getUsername());
        jsonObject.put("password",user.getPassword());
        jsonObject.put("fullname",user.getFullName());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phonenumber",user.getPhoneNumber());
        jsonObject.put("type",user.getType());
        JSONObject carJson = new JSONObject().put("user",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);

        return jsonArray.toString(2);

    }

    public String deleteUser(int id){

        Users user = new DataLayer().deleteUsers(id);

        if(user == null){
            return new JSONObject().put("error","No user with id " + id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("username",user.getUsername());
        jsonObject.put("fullname",user.getFullName());
        jsonObject.put("email",user.getEmail());
        jsonObject.put("phonenumber",user.getPhoneNumber());
        jsonObject.put("type",user.getType());
        JSONObject carJson = new JSONObject().put("user",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }


}
