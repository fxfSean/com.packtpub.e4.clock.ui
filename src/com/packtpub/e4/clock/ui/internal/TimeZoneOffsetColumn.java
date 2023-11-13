package com.packtpub.e4.clock.ui.internal;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeZoneOffsetColumn extends TimeZoneColumn{

	@Override
	public String getText(Object element) {
		if (element instanceof ZoneId) {
			return ((ZoneId)element).getRules().getOffset(Instant.now()).toString();
			
		} else {
			return "";
		}
	}

	@Override
	public String getTitle() {
		return "Offset";
	}

}
