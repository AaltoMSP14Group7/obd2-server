package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import models.DataPoint;
import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

    public static Result addDataPoint() {
    	JsonNode json = request().body().asJson();

    	String deviceId = json.findPath("deviceId").textValue();
    	String vin = json.findPath("vin").textValue();
    	int timestamp = json.findPath("timestamp").intValue();
    	String type = json.findPath("type").textValue();
    	JsonNode rawValue = json.findPath("value");
    	Object value = null;

    	if(type.equals("tracking_started") || type.equals("tracking_ended")) {
    		value = new Boolean(true);
    	} else if(type.equals("fuel_remaining") || type.equals("fuel_consumption")) {
    		value = rawValue.asDouble();
    	} else if(type.equals("vehicle_speed")) {
    		value = rawValue.asInt();
    	} else if(type.equals("distance_traveled")) {
    		value = rawValue.asDouble();
    	} else if(type.equals("location")) {
    		double[] location = new double[2];
    		location[0] = rawValue.get(0).asDouble();
    		location[1] = rawValue.get(1).asDouble();
    		value = location;
    	} else {
    		return badRequest();
    	}

    	DataPoint dp = new DataPoint(deviceId, vin, timestamp, type, value);
    	DataPoint.create(dp);

        return ok("");
    }

}