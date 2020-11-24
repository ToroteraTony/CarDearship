package edu.rit.resources;

import cardealership.DataLayer;
import cardealership.Receipt;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class ReceiptBusiness {

    public String getAllReceipts(){

        List<Receipt> receipts = new DataLayer().getAllReceipts();

        if(receipts.size() == 0){
            return new JSONObject().put("error","No receipts have been made yet.").toString();
        }

        JSONArray jsonArray = new JSONArray();
        for(Receipt receipt : receipts){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("receipt_id",receipt.getReceipt_id());
            jsonObject.put("buyer_id",receipt.getBuyer_id());
            jsonObject.put("car_id",receipt.getCar_id());
            jsonObject.put("date_of_purchase",receipt.getDate_of_purchase());
            JSONObject carJson = new JSONObject().put("car",jsonObject);
            JSONObject successJson = new JSONObject().put("success",carJson);
            jsonArray.put(successJson);
        }

        return jsonArray.toString(2);

    }

    public String getReceipt(int receipt_id){

        Receipt receipt = new DataLayer().getReceipt(receipt_id);

        if(receipt == null){
            return new JSONObject().put("error","No receipt with id " + receipt_id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receipt_id",receipt.getReceipt_id());
        jsonObject.put("buyer_id",receipt.getBuyer_id());
        jsonObject.put("car_id",receipt.getCar_id());
        jsonObject.put("date_of_purchase",receipt.getDate_of_purchase());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);
    }

    //TODO
    //public String getFilteredCar

    public String insertReceipt(int buyer_id, int car_id, Date date_of_purchase){

        Receipt receipt = new DataLayer().insertReceipt(buyer_id, car_id, date_of_purchase);

        //TODO
        //VALIDATION

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receipt_id",receipt.getReceipt_id());
        jsonObject.put("buyer_id",receipt.getBuyer_id());
        jsonObject.put("car_id",receipt.getCar_id());
        jsonObject.put("date_of_purchase",receipt.getDate_of_purchase());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }

    public String updateReceipt(int receipt_id,int buyer_id, int car_id, Date date_of_purchase){
        Receipt receipt = new DataLayer().updateReceipt(receipt_id,buyer_id,car_id,date_of_purchase);

        //TODO
        //VALIDATION

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receipt_id",receipt.getReceipt_id());
        jsonObject.put("buyer_id",receipt.getBuyer_id());
        jsonObject.put("car_id",receipt.getCar_id());
        jsonObject.put("date_of_purchase",receipt.getDate_of_purchase());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);

        return jsonArray.toString(2);

    }

    public String deleteReceipt(int receipt_id){

        Receipt receipt = new DataLayer().deleteReceipt(receipt_id);

        if(receipt == null){
            return new JSONObject().put("error","No receipt with id " + receipt_id).toString();
        }

        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("receipt_id",receipt.getReceipt_id());
        jsonObject.put("buyer_id",receipt.getBuyer_id());
        jsonObject.put("car_id",receipt.getCar_id());
        jsonObject.put("date_of_purchase",receipt.getDate_of_purchase());
        JSONObject carJson = new JSONObject().put("car",jsonObject);
        JSONObject successJson = new JSONObject().put("success",carJson);
        jsonArray.put(successJson);



        return jsonArray.toString(2);

    }

}
