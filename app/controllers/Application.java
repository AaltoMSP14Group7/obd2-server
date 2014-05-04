package controllers;

import play.*;
import play.mvc.*;

import models.DataPoint;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;

public class Application extends Controller {

    public static Result addDataPoint() {
    	JsonNode json = request().body().asJson();

    	String deviceId = json.findPath("deviceId").textValue();
    	String vin = json.findPath("vin").textValue();
    	int timestamp = json.findPath("timestamp").intValue();
    	String type = json.findPath("type").textValue();
    	JsonNode rawValue = json.findPath("value");
    	Object value = null;

    	if(rawValue.isBoolean()) {
            value = rawValue.asBoolean();
        } else if(rawValue.isDouble()) {
            value = rawValue.asDouble();
        } else if(rawValue.isInt()) {
            value = rawValue.asInt();
    	} else if(type.equals("location")) {
            // Location is special: it's always an array with to items
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

    public static Result getSpec() {
    	return ok(Play.application().getFile("/specs/spec.xml"));
    }

}