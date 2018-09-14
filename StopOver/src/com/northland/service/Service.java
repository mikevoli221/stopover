package com.northland.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.northland.util.AmadeusAPIUtil;


@Path("/")
public class Service {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchNearbyHotel (
			@QueryParam("airportCode") String airportCode,
			@QueryParam("date") String checkInDate) {		
		
		String response 		= "";  
	
		try {
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(checkInDate));
			cal.add( Calendar.DATE, 1 );
			String checkOutDate = sdf.format(cal.getTime());

			response = AmadeusAPIUtil.getCheapestHotel(airportCode, checkInDate, checkOutDate, "03");
		} 
		catch (Exception e) {	
			e.printStackTrace();
		}
		
		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(response).build(); 
	}
	
	
	
	
}