package com.packtpub.e4.clock.ui.internal;

import java.time.ZoneId;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.packtpub.e4.clock.ui.ClockWidget;

public class TimeZoneDialog extends MessageDialog{
	private ZoneId timeZone;

	public TimeZoneDialog(Shell parentShell, ZoneId timeZone) {
		super(parentShell, timeZone.getId(), null, 
				timeZone.getId(), INFORMATION, 
				new String[] {IDialogConstants.OK_LABEL},
				0);
		this.timeZone = timeZone;
	}

	@Override
	protected Control createCustomArea(Composite parent) {
		ClockWidget clockWidget = new ClockWidget(parent, SWT.NONE, new RGB(128, 255, 0));
		return parent;
	}
}
