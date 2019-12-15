package batch.tmp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Java8DateTime {

	public static void main(String args[]) {

		Java8DateTime tester = new Java8DateTime();
		tester.testLocalDateTime();
		tester.testZonedDateTime();
		tester.testChromoUnits();
	}

	public void testLocalDateTime() {
		// Get the current date and time
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("Current LocalDateTime: " + currentTime);

		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("date1: " + date1);

		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int seconds = currentTime.getSecond();

		System.out.println("Month: " + month + "day: " + day + "seconds: " + seconds);

		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date2: " + date2);

		// 12 december 2014
		LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println("date3: " + date3);

		// 22 hour 15 minutes
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4: " + date4);

		// parse a string
		LocalTime date5 = LocalTime.parse("20:15:30");
		System.out.println("date5: " + date5);
	}

	public void testZonedDateTime() {
		// Get the current date and time
		ZonedDateTime date1 = ZonedDateTime
				.parse("2007-12-03T10:15:30+05:30[Asia/Karachi]");
		System.out.println("date1: " + date1);

		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);

		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("CurrentZone: " + currentZone);
		
		ZonedDateTime nowDate = ZonedDateTime.now();
		System.out.println("Current ZonedDateTime: " + nowDate);
		System.out.println("Formated ZonedDateTime: " + nowDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
		System.out.println("Formated ZonedDateTime: " + nowDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
	}
	
	public void testChromoUnits() {
	      //Get the current date
	      LocalDate today = LocalDate.now();
	      System.out.println("Current date: " + today);
			
	      //add 1 week to the current date
	      LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
	      System.out.println("Next week: " + nextWeek);
			
	      //add 1 month to the current date
	      LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
	      System.out.println("Next month: " + nextMonth);
			
	      //add 1 year to the current date
	      LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
	      System.out.println("Next year: " + nextYear);
			
	      //add 10 years to the current date
	      LocalDate nextDecade = today.plus(1, ChronoUnit.DECADES);
	      System.out.println("Date after ten year: " + nextDecade);
	   }

}
