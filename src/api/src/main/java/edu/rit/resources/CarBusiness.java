package edu.rit.resources;

import cardealership.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class CarBusiness {

    public String getAllCars(){

        List<Car> carList = new DataLayer().getAllCars();

        JSONArray jsonArray = new JSONArray();
        for(Car car : carList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("car_id",car.getId());
            jsonObject.put("brand",car.getBrand());
            jsonObject.put("model",car.getModel());
            jsonObject.put("car_year",car.getCar_year());
            jsonObject.put("color",car.getColor());
            jsonObject.put("additional",car.getAdditional());
            jsonObject.put("sold",car.getSold());
            JSONObject carJson = new JSONObject().put("car",jsonObject);
            JSONObject successJson = new JSONObject().put("success",carJson);
            jsonArray.put(successJson);
        }

        return jsonArray.toString(2);

    }

    public String getCar(int car_id){

        Car car = new DataLayer().getCar(car_id);

        if(car == null){
            return new JSONObject().put("error","No car with id " + car_id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("car_id",car.getId());
        jsonObject.put("brand",car.getBrand());
        jsonObject.put("model",car.getModel());
        jsonObject.put("car_year",car.getCar_year());
        jsonObject.put("color",car.getColor());
        jsonObject.put("additional",car.getAdditional());
        jsonObject.put("sold",car.getSold());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);
    }

    //TODO
    //public String getFilteredCar

    public String insertCar(String brand, String model, int car_year, String color, String additional){

        Car car = new DataLayer().insertCar(brand,model,car_year,color, additional);

        //TODO
        //VALIDATION

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("car_id",car.getId());
        jsonObject.put("brand",car.getBrand());
        jsonObject.put("model",car.getModel());
        jsonObject.put("car_year",car.getCar_year());
        jsonObject.put("color",car.getColor());
        jsonObject.put("additional",car.getAdditional());
        jsonObject.put("sold",car.getSold());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }

    public String updateCar(int id, String brand, String model, int car_year, String color, String additional, int sold){

        Car car = new DataLayer().updateCar(id,brand,model,car_year,color,additional,sold);

        if(car == null){
            return new JSONObject().put("error","No car with id " + id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("car_id",car.getId());
        jsonObject.put("brand",car.getBrand());
        jsonObject.put("model",car.getModel());
        jsonObject.put("car_year",car.getCar_year());
        jsonObject.put("color",car.getColor());
        jsonObject.put("additional",car.getAdditional());
        jsonObject.put("sold",car.getSold());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }

    public String deleteCar(int car_id){

        Car car = new DataLayer().deleteCar(car_id);

        if(car == null){
            return new JSONObject().put("error","No car with id " + car_id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("car_id",car.getId());
        jsonObject.put("brand",car.getBrand());
        jsonObject.put("model",car.getModel());
        jsonObject.put("car_year",car.getCar_year());
        jsonObject.put("color",car.getColor());
        jsonObject.put("additional",car.getAdditional());
        jsonObject.put("sold",car.getSold());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }


}
