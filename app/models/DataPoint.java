package models;

import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;

public class DataPoint {
	private static JacksonDBCollection<DataPoint, String> collection = MongoDB.getCollection("datapoints", DataPoint.class, String.class);

	@Id
	@ObjectId
	public String id;

	public String deviceId;
	public String vin;
	public int timestamp;
	public String type;
	public Object value;

	public DataPoint(String deviceId, String name, int timestamp, String type, Object value) {
		this.deviceId = deviceId;
		this.vin = vin;
		this.timestamp = timestamp;
		this.type = type;
		this.value = value;
	}

	public static void create(DataPoint dataPoint) {
		collection.save(dataPoint);
	}
}