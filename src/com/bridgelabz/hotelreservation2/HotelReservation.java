package com.bridgelabz.hotelreservation2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HotelReservation {
	
	private final DateTimeFormatter DATE_RANGE_FORMAT = DateTimeFormatter.ofPattern("ddMMMyyyy");
	ArrayList<HotelInformation> hotels = new ArrayList<>();
	static Scanner s = new Scanner(System.in);
	public boolean addHotel() {
		HotelInformation hotel = new HotelInformation();
		System.out.println("Enter hotel name: ");
		hotel.setName(s.next());
		System.out.println("Enter hotel weekday rate: ");
		hotel.setWeekdayRate(s.nextInt());
		System.out.println("Enter hotel weekend rate: ");
		hotel.setWeekendRate(s.nextInt());
		if(hotels.add(hotel)) {
			System.out.println("Hotel Added");
			System.out.println("\n");
			return true;
		}
		else 
			return false;
	}
	
	public  ArrayList<Hotel> findCheapestHotel(String initialDateRange, String endDateRange) {
		LocalDate initialDate = LocalDate.parse(initialDateRange, DATE_RANGE_FORMAT);
		LocalDate endDate = LocalDate.parse(endDateRange, DATE_RANGE_FORMAT);
		int noOfDaysBetween = (int)ChronoUnit.DAYS.between(initialDate, endDate);
		ArrayList<Hotel> results = (ArrayList<Hotel>) hotels.stream()
				.map(hotel -> {
					Hotel Obj = new Hotel();
					Obj.setHotelName(hotel.getName());
					Obj.setTotalRate(hotel.getTotalRate(noOfDaysBetween));
					return Obj;
				})
				.sorted((type1, type2) -> (int)(type1.getTotalRate() - type2.getTotalRate()))
				.collect(Collectors.toList());
		return (ArrayList<Hotel>) results.stream().filter(result -> result.getTotalRate() == results.get(0).getTotalRate())
				.collect(Collectors.toList());


	}
	public void displayHotel() {
		System.out.println("\nHotels Present in Hotel Reservation System:");
		for(int i=0;i<hotels.size();i++) {
			System.out.println(hotels.get(i));
		}
	}

	public static void main(String[] args) {
		HotelReservation hotelObj = new HotelReservation();
		int ans, ch;
		do {
			System.out.println("Enter the your choice : ");
			System.out.println("1.Add hotel ");
			System.out.println("2.Display hotels");
			System.out.println("3.Show cheapest hotel");
			System.out.println("4.Exit");
			ch = s.nextInt();
			switch(ch) {
			case 1: 
				System.out.println("How many hotels you want to add");
				int number = s.nextInt();
				for(int i=0;i<number;i++)
					hotelObj.addHotel();
				break;
			case 2:
				hotelObj.displayHotel();
				break;
			case 3:
				System.out.println(hotelObj.findCheapestHotel("10Sep2021", "11Sep2021"));
				break;
			case 4:
				break;
			}
			System.out.println("Do you want to continue? if yes press '1' ");
			ans = s.nextInt();
		}while(ans == 1);
		s.close();
	}

}
