package edu.rit;

import edu.rit.resources.CarBusiness;
import edu.rit.resources.ReceiptBusiness;

import javax.ws.rs.*;
import java.sql.Date;

@Path("")
public class CompanyService {

    //@GET
    //public String helloWorld(){
    //    return "Hello World!";
    //}

    //http://localhost:8080/api/cars
    @GET
    @Path("cars")
    @Produces("application/json")
    public String getAllCars(){
        return new CarBusiness().getAllCars();
    }

    //http://localhost:8080/api/car/{id}
    @GET
    @Path("car/{car_id}")
    @Produces("application/json")
    public String getCar(@PathParam("car_id")int car_id){
        return new CarBusiness().getCar(car_id);
    }

    //http://localhost:8080/api/insertCar/Ford/Focus/2000/Blue/Aux
    @PUT
    @Path("insertCar/{brand}/{model}/{car_year}/{color}/{additional}")
    @Produces("application/json")
    public String insertCar(@PathParam("brand")String brand,
                            @PathParam("model")String model,
                            @PathParam("car_year")int car_year,
                            @PathParam("color") String color,
                            @PathParam("additional")String additional){
        return new CarBusiness().insertCar(brand, model, car_year, color, additional);
    }

    //http://localhost:8080/api/deleteCar/{id}
    @DELETE
    @Path("deleteCar/{car_id}")
    @Produces("application/json")
    public String deleteCar(@PathParam("car_id")int car_id){
        return new CarBusiness().deleteCar(car_id);
    }


    //http://localhost:8080/api/updateCar/{car_id}/{brand}/{model}/{car_year}/{color}/{additional}/{sold}
    @POST
    @Path("updateCar/{car_id}/{brand}/{model}/{car_year}/{color}/{additional}/{sold}")
    @Produces("application/json")
    public String updateCar(@PathParam("car_id")int id,
                            @PathParam("brand")String brand,
                            @PathParam("model")String model,
                            @PathParam("car_year")int car_year,
                            @PathParam("color") String color,
                            @PathParam("additional")String additional,
                            @PathParam("sold")int sold){
        return new CarBusiness().updateCar(id,brand,model,car_year,color,additional,sold);
    }

    //http://localhost:8080/api/receipts
    @GET
    @Path("receipts")
    @Produces("application/json")
    public String getAllReceipts(){
        return new ReceiptBusiness().getAllReceipts();
    }

    //http://localhost:8080/api/receipt/{id}
    @GET
    @Path("receipt/{receipt_id}")
    @Produces("application/json")
    public String getReceipt(@PathParam("receipt_id")int receipt_id){
        return new ReceiptBusiness().getReceipt(receipt_id);
    }

    //http://localhost:8080/api/insertReceipt/{buyer_id}/{car_id}/{date_of_purchase}
    @PUT
    @Path("insertReceipt/{buyer_id}/{car_id}/{date_of_purchase}")
    @Produces("application/json")
    public String insertReceipt(@PathParam("buyer_id")int buyer_id,
                                @PathParam("car_id")int car_id,
                                @PathParam("date_of_purchase")Date date_of_purchase){
        return new ReceiptBusiness().insertReceipt(buyer_id,car_id,date_of_purchase);
    }

    //http://localhost:8080/api/deleteReceipt/{id}
    @DELETE
    @Path("deleteCar/{receipt_id}")
    @Produces("application/json")
    public String deleteReceipt(@PathParam("receipt_id")int receipt_id){
        return new ReceiptBusiness().deleteReceipt(receipt_id);
    }


    //http://localhost:8080/api/updateCar/{car_id}/{brand}/{model}/{car_year}/{color}/{additional}/{sold}
    @POST
    @Path("updateReceipt/{receipt_id}/{buyer_id}/{car_id}/{date_of_purchase}")
    @Produces("application/json")
    public String updateReceipt(@PathParam("receipt_id")int receipt_id,
                                @PathParam("buyer_id")int buyer_id,
                                @PathParam("car_id")int car_id,
                                @PathParam("date_of_purchase")Date date_of_purchase){
        return new ReceiptBusiness().updateReceipt(receipt_id, buyer_id, car_id, date_of_purchase);
    }



}

