package com.cloudboy.study.newInJDK8.no7_newDate;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class NewDateClassTest {
	
	public static void testClock() {
		long time1 = System.currentTimeMillis();
		System.out.println(time1);
		
		Clock clock = Clock.systemDefaultZone();
		System.out.println(clock.getZone());
		System.out.println(clock.millis());
		
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);
		System.out.println(legacyDate);
	}
	
	public static void testZone() {
		ZoneId zone0 = ZoneId.systemDefault();
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone0.getId());
		System.out.println(zone0.getRules());
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
	}
	
	public static void testLocalTime() {
		ZoneId zone0 = ZoneId.systemDefault();
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		
		LocalTime now0 = LocalTime.now(zone0);
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		System.out.println(now0);
		System.out.println(now1);
		System.out.println(now2);
		
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);
	}
	
	public static void testLocalDate() {
		ZoneId zone0 = ZoneId.systemDefault();
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		
		LocalDate date0 = LocalDate.now(zone0);
		LocalDate date1 = LocalDate.now(zone1);
		LocalDate date2 = LocalDate.now(zone2);
		
		System.out.println(date0);
		System.out.println(date1);
		System.out.println(date2);
	}
	
	public static void testLocalDateTime() {
		ZoneId zone0 = ZoneId.systemDefault();
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		
		LocalDateTime date0 = LocalDateTime.now(zone0);
		LocalDateTime date1 = LocalDateTime.now(zone1);
		LocalDateTime date2 = LocalDateTime.now(zone2);
		System.out.println(date0);
		System.out.println(date1);
		System.out.println(date2);
		
		DayOfWeek dayOfWeek = date0.getDayOfWeek();
		System.out.println(dayOfWeek.getValue());
	}
	
	public static void main(String[] args) {
//		testClock();
//		testZone();
//		testLocalTime();
//		testLocalDate();
		testLocalDateTime();
	}
}
