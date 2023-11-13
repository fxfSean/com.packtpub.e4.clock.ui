package com.packtpub.e4.clock.ui.internal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.zone.ZoneRules;
import java.util.Locale;

public class TimeZoneSummerTimeColumn extends TimeZoneColumn{

	@Override
	public String getText(Object element) {
		if (element instanceof ZoneId) {
			LocalDate date = LocalDate.of(2022, Month.JULY, 1);
			LocalTime time = LocalTime.NOON;
			ZonedDateTime zondatiDateTime = ZonedDateTime.of(date, time, (ZoneId) element);
			ZoneRules rules = ((ZoneId)element).getRules();
			if (rules.isDaylightSavings(zondatiDateTime.toInstant())) {
				return ""+true;
			} else {
				return "" + false;
			}
		} else {
			return "";
		}
	}

	@Override
	public String getTitle() {
		return "Summer Time";
	}

}
