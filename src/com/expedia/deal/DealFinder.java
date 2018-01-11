package com.expedia.deal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.expedia.jsonDTOs.Hotel;
import com.expedia.jsonDTOs.Resultant;
import com.google.gson.Gson;
import java.net.URL;

import net.htmlparser.jericho.Source;

/**
 * Servlet implementation class CreateDeal
 */
@WebServlet("/DealFinder")
public class DealFinder extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String URL = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";
	private static final String RESULT_ATTRIBUTE = "RESULT";
	private static final String MSG_ATTRIBUTE = "MSG";

	/**
	 * Default constructor. 
	 */
	public DealFinder() {
		// Empty
	}

	/**
	 * Service method execute the passed request through the command attribute
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			PrintWriter writer = response.getWriter();
			String json = readJsonFromURL(appendQueryParametersTOUri(request));
			Gson gson = new Gson();
			Resultant result = gson.fromJson(json, Resultant.class);
			request.setAttribute(RESULT_ATTRIBUTE, result);
			if (result == null || result.getOffers().getHotel() == null) {
				request.setAttribute(MSG_ATTRIBUTE, "No results found...");
				writer.println("No results found...");
			} else {
				writer.println("Search Results :");
				for (Hotel hotel : result.getOffers().getHotel()) {
					writer.println("**************");
					writer.println("Hotel Name : " + hotel.getHotelInfo().getHotelName());
					writer.println("hotel Star Rating :" + hotel.getHotelInfo().getHotelStarRating());
					writer.println("Number of guests :" + hotel.getHotelUrgencyInfo().getNumberOfPeopleBooked());
					writer.println("Price Per Night :" + hotel.getHotelPricingInfo().getOriginalPricePerNight());
					writer.println("Total Price :" + hotel.getHotelPricingInfo().getTotalPriceValue());
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	



	private String readJsonFromURL(String Url) throws Exception {
		URL url = new URL(Url);
		Source source = new Source(url);
		return source.toString();
	}
	
	
	

	private String appendQueryParametersTOUri(HttpServletRequest req) {
		StringBuilder uriWithParameters = new StringBuilder(URL);
		if(req.getParameter("shortName")!=null && !req.getParameter("shortName").isEmpty())
			uriWithParameters.append("&" + "shortName" + "=" + req.getParameter("shortName"));

		if(req.getParameter("minTripStartDate")!=null&& !req.getParameter("minTripStartDate").isEmpty())
			uriWithParameters.append("&" + "travelStartDate=" + "{\"gt\":" + req.getParameter("minTripStartDate"));
	
		if(req.getParameter("maxTripStartDate")!=null && !req.getParameter("maxTripStartDate").isEmpty())
			uriWithParameters.append(",\"lt\":"+req.getParameter("maxTripStartDate"));
		uriWithParameters.append("}");
		
		if(req.getParameter("lengthOfStay")!=null && !req.getParameter("lengthOfStay").isEmpty())
			uriWithParameters.append("&" + "lengthOfStay" + "=" + req.getParameter("lengthOfStay"));
		
		
		if(req.getParameter("minStarRating")!=null && !req.getParameter("minStarRating").isEmpty())
		
		if(req.getParameter("maxStarRating")!=null && !req.getParameter("maxStarRating").isEmpty())
			uriWithParameters.append(",\"lt\":"+req.getParameter("maxStarRating"));
		uriWithParameters.append("}");

		
		
		if(req.getParameter("minTotalRate")!=null &&!req.getParameter("minTotalRate").isEmpty())
			uriWithParameters.append("&" + "hotelReviewTotal=" + "{\"gt\":" + req.getParameter("minTotalRate"));
		
		if(req.getParameter("maxTotalRate")!=null && !req.getParameter("maxTotalRate").isEmpty())
			uriWithParameters.append(",\"lt\":"+req.getParameter("maxTotalRate"));
		uriWithParameters.append("}");

		
		if(req.getParameter("minGuestRating")!=null && !req.getParameter("minGuestRating").isEmpty())
			uriWithParameters.append("&" + "hotelGuestReviewRating={\"gt\":" + req.getParameter("minGuestRating"));
		
		if(req.getParameter("maxGuestRating")!=null && !req.getParameter("maxGuestRating").isEmpty())
			uriWithParameters.append(",\"lt\":"+req.getParameter("maxGuestRating"));
		
		uriWithParameters.append("}");

		return uriWithParameters.toString();
	}

}
