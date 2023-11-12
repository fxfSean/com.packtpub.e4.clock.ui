package com.packtpub.e4.clock.ui.internal;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TimeZoneViewerFilter extends ViewerFilter{

	private String pattern;
	public TimeZoneViewerFilter(String pattern) {
		this.pattern = pattern;
	}
	@Override
	public boolean select(Viewer v, Object parent, Object element) {
		if (element instanceof ZoneId) {
			ZoneId zoneId = (ZoneId)element;
			String displayName = zoneId.getDisplayName(TextStyle.FULL, Locale.getDefault());
			return displayName.contains(pattern);
		} else {
			return true;
		}
	}
	

}
