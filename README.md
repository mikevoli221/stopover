# stopover
Find nearby hotels, around the specific airport for a particular date.

1. Using Eclipse to export the project to WAR file and deploy it to Tomcat server
2. Test URL: http://localhost:8080/StopOver?airportCode=YVR&date=2018-12-15

Sample Response:
[
  {
    "HotelName":"Coast Vancouver Airport Hotel",
    "HotelAddress":"1041 Sw Marine Drive, Vancouver, BC V6P 6L6, CA",
    "HotelPhone":"1-604-263-1555",
    "HotelCheapestRate":"84.57 USD"
  },
  {
    "HotelName":"La Quinta Inn Vancouver Airport",
    "HotelAddress":"18640 Alexandra Road, Richmond, BC Y6X1C4, CA",
    "HotelPhone":"+1 604 276-2711",
    "HotelCheapestRate":"121.96 USD"
  },
  {
    "HotelName":"Radisson Hotel Vancouver Arprt",
    "HotelAddress":"8181 Cambie Road, Richmond, BC V6X3X9, CA",
    "HotelPhone":"+1 604 276-8181",
    "HotelCheapestRate":"141.72 USD"
  }
]
