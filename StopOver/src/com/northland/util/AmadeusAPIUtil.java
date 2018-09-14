package com.northland.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AmadeusAPIUtil {

	private static final String REST_URI = "https://api.sandbox.amadeus.com/v1.2/hotels/search-airport";
	private static final String API_KEY = "YGS1liXoHkNbiGnJkjrXYkULyIXReOaq";

	public static String getCheapestHotel(String airportCode, String checkInDate, String checkOutDate, String numberOfResult){

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(REST_URI);
		webTarget = webTarget.queryParam("apikey", API_KEY);
		webTarget = webTarget.queryParam("location",  airportCode);
		webTarget = webTarget.queryParam("check_in", checkInDate);
		webTarget = webTarget.queryParam("check_out", checkOutDate);
		webTarget = webTarget.queryParam("number_of_results",  numberOfResult);

		String response = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);	
		JsonObject root = new JsonParser().parse(response).getAsJsonObject();
		JsonArray amadeusHotelList = root.getAsJsonArray("results");
		JsonArray ourHotelList = new JsonArray();
		
		for (JsonElement je : amadeusHotelList) {
			JsonObject amadeusHotel = (JsonObject)je;
			
			JsonObject addressObj = amadeusHotel.get("address").getAsJsonObject();
			JsonObject rateObj = amadeusHotel.get("min_daily_rate").getAsJsonObject();
			JsonArray contactArray = amadeusHotel.get("contacts").getAsJsonArray();
			
			
			String hotelName = amadeusHotel.get("property_name").getAsString();
			String hotelRate = rateObj.get("amount").getAsString() + " " + rateObj.get("currency").getAsString(); 
			String addressStr = addressObj.get("line1").getAsString() + ", " + 
											addressObj.get("city").getAsString() + ", " +  
											addressObj.get("region") .getAsString()+ " " + 
											addressObj.get("postal_code").getAsString() + ", " +  
											addressObj.get("country").getAsString() ; 
			String phoneStr = "N/A"; 
			for (JsonElement je1 : contactArray) {
				JsonObject contact = (JsonObject)je1;
				if (contact.get("type").getAsString().equalsIgnoreCase("PHONE")){
					phoneStr = contact.get("detail").getAsString();
				}
			}
			
			JsonObject ourHotel = new JsonObject();
			ourHotel.addProperty("HotelName", hotelName);
			ourHotel.addProperty("HotelAddress", addressStr);
			ourHotel.addProperty("HotelPhone", phoneStr);
			ourHotel.addProperty("HotelCheapestRate", hotelRate);
			ourHotelList.add(ourHotel);
			
		}
		
		return ourHotelList.toString();

	} 

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		System.out.println(getCheapestHotel("YVR", "2018-12-15", "2018-12-16", "3"));
		

	}

}
